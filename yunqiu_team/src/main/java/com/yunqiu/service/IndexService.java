package com.yunqiu.service;

import java.util.Map;

/**
 * Created by 11366 on 2017/1/24.
 */
public interface IndexService {
    Map<String,Object> indexTeamList(String userId);
    Map<String,Object> indexTeamInfo(String teamId,String userId);
    Map<String,Object> indexRefreshRecommendedTeam(int number);
    Map<String,Object> getRecommendationUser(int number);
    Map<String,Object> getMessageList(String user_id);
    Map<String,Object> getMessage(String user_id,int message_type,String type_id,int pageNum,int pageSize);
    Map<String,Object> updateMessageStatus(String user_id,String message_id,int message_type,String type_id);
}
