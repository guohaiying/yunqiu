package com.yunqiu.controller;

import com.yunqiu.model.Team;
import com.yunqiu.service.TeamService;
import com.yunqiu.util.ControllerReturnBase;
import com.yunqiu.util.LoggerUtil;
import com.yunqiu.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/team")
@EnableAutoConfiguration
public class TeamController {
    @Autowired
    private TeamService teamService;

    /**
     * 创建球队
     * @param team
     * @return
     */
    @RequestMapping(value = "/inte/insertTeam",method = RequestMethod.POST)
    public Map<String,Object> insertTeam(Team team, HttpServletRequest request){
        try {
            String user_id = request.getHeader("user_id");
            return teamService.insertTeam(team,user_id);
        }catch (Exception e){
            LoggerUtil.outError(TeamController.class,"创建球队时发生错误",e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 查询球队基本信息资料
     * @param team_id
     * @return
     */
    @RequestMapping(value = "/inte/selectTeamInfo",method = RequestMethod.GET)
    public Map<String,Object> selectTeamInfo(@RequestParam("team_id") String team_id){
        try {
            return teamService.selectTeamInfo(team_id);
        }catch (Exception e){
            LoggerUtil.outError(TeamController.class,"查询球队基本信息时发生错误",e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 修改球队基础信息
     * @param team
     * @param request
     * @return
     */
    @RequestMapping(value = "/inte/updateTeamInfo",method = RequestMethod.POST)
    public Map<String,Object> updateTeamInfo(Team team,HttpServletRequest request){
        try {
            String user_id = request.getHeader("user_id");
            return teamService.updateTeamInfo(team,user_id);
        }catch (Exception e){
            LoggerUtil.outError(TeamController.class,"修改球队基础信息时发生错误",e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 获取球队详细数据
     * @param team_id
     * @param request
     * @return
     */
    @RequestMapping(value = "/inte/selectTeamDetailedDate",method = RequestMethod.GET)
    public Map<String,Object> selectTeamDetailedDate(@RequestParam("team_id") String team_id,HttpServletRequest request){
        try {
            String user_id = request.getHeader("user_id");
            return teamService.selectTeamDetailedDate(team_id,user_id);
        }catch (Exception e){
            LoggerUtil.outError(TeamController.class,"获取球队详细数据时发生错误",e);
            return ControllerReturnBase.errorResule();
        }
    }
    @RequestMapping(value = "/web/selectTeamDetailedDate",method = RequestMethod.GET)
    public Map<String,Object> selectTeamDetailedDateWeb(@RequestParam("team_id") String team_id,HttpServletRequest request){
        String user_id = request.getHeader("user_id");
        return teamService.selectTeamDetailedDate(team_id,user_id);
    }

    /**
     * 修改球队邀请码
     * @param team_id
     * @param invite
     * @param request
     * @return
     */
    @RequestMapping(value = "/inte/updateTeamInvite",method = RequestMethod.POST)
    public Map<String,Object> updateTeamInvite(@RequestParam("team_id") String team_id,@RequestParam("invite") String invite, HttpServletRequest request){
        try {
            String user_id = request.getHeader("user_id");
            return teamService.updateTeamInvite(team_id,invite,user_id);
        }catch (Exception e){
            LoggerUtil.outError(TeamController.class,"修改球队邀请码时发生错误",e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 解散球队
     * @param team_id
     * @param request
     * @return
     */
    @RequestMapping(value = "/inte/dissolveTeam",method = RequestMethod.POST)
    public Map<String,Object> dissolveTeam(@RequestParam("team_id") String team_id,HttpServletRequest request){
        try {
            String user_id = request.getHeader("user_id");
            return teamService.dissolveTeam(team_id,user_id);
        }catch (Exception e){
            LoggerUtil.outError(TeamController.class,"解散球队时发生错误",e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 查询球队列表
     * @param request
     * @return
     */
    @RequestMapping(value = "/inte/selectTeamList",method = RequestMethod.GET)
    public Map<String,Object> selectTeamList(HttpServletRequest request){
        try {
            String user_id = request.getHeader("user_id");
            return teamService.selectTeamList(user_id);
        }catch (Exception e){
            LoggerUtil.outError(TeamController.class,"查询球队列表时发生错误",e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 查询所有球队列表(除了指定id得球队)
     * @return
     */
    @RequestMapping(value = "/inte/selectTeamAllList",method = RequestMethod.GET)
    public Map<String,Object> selectTeamAllList(@RequestParam(value = "team_id",required = false,defaultValue = "") String team_id,
                                                @RequestParam(value = "pageNum",required = false,defaultValue = "0") int pageNum,
                                                @RequestParam(value = "pageSize",required = false,defaultValue = "0") int pageSize,
                                                @RequestParam(value = "team_name",required = false,defaultValue = "") String team_name){
        try {
            return teamService.selectTeamAllList(team_id,pageNum,pageSize,team_name);
        }catch (Exception e){
            LoggerUtil.outError(TeamController.class,"查询所有球队列表时发生错误",e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 查询可加入的球队(除自己所在的球队)
     * @param request
     * @return
     */
    @RequestMapping(value = "/inte/selectJoinTeam",method = RequestMethod.GET)
    public Map<String,Object> selectJoinTeam(HttpServletRequest request){
        try {
            String user_id = request.getHeader("user_id");
            return teamService.selectJoinTeam(user_id);
        }catch (Exception e){
            LoggerUtil.outError(TeamController.class,"查询可加入球队时发生错误",e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 查询球队战绩
     * @param team_id
     * @return
     */
    @RequestMapping(value = "/inte/teamRecord",method = RequestMethod.GET)
    public Map<String,Object> teamRecord(@RequestParam("pageNum") int pageNum,@RequestParam("pageSize") int pageSize,@RequestParam("team_id") String team_id){
        return teamService.teamRecord(pageNum,pageSize,team_id);
    }

    @RequestMapping(value = "/web/teamRecord",method = RequestMethod.GET)
    public Map<String,Object> teamRecordWeb(@RequestParam("pageNum") int pageNum,@RequestParam("pageSize") int pageSize,@RequestParam("team_id") String team_id){
        return teamService.teamRecord(pageNum,pageSize,team_id);
    }

    /**
     * 查询球队荣誉
     * @param team_id
     * @return
     */
    @RequestMapping(value = "/inte/honor",method = RequestMethod.GET)
    public Map<String,Object> honor(@RequestParam("team_id") String team_id){
        return teamService.honor(team_id);
    }

    @RequestMapping(value = "/web/honor",method = RequestMethod.GET)
    public Map<String,Object> honorWeb(@RequestParam("team_id") String team_id){
        return teamService.honor(team_id);
    }


    /**
     * 获取拥有管理权限的球队
     * @param user_id
     * @param request
     * @return
     */
    @RequestMapping(value = "/inte/selectManagementTeam",method = RequestMethod.GET)
    public Map<String,Object> selectManagementTeam(@RequestParam(value = "user_id",required = false,defaultValue = "") String user_id,
                                                   HttpServletRequest request){
        if (Utils.isNull(user_id)){
            user_id = request.getHeader("user_id");
        }
        return teamService.selectManagementTeam(user_id);
    }

    /**
     * 搜索球队、球员、赛事
     * @param name 搜索条件
     * @param type 类型 0：3类全部，1：球队  2：球员  3：赛事
     * @return
     */
    @RequestMapping(value = "/searchTeamAndUserAndGame",method = RequestMethod.GET)
    public Map<String,Object> searchTeamAndUserAndGame(@RequestParam("name") String name,
                                                       @RequestParam(value = "type",required = false,defaultValue = "0") int type,
                                                       @RequestParam(value = "pageNum",required = false,defaultValue = "1") int pageNum,
                                                       @RequestParam(value = "pageSize",required = false,defaultValue = "20") int pageSize){
        return teamService.searchTeamAndUserAndGame(name,type,pageNum,pageSize);
    }

    /**
     * 获取排行榜
     * @param pageNum 页码
     * @param pageSize 条数
     * @param type  类型 1：球队 2球员
     * @return
     */
    @RequestMapping(value = "/getBillboard",method = RequestMethod.GET)
    public Map<String,Object> getBillboard(@RequestParam(value = "pageNum",required = false,defaultValue = "1") int pageNum,
                                           @RequestParam(value = "pageSize",required = false,defaultValue = "20") int pageSize,
                                           @RequestParam(value = "type",required = false,defaultValue = "1") int type){
        return teamService.getBillboard(type,pageNum,pageSize);
    }


    /**
     * 查询球队战力值
     * @param team_id
     * @return
     */
    @RequestMapping(value = "/getTeamPower",method = RequestMethod.GET)
    public Map<String,Object> getTeamPower(@RequestParam("team_id") String team_id){
        return teamService.getTeamPower(team_id);
    }
}
