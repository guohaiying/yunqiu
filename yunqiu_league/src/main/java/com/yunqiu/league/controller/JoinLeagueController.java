package com.yunqiu.league.controller;

import com.yunqiu.league.model.JoinLeague;
import com.yunqiu.league.service.JoinLeagueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 赛事报名
 */
@RestController
@RequestMapping(value = "/league/join")
@EnableAutoConfiguration
public class JoinLeagueController {
    @Autowired
    private JoinLeagueService joinLeagueService;

    /**
     * 报名赛事
     * @param joinLeague
     * @return
     */
    @RequestMapping(value = "/league",method = RequestMethod.POST)
    public Map<String,Object> joinLeague(JoinLeague joinLeague){
        return joinLeagueService.joinLeague(joinLeague);
    }

    /**
     * 查询报名的球队
     * @param league_id
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/select",method = RequestMethod.GET)
    public Map<String,Object> selectJoin(@RequestParam("league_id") String league_id,@RequestParam("pageNum") int pageNum,
                                         @RequestParam("pageSize") int pageSize){
        return joinLeagueService.selectJoin(league_id,pageNum,pageSize);
    }
}
