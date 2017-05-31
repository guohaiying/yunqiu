package com.yunqiu.service;

import com.yunqiu.model.JoinLeague;
import com.yunqiu.model.PageCrt;

import java.util.List;
import java.util.Map;

/**
 * Created by 11366 on 2017/1/14.
 */
public interface JoinLeagueService {
    Integer selectCount(PageCrt page);

    List<Map> selectPaging(PageCrt page);

    Map<String, Object> addjoinLeague(JoinLeague joinuleague);

    Map<String, Object> upjoinLeague(JoinLeague joinuleague);

    Map<String, Object> deletejoinLeague(String joinId);

    String getJoinLeagueName(String leagueId);

}
