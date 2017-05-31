package com.yunqiu.controller;

import com.yunqiu.service.TeamFansService;
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
 * Created by 11366 on 2017/2/11.
 */

@RestController
@RequestMapping("/teamFans")
@EnableAutoConfiguration
public class TeamFansController {
    @Autowired
    private TeamFansService teamFansService;

    /**
     * 关注球队
     * @param team_id
     * @param request
     * @return
     */
    @RequestMapping(value = "/inte/attentionTeam",method = RequestMethod.POST)
    public Map<String,Object> attentionTeam(@RequestParam("team_id") String team_id, HttpServletRequest request){
        String user_id = request.getHeader("user_id");
        return teamFansService.attentionTeam(user_id,team_id);
    }

    /**
     * 取消球队关注
     * @param team_id
     * @param request
     * @return
     */
    @RequestMapping(value = "/inte/cancelAttentionTeam",method = RequestMethod.POST)
    public Map<String,Object> cancelAttentionTeam(@RequestParam("team_id") String team_id,HttpServletRequest request){
        String user_id = request.getHeader("user_id");
        return teamFansService.cancelAttentionTeam(user_id,team_id);
    }

    /**
     * 获取关注的球队列表
     * @param pageNum
     * @param pageSize
     * @param request
     * @return
     */
    @RequestMapping(value = "/inte/focusTeamList",method = RequestMethod.GET)
    public Map<String,Object> focusTeamList(@RequestParam("pageNum") int pageNum,@RequestParam("pageSize") int pageSize,HttpServletRequest request){
        String user_id = request.getHeader("user_id");
        return teamFansService.focusTeamList(pageNum,pageSize,user_id);
    }

    /**
     * 获取球队粉丝
     * @param team_id
     * @return
     */
    @RequestMapping(value = "/inte/getTeamFans",method = RequestMethod.GET)
    public Map<String,Object> getTeamFans(@Param("pageNum") int pageNum,@Param("pageSize") int pageSize,@Param("team_id") String team_id){
        return teamFansService.getTeamFans(pageNum,pageSize,team_id);
    }
}
