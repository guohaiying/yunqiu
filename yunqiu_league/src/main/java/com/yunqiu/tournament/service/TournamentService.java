package com.yunqiu.tournament.service;

import com.yunqiu.tournament.model.Tournament;

import java.util.Map;

/**
 * 比赛方法定义
 */

public interface TournamentService {
    Map<String,Object> createTournament(Tournament tournament,String userId);
    Map<String,Object> gameFiltrateConditions(String userId);
    Map<String,Object> selectGameList(String userId,int pageNum,int pageSize,int game_status,String game_time,String team_id);
    Map<String,Object> selectCollectionGameList(String userId,int pageNum,int pageSize);
    Map<String,Object> selectGameInfo(String user_id,String game_id);
    Map<String,Object> auditOrFightGame(String game_id,int type,int comment,String cause,String user_id,int uniform_teamB);
    Map<String,Object> cancelGame(String game_id,String user_id,String cause);
    Map<String,Object> updateGame(Tournament tournament_params,String userId);
    Map<String,Object> getAboutGameList(int pageNum,int pageSize,String name,String game_time,String province,String city,String area,int game_system);
}
