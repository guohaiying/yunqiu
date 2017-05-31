package com.yunqiu.tournament.service;

import java.util.Map;

/**
 * 收藏比赛方法定义
 */
public interface GameCollectionService {
    Map<String,Object> collect(String user_id, String game_id);
    Map<String,Object> cancelCollect(String user_id,String game_id);
}
