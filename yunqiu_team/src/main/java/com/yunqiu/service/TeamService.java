package com.yunqiu.service;

import com.yunqiu.model.Team;

import java.util.Map;

/**
 * Created by 11366 on 2017/1/16.
 */

public interface TeamService {
    Map<String,Object> insertTeam(Team team,String user_id);
    Map<String,Object> selectTeamInfo(String teamId);
    Map<String,Object> updateTeamInfo(Team team,String user_id);
    Map<String,Object> selectTeamDetailedDate(String teamId,String userId);
    Map<String,Object> updateTeamInvite(String teamId,String invite,String userId);
    Map<String,Object> dissolveTeam(String teamId,String userId);
    Map<String,Object> selectTeamList(String userId);
    Map<String,Object> selectTeamAllList(String teamId,int pageNum,int pageSize,String teamName);
    Map<String,Object> selectJoinTeam(String userId);
    Map<String,Object> teamRecord(int pageNum,int pageSize,String team_id);
    Map<String,Object> honor(String team_id);
    Map<String,Object> selectManagementTeam(String user_id);
    Map<String,Object> searchTeamAndUserAndGame(String name,int type,int pageNum,int pageSize);
    Map<String,Object> getBillboard(int type,int pageNum,int pageSize);
    Map<String,Object> getTeamPower(String team_id);
}
