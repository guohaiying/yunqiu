package com.yunqiu.league.service;

import java.util.Map;

/**
 * 赛事管理业务接口定义
 */
public interface LeagueService {
    Map<String,Object> leagueListScreen(String user_id,int type);
    Map<String,Object> meTeamLeagueList(int pageNum,int pageSize,String team_id,int status,String user_id,int game_system,int type);
    Map<String,Object> leagueInfo(String league_id,String user_id);
}
