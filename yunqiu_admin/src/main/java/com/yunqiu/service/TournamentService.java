package com.yunqiu.service;

import com.yunqiu.model.PageCrt;
import com.yunqiu.model.Tournament;

import java.util.List;
import java.util.Map;

/**
 * Created by 11366 on 2017/1/14.
 */
public interface TournamentService {
    Integer selectCount(PageCrt page);

    List<Map> selectPaging(PageCrt page);

    Map<String, Object> addTournament(Tournament tournament);

    Map<String, Object> addTournamentList(Tournament tournament);

    Map<String, Object> updateTournament(Tournament tournament);

    Map<String, Object> updateTournamentList(Tournament tournament);

    Map<String, Object> deleteTournament(String gameId);

    Map<String, Object> getTournamentList();

    Integer selectCountTournament(PageCrt page);

    List<Map> selectPagingTournament(PageCrt page);

    Map<String, Object> getEntryTeamAList(String gameId);

    Map<String, Object> getEntryTeamAName(String gameId);

    Map<String, Object> getEntryTeamBList(String gameId);

    Map<String, Object> getEntryTeamBName(String gameId);

    Map<String, Object>  getEntryTeamNameAB(String gameId);

    Map<String, Object> getEntryTeamByTeamId(String gameId,String teamId);

    Map<String, Object> getEntryTeamAllUser(String gameId);

    Map<String, Object> getEntryTeamNameABMap(String gameId);

}
