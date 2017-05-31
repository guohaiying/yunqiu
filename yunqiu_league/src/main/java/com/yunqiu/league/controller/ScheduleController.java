package com.yunqiu.league.controller;

import com.yunqiu.league.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 赛程
 */

@RestController
@RequestMapping("/schedule")
@EnableAutoConfiguration
public class ScheduleController {
    @Autowired
    private ScheduleService scheduleService;

    /**
     * 获取赛程轮次
     * @param league_id 赛事id
     * @return
     */
    @RequestMapping(value = "/inte/selectScheduleRounds",method = RequestMethod.GET)
    public Map<String,Object> selectScheduleRounds(@RequestParam("league_id") String league_id){
        return scheduleService.selectScheduleRounds(league_id);
    }

    @RequestMapping(value = "/web/selectScheduleRounds",method = RequestMethod.GET)
    public Map<String,Object> selectScheduleRoundsWeb(@RequestParam("league_id") String league_id){
        return scheduleService.selectScheduleRounds(league_id);
    }

    /**
     * 获取赛事的赛程
     * @param league_id
     * @return
     */
    @RequestMapping(value = "/inte/selectSchedule",method = RequestMethod.GET)
    public Map<String,Object> selectSchedule(@RequestParam(value = "rounds",required = false,defaultValue = "0") int rounds,
                                             @RequestParam(value = "schedule_id",required = false,defaultValue = "") String schedule_id,
                                             @RequestParam("league_id") String league_id){
        return scheduleService.selectSchedule(league_id,schedule_id,rounds);
    }

    @RequestMapping(value = "/web/selectSchedule",method = RequestMethod.GET)
    public Map<String,Object> selectScheduleWeb(@RequestParam(value = "rounds",required = false,defaultValue = "0") int rounds,
                                             @RequestParam(value = "schedule_id",required = false,defaultValue = "") String schedule_id,
                                             @RequestParam("league_id") String league_id){
        return scheduleService.selectSchedule(league_id,schedule_id,rounds);
    }

    /**
     * 获取赛事积分榜
     * @param league_id
     * @return
     */
    @RequestMapping(value = "/inte/selectTabelle",method = RequestMethod.GET)
    public Map<String,Object> selectTabelle(@RequestParam("league_id") String league_id){
        return scheduleService.selectTabelle(league_id);
    }
    @RequestMapping(value = "/web/selectTabelle",method = RequestMethod.GET)
    public Map<String,Object> selectTabelleWeb(@RequestParam("league_id") String league_id){
        return scheduleService.selectTabelle(league_id);
    }

    /**
     * 获取榜单
     * @param league_id
     * @return
     */
    @RequestMapping(value = "/inte/selectCrunchies",method = RequestMethod.GET)
    public Map<String,Object> selectCrunchies(@RequestParam("league_id") String league_id){
        return scheduleService.selectCrunchies(league_id);
    }
    @RequestMapping(value = "/web/selectCrunchies",method = RequestMethod.GET)
    public Map<String,Object> selectCrunchiesWeb(@RequestParam("league_id") String league_id){
        return scheduleService.selectCrunchies(league_id);
    }
}
