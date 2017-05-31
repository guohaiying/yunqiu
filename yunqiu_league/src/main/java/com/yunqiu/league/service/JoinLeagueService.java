package com.yunqiu.league.service;

import com.yunqiu.league.model.JoinLeague;

import java.util.Map;

/**
 * Created by 11366 on 2017/2/23.
 */

public interface JoinLeagueService {
    Map<String,Object> joinLeague(JoinLeague joinLeague);
    Map<String,Object> selectJoin(String league_id,int pageNum,int pageSize);
}
