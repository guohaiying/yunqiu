package com.yunqiu.service;

import java.util.Map;

/**
 * Created by 11366 on 2017/1/25.
 */
public interface MatchService {
    Map<String,Object> participationGame(String team_id,String event_id,int status,String user_id);
}
