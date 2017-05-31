package com.yunqiu.league.service;

import java.util.Map;

/**
 * 赛程方法定义
 */
public interface ScheduleService {
    Map<String,Object> selectScheduleRounds(String league_id);
    Map<String,Object> selectSchedule(String league_id,String schedule_id,int rounds);
    Map<String,Object> selectTabelle(String league_id);
    Map<String,Object> selectCrunchies(String league_id);
}
