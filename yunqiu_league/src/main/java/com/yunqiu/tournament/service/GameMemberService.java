package com.yunqiu.tournament.service;

import java.util.Map;

/**
 * 比赛成员操作方法定义
 * @time 2017-02-26
 */
public interface GameMemberService {
    Map<String,Object> participationGame(String game_id,String team_id,String user_id,int join_status);
}
