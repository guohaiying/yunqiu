package com.yunqiu.service.impl;

import com.yunqiu.dao.JoinTeamMapper;
import com.yunqiu.dao.TeamMapper;
import com.yunqiu.dao.TeamMemberMapper;
import com.yunqiu.model.JoinTeam;
import com.yunqiu.model.Team;
import com.yunqiu.model.TeamMember;
import com.yunqiu.service.JoinTeamService;
import com.yunqiu.service.PushMessageService;
import com.yunqiu.util.ControllerReturnBase;
import com.yunqiu.util.LoggerUtil;
import com.yunqiu.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

/**
 * Created by 11366 on 2017/1/21.
 */

@Service
public class JoinTeamServiceImpl implements JoinTeamService {
    @Autowired
    private TeamMemberMapper teamMemberMapper;
    @Autowired
    private JoinTeamMapper joinTeamMapper;
    @Autowired
    private TeamMapper teamMapper;
    @Autowired
    private PushMessageService pushMessageService;

    /**
     * 申请加入球队
     * @param teamId
     * @param userId
     * @return
     */
    @Override
    public Map<String, Object> applyJoinTeam(String teamId, String userId,String remark) {
        try {
            //参数验证
            if (Utils.isNull(teamId)){
                return ControllerReturnBase.errorResule(1501,"缺少球队ID");
            }
            //验证该用户是否已是该球队成员
            TeamMember teamMember = teamMemberMapper.selectTeamMemberByTeamIdAndUserId(teamId,userId);
            if (teamMember != null){
                return ControllerReturnBase.errorResule(1502,"申请无效,已是该球队成员");
            }
            //验证该用户是否已提交申请
            JoinTeam joinTeam = joinTeamMapper.selectJoinTeamByTeamIdAndUserIdAndStatus(teamId,userId,1);
            if (joinTeam != null){
                return ControllerReturnBase.errorResule(1502,"已提交申请，请勿重复提交");
            }
            //添加申请信息
            JoinTeam db_join_team = new JoinTeam();
            db_join_team.setJoin_id(Utils.getUUID());
            db_join_team.setUser_id(userId);
            db_join_team.setTeam_id(teamId);
            db_join_team.setRemark(remark);
            db_join_team.setApply_time(new Date());
            db_join_team.setStatus(1);
            joinTeamMapper.insertJoinTeam(db_join_team);
            //给管理员推送申请信息
            boolean isPush = pushMessageService.applyJoinTeamPush(userId,teamId);
            return ControllerReturnBase.successResule();
        }catch (Exception e){
            LoggerUtil.outError(JoinTeamServiceImpl.class,"申请加入球队（service）时发生错误",e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 通过邀请码加入球队
     * @param teamId
     * @param userId
     * @param invite
     * @return
     */
    @Override
    public Map<String, Object> applyJoinTeamByInvite(String teamId, String userId, String invite) {
        try {
            if (Utils.isNull(invite) || Utils.isNull(teamId)){
                return ControllerReturnBase.errorResule(1501,"缺少参数");
            }
            Team team = teamMapper.selectTeamInfo(teamId);
            if (!invite.equals(team.getInvite())){
                return ControllerReturnBase.errorResule(1502,"邀请码不匹配");
            }
            TeamMember teamMember = new TeamMember();
            teamMember.setMember_id(Utils.getUUID());
            teamMember.setUser_id(userId);
            teamMember.setTeam_id(teamId);
            teamMember.setIdentity(5);
            teamMember.setJurisdiction(0);
            teamMember.setEnqueue_time(new Date());
            teamMember.setStatus(1);
            teamMemberMapper.insertTeamMember(teamMember);
            return ControllerReturnBase.successResule();
        }catch (Exception e){
            LoggerUtil.outError(JoinTeamServiceImpl.class,"通过邀请码加入球队时发生错误",e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 审核加入球队申请
     * @param join_id
     * @param audit_msg
     * @param user_id
     * @return
     */
    @Override
    public Map<String, Object> auditJoin(String join_id, String audit_msg,int status, String user_id) {
        try {
            //验证参数
            if (Utils.isNull(join_id)){
                return ControllerReturnBase.errorResule(1501,"缺少申请id");
            }
            if (status != 1 && status !=2){
                return ControllerReturnBase.errorResule(1501,"审核意见错误");
            }
            //验证申请id
            JoinTeam joinTeam = joinTeamMapper.selectJoinTeamById(join_id);
            if (joinTeam == null){
                return ControllerReturnBase.errorResule(1502,"申请信息不存在");
            }
            //验证是否已是球队成员
            TeamMember teamMember = teamMemberMapper.selectTeamMemberByTeamIdAndUserId(joinTeam.getTeam_id(),joinTeam.getUser_id());
            if (teamMember != null){
                return ControllerReturnBase.errorResule(1502,"已是该球队成员");
            }
            //验证权限
             teamMember = teamMemberMapper.selectTeamMemberByTeamIdAndUserId(joinTeam.getTeam_id(),user_id);
            if (teamMember == null){
                return ControllerReturnBase.errorResule(1502,"不是该球队成员，无权修改");
            }
            if (teamMember.getJurisdiction() != 1 && teamMember.getJurisdiction() !=2){
                return ControllerReturnBase.errorResule(1502,"无权限操作");
            }
            //修改状态
            if (status == 2){
                //拒绝通过申请
                joinTeamMapper.updateStatus(join_id,audit_msg,3,new Date());
                return ControllerReturnBase.successResule();
            }else {
                //通过申请
                joinTeamMapper.updateStatus(join_id,audit_msg,2,new Date());
                //添加球员
                teamMember = new TeamMember();
                teamMember.setMember_id(Utils.getUUID());
                teamMember.setUser_id(joinTeam.getUser_id());
                teamMember.setTeam_id(joinTeam.getTeam_id());
                teamMember.setIdentity(5);
                teamMember.setJurisdiction(0);
                teamMember.setEnqueue_time(new Date());
                teamMember.setStatus(1);
                teamMemberMapper.insertTeamMember(teamMember);
                return ControllerReturnBase.successResule();
            }
        }catch (Exception e){
            LoggerUtil.outError(JoinTeamServiceImpl.class,"审核球员加入申请时报错",e);
            return ControllerReturnBase.errorResule();
        }
    }
}
