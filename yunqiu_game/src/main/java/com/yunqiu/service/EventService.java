package com.yunqiu.service;

import com.yunqiu.model.Event;

import java.util.Map;

/**
 * 比赛业务
 */
public interface EventService {
    Map<String,Object> createGame(Event event,String user_id);
    Map<String,Object> selectGameList(String user_id,int pageNum,int pageSize,int status,String time,String team_id);
    Map<String,Object> auditEvent(String event_id,int audit_status,String audit_reason,String user_id);
    Map<String,Object> battleEvent(String event_id,int battle_status,String refuse_reason,String user_id);
    Map<String,Object> cancelEvent(String event_id,String cancel_reason,String user_id);
    Map<String,Object> selectEventInfo(String event_id,String user_id,int type);
    Map<String,Object> selectEventBasis(String event_id);
    Map<String,Object> eventFiltrateConditions(String user_id);

}
