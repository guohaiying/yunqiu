package com.yunqiu.league.controller;

import com.yunqiu.league.service.LeagueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 赛事管理
 */

@RestController
@RequestMapping("/league")
@EnableAutoConfiguration
public class LeagueController {
    @Autowired
    private LeagueService leagueService;

    /**
     * 获取赛事列表的赛选条件
     * @param type 1:球队赛事列表 2：发现的赛事列表
     * @return
     */
    @RequestMapping(value = "/leagueListScreen",method = RequestMethod.GET)
    public Map<String,Object> leagueListScreen(@RequestParam(value = "type",required = false,defaultValue = "1") int type,
                                               HttpServletRequest request){
        String user_id = request.getHeader("user_id");
        return leagueService.leagueListScreen(user_id,type);
    }

    /**
     * 获取我所在球队的赛事/发现模块的赛事
     * @param pageNum
     * @param pageSize
     * @param team_id
     * @param status
     * @param type 1:球队的赛事列表 2：发现的赛事列表
     * @param request
     * @return
     */
    @RequestMapping(value = "/inte/meTeamLeagueList",method = RequestMethod.GET)
    public Map<String,Object> meTeamLeagueList(@RequestParam("pageNum") int pageNum,@RequestParam("pageSize") int pageSize,
                                               @RequestParam(value = "team_id",required = false,defaultValue = "") String team_id,
                                               @RequestParam(value = "status",required = false,defaultValue = "0") int status,
                                               @RequestParam(value = "game_system",required = false,defaultValue = "0") int game_system,
                                               @RequestParam(value = "type",required = false,defaultValue = "1") int type,
                                         HttpServletRequest request){
        String user_id = request.getHeader("user_id");
        return leagueService.meTeamLeagueList(pageNum,pageSize,team_id,status,user_id,game_system,type);
    }

    /**
     * 获取赛事详情
     * @param league_id
     * @param request
     * @return
     */
    @RequestMapping(value = "/inte/leagueInfo",method = RequestMethod.GET)
    public Map<String,Object> leagueInfo(@RequestParam("league_id") String league_id,HttpServletRequest request){
        String user_id = request.getHeader("user_id");
        return leagueService.leagueInfo(league_id,user_id);
    }
    @RequestMapping(value = "/web/leagueInfo",method = RequestMethod.GET)
    public Map<String,Object> leagueInfoWeb(@RequestParam("league_id") String league_id,HttpServletRequest request){
        String user_id = request.getHeader("user_id");
        return leagueService.leagueInfo(league_id,user_id);
    }
}
