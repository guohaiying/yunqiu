package com.yunqiu.controller;

import com.yunqiu.service.JoinTeamService;
import com.yunqiu.util.ControllerReturnBase;
import com.yunqiu.util.LoggerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 申请加入球队
 */

@RestController
@RequestMapping("/team")
@EnableAutoConfiguration
public class JoinTeamController {
    @Autowired
    private JoinTeamService joinTeamService;

    /**
     *申请加入球队
     * @param team_id
     * @param request
     * @return
     */
    @RequestMapping(value = "/inte/applyJoinTeam",method = RequestMethod.POST)
    public Map<String,Object> applyJoinTeam(@RequestParam("team_id") String team_id,@RequestParam("remark") String remark, HttpServletRequest request){
        try {
            String user_id = request.getHeader("user_id");
            return joinTeamService.applyJoinTeam(team_id,user_id,remark);
        }catch (Exception e){
            LoggerUtil.outError(JoinTeamController.class,"申请加入球队时发生错误",e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 通过邀请码加入球队
     * @param team_id
     * @param invite
     * @param request
     * @return
     */
    @RequestMapping(value = "/inte/applyJoinTeamByInvite",method = RequestMethod.POST)
    public Map<String,Object> applyJoinTeamByInvite(@RequestParam("team_id") String team_id,@RequestParam("invite") String invite,HttpServletRequest request){
        String user_id = request.getHeader("user_id");
        return joinTeamService.applyJoinTeamByInvite(team_id,user_id,invite);
    }

    /**
     * 审核加入球队申请
     * @param join_id
     * @param audit_msg
     * @param request
     * @return
     */
    @RequestMapping(value = "/inte/auditJoin",method = RequestMethod.POST)
    public Map<String,Object> auditJoin(@RequestParam("join_id") String join_id,@RequestParam("audit_msg") String audit_msg,
                                        @RequestParam("status") int status, HttpServletRequest request){
        try {
            String user_id = request.getHeader("user_id");
            return joinTeamService.auditJoin(join_id,audit_msg,status,user_id);
        }catch (Exception e){
            LoggerUtil.outError(JoinTeamController.class,"审核申请加入球队时发生错误",e);
            return ControllerReturnBase.errorResule();
        }
    }
}
