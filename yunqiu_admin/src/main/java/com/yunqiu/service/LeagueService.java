package com.yunqiu.service;

import com.yunqiu.model.League;
import com.yunqiu.model.PageCrt;

import java.util.List;
import java.util.Map;

/**
 * Created by 11366 on 2017/1/14.
 */
public interface LeagueService {
    Integer selectCount(PageCrt page);

    List<Map> selectPaging(PageCrt page);

    Map<String, Object> addLeague(League league);

    Map<String, Object> updateLeague(League league);

    Map<String, Object> updateHostUnit(League league);

    Map<String, Object> updateLeagueSite(League league);

    Map<String, Object> closeLeague(String leagueId,String username,String password,String userId);

    League getHostUnit(String leagueId);

    Map<String, Object> getLeagueList();

    Integer selectCountTeam(PageCrt page);

    List<Map> selectPagingTeam(PageCrt page);

    Integer selectCountUser(PageCrt page);

    List<Map> selectPagingUser(PageCrt page);

    Integer selectCountPoints(PageCrt page);

    List<Map> selectPagingPoints(PageCrt page);
}
