package com.yunqiu.service;

import java.util.Map;

/**
 * Created by 11366 on 2017/2/11.
 */
public interface TeamFansService {
    Map<String,Object> attentionTeam(String user_id,String team_id);
    Map<String,Object> cancelAttentionTeam(String user_id,String team_id);
    Map<String,Object> focusTeamList(int pageNum,int pageSize,String user_id);
    Map<String,Object> getTeamFans(int pageNum,int pageSize,String team_id);
}
