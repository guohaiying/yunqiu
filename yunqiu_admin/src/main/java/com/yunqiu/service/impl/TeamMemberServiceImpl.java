package com.yunqiu.service.impl;

import com.yunqiu.dao.JoinTeamMapper;
import com.yunqiu.dao.TeamMemberMapper;
import com.yunqiu.model.JoinTeam;
import com.yunqiu.model.PageCrt;
import com.yunqiu.model.TeamMember;
import com.yunqiu.service.TeamMemberService;
import com.yunqiu.util.ControllerReturnBase;
import com.yunqiu.util.LoggerUtil;
import com.yunqiu.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TeamMemberServiceImpl implements TeamMemberService {
    @Autowired
    private TeamMemberMapper teamMemberMapper;

    @Autowired
    private JoinTeamMapper jointeamMapper;


    // 查询总条数
    public Integer selectCount(PageCrt page) {
        return teamMemberMapper.selectTotalPage(page);
    }

    //分页查询
    public List<Map> selectPaging(PageCrt page) {
        return teamMemberMapper.selectePaging(page);
    }

    // 查询总条数
    public Integer selectAllTeamUserCount(PageCrt page) {
        return teamMemberMapper.selectTotalPageUser(page);
    }

    //分页查询
    public List<Map> selectAllTeamUserPaging(PageCrt page) {
        return teamMemberMapper.selectePagingUser(page);
    }




    public Map<String, Object> addTeamMember(TeamMember teamMember) {
        try {
            //判断球队中是否有队长
            if(teamMember.getIdentity()==1){
                int identityCount = jointeamMapper.selectIdentityCount(teamMember.getTeamId());
                if(identityCount>0){
                    return ControllerReturnBase.errorResule(1501, "该球队已经有队长,请选择其他身份");
                }
            }

            //判断球队中是否有超管
            if(teamMember.getJurisdiction()==1){
                int JurisdictionCount = jointeamMapper.selectJurisdictionCount(teamMember.getTeamId());
                if(JurisdictionCount>0){
                    return ControllerReturnBase.errorResule(1501, "该球队已经有超级管理员,请选择其他身份");
                }
            }

            //球队加入申请
            JoinTeam joinTeam = new JoinTeam();
            String joinId = Utils.getID(22);
            joinTeam.setJoinId(joinId);
            joinTeam.setUserId(teamMember.getUserId());
            joinTeam.setTeamId(teamMember.getTeamId());
            joinTeam.setApplyTime(new Date());
            int type = teamMember.getStatus();
            joinTeam.setStatus(type);
            if (type == 2) {//直接加入
                joinTeam.setAuditTime(new Date());
            }
            int result = jointeamMapper.insertJoinTeam(joinTeam);
            if (result <= 0) {
                return ControllerReturnBase.errorResule(1501, "服务器繁忙 请稍后重试");
            }

            if (type == 2) {//直接加入
                String memberId = Utils.getID(22);
                teamMember.setMemberId(memberId);
                //验证参数是否为空
                /*teamMember.setIdentity(5);
                teamMember.setJurisdiction(0);*/
                teamMember.setEnqueueTime(new Date());
                teamMember.setStatus(1);
                result = teamMemberMapper.insertTeamMeaMember(teamMember);
                if (result <= 0) {
                    return ControllerReturnBase.errorResule(1501, "服务器繁忙 请稍后重试");
                }
            }
            //返回参数封装
            Map<String, String> requst_parm = new HashMap<>();
            requst_parm.put("result", result + "");
            return ControllerReturnBase.successResule(result);
        } catch (Exception e) {
            LoggerUtil.outError(TeamMemberService.class, "用户添加时发生错误", e);
            return ControllerReturnBase.errorResule();
        }

    }


    /**
     * 修改
     * @return
     */
    @Override
    public Map<String, Object> updateTeamMember(TeamMember teamMember) {
        try {

            //判断球队中是否有队长
            if(teamMember.getIdentity()==1){
                int identityCount = jointeamMapper.selectIdentityCount(teamMember.getTeamId());
                if(identityCount>0){
                    return ControllerReturnBase.errorResule(1501, "该球队已经有队长,请选择其他身份");
                }
            }

            //判断球队中是否有超管
            if(teamMember.getJurisdiction()==1){
                int JurisdictionCount = jointeamMapper.selectJurisdictionCount(teamMember.getTeamId());
                if(JurisdictionCount>0){
                    return ControllerReturnBase.errorResule(1501, "该球队已经有超级管理员,请选择其他身份");
                }
            }

            int result = teamMemberMapper.updateTeamMember(teamMember);
            if(result<=0){
                return ControllerReturnBase.errorResule(1501, "服务器繁忙 请稍后重试");
            }
            //返回参数封装
            Map<String, String> requst_parm = new HashMap<>();
            requst_parm.put("result", result+"");
            return ControllerReturnBase.successResule(result);
        } catch (Exception e) {
            LoggerUtil.outError(TeamMemberService.class, "赛事修改时发生错误", e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 修改队员状态
     * @return
     */
    @Override
    public Map<String, Object> updateTeamMemberStatus(TeamMember teamMember) {
        try {
            if(teamMember.getStatus()==1){
                JoinTeam joinTeam = new JoinTeam();
                joinTeam.setStatus(2);
                joinTeam.setTeamId(teamMember.getTeamId());
                joinTeam.setUserId(teamMember.getUserId());
                int result = jointeamMapper.updateJoinTeamStatus(joinTeam);
                if(result<=0){
                    return ControllerReturnBase.errorResule(1501, "服务器繁忙 请稍后重试");
                }
            }

            int result = teamMemberMapper.updateTeamMemberStatus(teamMember);
            if(result<=0){
                return ControllerReturnBase.errorResule(1501, "服务器繁忙 请稍后重试");
            }
            //返回参数封装
            Map<String, String> requst_parm = new HashMap<>();
            requst_parm.put("result", result+"");
            return ControllerReturnBase.successResule(result);
        } catch (Exception e) {
            LoggerUtil.outError(TeamMemberService.class, "赛事修改时发生错误", e);
            return ControllerReturnBase.errorResule();
        }
    }


    /**
     * 审批
     * @return
     */
    @Override
    public Map<String, Object> updateShenpi(TeamMember teamMember) {
        try {
            if(teamMember.getStatus()==1){
                JoinTeam joinTeam = new JoinTeam();
                joinTeam.setStatus(2);
                joinTeam.setTeamId(teamMember.getTeamId());
                joinTeam.setUserId(teamMember.getUserId());
                int result = jointeamMapper.updateJoinTeamStatus(joinTeam);
                if(result<=0){
                    return ControllerReturnBase.errorResule(1501, "服务器繁忙 请稍后重试");
                }
            }

            teamMember.setIdentity(5);
            teamMember.setJerseyNo(0);
            teamMember.setEnqueueTime(new Date());
            int result = teamMemberMapper.insertTeamMeaMember(teamMember);
            if(result<=0){
                return ControllerReturnBase.errorResule(1501, "服务器繁忙 请稍后重试");
            }
            //返回参数封装
            Map<String, String> requst_parm = new HashMap<>();
            requst_parm.put("result", result+"");
            return ControllerReturnBase.successResule(result);
        } catch (Exception e) {
            LoggerUtil.outError(TeamMemberService.class, "赛事修改时发生错误", e);
            return ControllerReturnBase.errorResule();
        }
    }


    /**
     * 获取球队名称
     * @return
     */
    @Override
    public Map<String, Object> getTeamName(String teamId){
        try {
            String teamName = teamMemberMapper.getTeamName(teamId);

            return ControllerReturnBase.successResule(teamName);
        } catch (Exception e) {
            LoggerUtil.outError(TeamMemberService.class, "发生错误", e);
            return ControllerReturnBase.errorResule();
        }
    }


    /**
     * 获取用户名称
     * @return
     */
    @Override
    public Map<String, Object> getUserName(String userId){
        try {
            String teamName = teamMemberMapper.getUserName(userId);

            return ControllerReturnBase.successResule(teamName);
        } catch (Exception e) {
            LoggerUtil.outError(TeamMemberService.class, "发生错误", e);
            return ControllerReturnBase.errorResule();
        }
    }

}
