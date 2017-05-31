package com.yunqiu.service.impl;

import com.yunqiu.dao.TeamMemberMapper;
import com.yunqiu.model.Invitation;
import com.yunqiu.model.TeamMember;
import com.yunqiu.service.PushMessageService;
import com.yunqiu.service.TeamMemberService;
import com.yunqiu.util.ControllerReturnBase;
import com.yunqiu.util.LoggerUtil;
import com.yunqiu.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by 11366 on 2017/1/18.
 */

@Service
public class TeamMemberServiceImpl implements TeamMemberService {
    @Autowired
    private TeamMemberMapper teamMemberMapper;
    @Autowired
    private PushMessageService pushMessageService;

    /**
     * 查询球队成员
     * @param teamId
     * @return
     */
    @Override
    public Map<String, Object> selectTeamMember(String teamId) {
        try {
            //验证球队ID
            if (Utils.isNull(teamId)){
                return ControllerReturnBase.errorResule(1501,"缺少球队ID");
            }
            List<Map<String,Object>> teamMemberList = teamMemberMapper.selectTeamMemberByTeamId(teamId);
            return ControllerReturnBase.successResule(teamMemberList);
        }catch (Exception e){
            LoggerUtil.outError(TeamMemberServiceImpl.class,"查询球队成员时发生错误",e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 移交超级管理员
     * @param operate_user
     * @param teamId
     * @param user_id
     * @return
     */
    @Override
    public Map<String, Object> handoverManagement(String operate_user, String teamId, String user_id) {
        try {
            //验证参数
            if (Utils.isNull(teamId) || Utils.isNull(user_id)){
                return ControllerReturnBase.errorResule(1501,"缺少参数");
            }
            //验证操作人的权限
            TeamMember teamMember = teamMemberMapper.selectTeamMemberByTeamIdAndUserId(teamId,operate_user);
            if (teamMember == null){
                return ControllerReturnBase.errorResule(1502,"不是该球队成员，无权修改");
            }
            if (teamMember.getJurisdiction() != 1){
                return ControllerReturnBase.errorResule(1502,"无权限操作");
            }
            //移交超级管理员
            teamMemberMapper.updateJurisdiction(teamId,user_id,1);
            //更改操作人的权限
            teamMemberMapper.updateJurisdiction(teamId,operate_user,0);
            return ControllerReturnBase.successResule();
        }catch (Exception e){
            LoggerUtil.outError(TeamMemberServiceImpl.class,"移交超级管理员时发生错误",e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 查询球队成员管理列表
     * @param teamId
     * @param userId
     * @return
     */
    @Override
    public Map<String, Object> selectTeamMemberManageList(String teamId, String userId) {
        try {
            //验证参数
            if (Utils.isNull(teamId)){
                return ControllerReturnBase.errorResule(1501,"缺少球队ID");
            }
            //验证权限
            TeamMember teamMember = teamMemberMapper.selectTeamMemberByTeamIdAndUserId(teamId,userId);
            if (teamMember == null){
                return ControllerReturnBase.errorResule(1502,"不是该球队成员，无权修改");
            }
            if (teamMember.getJurisdiction() != 1 && teamMember.getJurisdiction() !=2){
                return ControllerReturnBase.errorResule(1502,"无权限操作");
            }
            //获取列表
            List<Map<String,Object>> teamMemberList = teamMemberMapper.selectTeamMemberManageList(teamId);
            return ControllerReturnBase.successResule(teamMemberList);
        }catch (Exception e){
            LoggerUtil.outError(TeamMemberServiceImpl.class,"查询球队成员管理列表时发生错误",e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 修改球衣号码
     * @param teamId
     * @param jerseyNo
     * @param userId
     * @param operate_user
     * @return
     */
    @Override
    public Map<String, Object> updateJerseyNo(String teamId, int jerseyNo, String userId, String operate_user) {
        try {
            //验证参数
            if (Utils.isNull(teamId) || jerseyNo == 0 || Utils.isNull(userId)){
                return ControllerReturnBase.errorResule(1501,"缺少参数，请参照API文档传参");
            }
            //验证权限
            TeamMember teamMember = teamMemberMapper.selectTeamMemberByTeamIdAndUserId(teamId,operate_user);
            if (teamMember == null){
                return ControllerReturnBase.errorResule(1502,"不是该球队成员，无权修改");
            }
            if (teamMember.getJurisdiction() != 1 && teamMember.getJurisdiction() !=2){
                return ControllerReturnBase.errorResule(1502,"无权限操作");
            }
            //查询球员信息
            teamMember = teamMemberMapper.selectTeamMemberByTeamIdAndUserId(teamId,userId);
            if (teamMember == null){
                return ControllerReturnBase.errorResule(1502,"该球员不存在");
            }
            //修改球员号码
            teamMemberMapper.updateJerseyNo(jerseyNo,teamMember.getTeam_id(),teamMember.getUser_id());
            return ControllerReturnBase.successResule();
        }catch (Exception e){
            LoggerUtil.outError(TeamMemberServiceImpl.class,"修改球员球衣号码时发生错误",e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 设置球员身份跟权限
     * @param teamId
     * @param userId
     * @param identity
     * @param jurisdiction
     * @param operate_user
     * @return
     */
    @Override
    public Map<String, Object> updateIdentity(String teamId, String userId, int identity, int jurisdiction, String operate_user) {
        try {
            //验证参数
            if (Utils.isNull(teamId) || Utils.isNull(userId) || identity == 0){
                return ControllerReturnBase.errorResule(1501,"缺少参数，请参照API文档传参");
            }
            //验证权限
            TeamMember teamMember = teamMemberMapper.selectTeamMemberByTeamIdAndUserId(teamId,operate_user);
            if (teamMember == null){
                return ControllerReturnBase.errorResule(1502,"不是该球队成员，无权修改");
            }
            if (teamMember.getJurisdiction() != 1 && teamMember.getJurisdiction() !=2){
                return ControllerReturnBase.errorResule(1502,"无权限操作");
            }
            //修改权限，首先验证当前操作人是否为超级管理员，否则不修改
            if (teamMember.getJurisdiction() == 1){
                //权限不可为超级管理员，只能是非管理0 跟 普通管理 2
                if (jurisdiction != 2 && jurisdiction != 0){
                    return ControllerReturnBase.errorResule(1502,"管理员类型错误");
                }else {
                    teamMemberMapper.updateJurisdiction(teamId,userId,jurisdiction);
                }
            }else {
                //普通管理员，权限只能是非管理员0
                if (jurisdiction != 0){
                    return ControllerReturnBase.errorResule(1502,"无权修改为管理员");
                }else {
                    teamMemberMapper.updateJurisdiction(teamId,userId,jurisdiction);
                }
            }
            //修改身份
            teamMemberMapper.updateIdentity(teamId,userId,identity);
            //发送推送消息
            boolean isPust = pushMessageService.updateIdentityPush(userId,teamId,identity,jurisdiction);
            return ControllerReturnBase.successResule();
        }catch (Exception e){
            LoggerUtil.outError(TeamMemberServiceImpl.class,"设置球员身份跟权限时发生错误",e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 修改球员位置
     * @param teamId
     * @param userId
     * @param place
     * @param operate_user
     * @return
     */
    @Override
    public Map<String, Object> updatePlace(String teamId, String userId, String place, String operate_user) {
        try {
            //验证参数
            if (Utils.isNull(teamId) || Utils.isNull(userId) || Utils.isNull(place)){
                return ControllerReturnBase.errorResule(1501,"缺少参数，请参照API文档传参");
            }
            //验证权限
            TeamMember teamMember = teamMemberMapper.selectTeamMemberByTeamIdAndUserId(teamId,operate_user);
            if (teamMember == null){
                return ControllerReturnBase.errorResule(1502,"不是该球队成员，无权修改");
            }
            if (teamMember.getJurisdiction() != 1 && teamMember.getJurisdiction() !=2){
                return ControllerReturnBase.errorResule(1502,"无权限操作");
            }
            //修改位置
            teamMemberMapper.updatePlace(teamId,userId,place);
            return ControllerReturnBase.successResule();
        }catch (Exception e){
            LoggerUtil.outError(TeamMemberServiceImpl.class,"设置球员位置时发生错误",e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 移除球队成员
     * @param teamId
     * @param userId
     * @param operate_user
     * @return
     */
    @Override
    public Map<String, Object> removeMember(String teamId, String userId, String operate_user) {
        try {
            //验证参数
            if (Utils.isNull(teamId) || Utils.isNull(userId)){
                return ControllerReturnBase.errorResule(1501,"缺少参数，请参照API文档传参");
            }
            //验证权限
            TeamMember teamMember = teamMemberMapper.selectTeamMemberByTeamIdAndUserId(teamId,operate_user);
            if (teamMember == null){
                return ControllerReturnBase.errorResule(1502,"不是该球队成员，无权修改");
            }
            if (teamMember.getJurisdiction() != 1 && teamMember.getJurisdiction() !=2){
                return ControllerReturnBase.errorResule(1502,"无权限操作");
            }
            //移除球员
            teamMemberMapper.updateStatus(teamId,userId,2);
            return ControllerReturnBase.successResule();
        }catch (Exception e){
            LoggerUtil.outError(TeamMemberServiceImpl.class,"移除球员时发生错误",e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 查询最佳球员
     * @param team_id
     * @return
     */
    @Override
    public Map<String, Object> getBestTeamMember(String team_id) {
        try {
            List<Map<String,Object>> result = teamMemberMapper.getBestTeamMember(team_id);
            return ControllerReturnBase.successResule(result);
        }catch (Exception e){
            LoggerUtil.outError(TeamMemberServiceImpl.class,"查询最佳球员时发生错误",e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 邀请球员加入球队
     * @param team_id
     * @param user_id
     * @return
     */
    @Override
    public Map<String, Object> invitationUser(String team_id, String user_id) {
        try {
            //验证参数
            if (Utils.isNull(team_id) || Utils.isNull(user_id)){
                return ControllerReturnBase.errorResule(1501,"缺少参数，请参照API文档传参");
            }
            //查询球员信息
            TeamMember teamMember = teamMemberMapper.selectTeamMemberByTeamIdAndUserId(team_id,user_id);
            if (teamMember != null){
                return ControllerReturnBase.errorResule(1502,"该球员已是球队的成员");
            }
            Invitation invitation = new Invitation();
            invitation.setInvitation_id(Utils.getUUID());
            invitation.setUser_id(user_id);
            invitation.setTeam_id(team_id);
            invitation.setAudit_status(1);
            invitation.setInvitation_time(new Date());
            teamMemberMapper.insertInvitation(invitation);
            //发送推送消息
            boolean isPushStatus = pushMessageService.insertInvitationPush(team_id,user_id);
            return ControllerReturnBase.successResule();
        }catch (Exception e){
            LoggerUtil.outError(TeamMemberServiceImpl.class,"邀请球员加入球队时发送错误",e);
            return ControllerReturnBase.errorResule();
        }
    }


}
