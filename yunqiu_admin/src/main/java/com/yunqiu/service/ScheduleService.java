package com.yunqiu.service;

import com.yunqiu.model.PageCrt;
import com.yunqiu.model.Schedule;
import com.yunqiu.model.ScheduleRelateGame;

import java.util.List;
import java.util.Map;

/**
 * Created by 11366 on 2017/1/14.
 */
public interface ScheduleService {
    Integer selectCount(PageCrt page);

    List<Map> selectPaging(PageCrt page);

    Map<String, Object> addSchedule(Schedule schedule);

    Map<String, Object> deleteSchedule(String scheduleId);

    Map<String, Object> getScheduleList(String leagueId);

    Map<String, Object> addScheduleRelateGame(ScheduleRelateGame scheduleRelateGame);

    Map<String, Object> updateScheduleRelateGame(ScheduleRelateGame scheduleRelateGame);

    Map<String, Object> deleteScheduleRelateGame(String relateId,String username,String  password,String  userId);



}
