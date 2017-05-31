package com.yunqiu.controller;

import com.yunqiu.service.TeamMemberService;
import com.yunqiu.util.ControllerReturnBase;
import com.yunqiu.util.LoggerUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 球队成员管理
 */

@RestController
@RequestMapping("/team")
@EnableAutoConfiguration
public class TeamMemberController {
    @Autowired
    private TeamMemberService teamMemberService;

    /**
     * 查询球队成员
     * @param team_id
     * @return
     */
    @RequestMapping(value = "/inte/selectTeamMember",method = RequestMethod.GET)
    public Map<String,Object> selectTeamMember(@RequestParam("team_id") String team_id){
        try {
            return teamMemberService.selectTeamMember(team_id);
        }catch (Exception e){
            LoggerUtil.outError(TeamController.class,"查询球队成员时发生错误",e);
            return ControllerReturnBase.errorResule();
        }
    }
    @RequestMapping(value = "/web/selectTeamMember",method = RequestMethod.GET)
    public Map<String,Object> selectTeamMemberWeb(@RequestParam("team_id") String team_id){
        try {
            return teamMemberService.selectTeamMember(team_id);
        }catch (Exception e){
            LoggerUtil.outError(TeamController.class,"查询球队成员时发生错误",e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 移交超级管理员
     * @param user_id
     * @param team_id
     * @param request
     * @return
     */
    @RequestMapping(value = "/inte/handoverManagement",method = RequestMethod.POST)
    public Map<String,Object> handoverManagement(@RequestParam("user_id") String user_id, @RequestParam("team_id") String team_id, HttpServletRequest request){
        try {
            String operate_user = request.getHeader("user_id");//操作人
            return teamMemberService.handoverManagement(operate_user,team_id,user_id);
        }catch (Exception e){
            LoggerUtil.outError(TeamController.class,"移交超级管理员时发生错误",e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 查询球队成员管理列表
     * @param team_id
     * @param request
     * @return
     */
    @RequestMapping(value = "/inte/selectTeamMemberManageList",method = RequestMethod.GET)
    public Map<String,Object> selectTeamMemberManageList(@RequestParam("team_id") String team_id,HttpServletRequest request){
        try {
            String user_id = request.getHeader("user_id");
            return teamMemberService.selectTeamMemberManageList(team_id,user_id);
        }catch (Exception e){
            LoggerUtil.outError(TeamController.class,"查询球队成员管理列表时发生错误",e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 修改球衣号码
     * @param team_id
     * @param jersey_no
     * @param user_id
     * @param request
     * @return
     */
    @RequestMapping(value = "/inte/updateJerseyNo",method = RequestMethod.POST)
    public Map<String,Object> updateJerseyNo(@RequestParam("team_id") String team_id,@RequestParam("jersey_no") int jersey_no,
                                             @RequestParam("user_id") String user_id,HttpServletRequest request){
        try {
            String operate_user = request.getHeader("user_id");//操作人
            return teamMemberService.updateJerseyNo(team_id,jersey_no,user_id,operate_user);
        }catch (Exception e){
            LoggerUtil.outError(TeamController.class,"修改球员球衣号码时发生错误",e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 设置球员身份跟权限
     * @param team_id
     * @param user_id
     * @param identity
     * @param jurisdiction
     * @param request
     * @return
     */
    @RequestMapping(value = "/inte/settingIdentity",method = RequestMethod.POST)
    public Map<String,Object> updateIdentity(@RequestParam("team_id") String team_id,@RequestParam("user_id") String user_id,@RequestParam("identity") int identity,
                                             @RequestParam("jurisdiction") int jurisdiction,HttpServletRequest request){
        try {
            String operate_user = request.getHeader("user_id");//操作人
            return teamMemberService.updateIdentity(team_id,user_id,identity,jurisdiction,operate_user);
        }catch (Exception e){
            LoggerUtil.outError(TeamController.class,"设置球员身份跟权限时发生错误",e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 修改球员位置
     * @param team_id
     * @param user_id
     * @param place
     * @param request
     * @return
     */
    @RequestMapping(value = "/inte/updatePlace",method = RequestMethod.POST)
    public Map<String,Object> updatePlace(@RequestParam("team_id") String team_id,@RequestParam("user_id") String user_id,
                                          @RequestParam("place") String place,HttpServletRequest request){
        try {
            String operate_user =  request.getHeader("user_id");
            return teamMemberService.updatePlace(team_id,user_id,place,operate_user);
        }catch (Exception e){
            LoggerUtil.outError(TeamController.class,"设置球员位置时发生错误",e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 移除管理员
     * @param team_id
     * @param user_id
     * @param request
     * @return
     */
    @RequestMapping(value = "/inte/removeMember",method = RequestMethod.POST)
    public Map<String,Object> removeMember(@RequestParam("team_id") String team_id,@RequestParam("user_id") String user_id,
                                           HttpServletRequest request){
        try {
            String operate_user =  request.getHeader("user_id");
            return teamMemberService.removeMember(team_id,user_id,operate_user);
        }catch (Exception e){
            LoggerUtil.outError(TeamController.class,"移除球员时发生错误",e);
            return ControllerReturnBase.errorResule();
        }
    }


    /**
     * 查询最佳球员
     * @param team_id
     * @return
     */
    @RequestMapping(value = "/inte/getBestTeamMember",method = RequestMethod.GET)
    public Map<String,Object> getBestTeamMember(@RequestParam("team_id") String team_id){
        return teamMemberService.getBestTeamMember(team_id);
    }
    @RequestMapping(value = "/web/getBestTeamMember",method = RequestMethod.GET)
    public Map<String,Object> getBestTeamMemberWeb(@RequestParam("team_id") String team_id){
        return teamMemberService.getBestTeamMember(team_id);
    }

    /**
     * 邀请球员加入球队
     * @param team_id 发起邀请的球队
     * @param user_id 被邀请的用户
     * @return
     */
    @RequestMapping(value = "/inte/invitationUser",method = RequestMethod.POST)
    public Map<String,Object> invitationUser(@Param("team_id") String team_id,@Param("user_id") String user_id){
        return teamMemberService.invitationUser(team_id,user_id);
    }
}
