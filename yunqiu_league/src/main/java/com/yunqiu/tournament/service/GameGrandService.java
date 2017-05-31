package com.yunqiu.tournament.service;

import java.util.Map;

/**
 * 比赛战报方法定义
 */
public interface GameGrandService {
    Map<String,Object> enterGrand(String date_json, String user_id);
    Map<String,Object>selectGrand(String game_id);
}
