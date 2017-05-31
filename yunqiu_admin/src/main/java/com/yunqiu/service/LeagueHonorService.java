package com.yunqiu.service;

import com.yunqiu.model.LeagueHonor;
import com.yunqiu.model.PageCrt;
import com.yunqiu.model.TeamColour;

import java.util.List;
import java.util.Map;

/**
 * Created by 11366 on 2017/1/14.
 */
public interface LeagueHonorService {
    Integer selectCount(PageCrt page);

    List<Map> selectPaging(PageCrt page);

    Map<String, Object> addLeagueHonor(LeagueHonor leagueHonor);

    Map<String, Object> addLeagueHonorGL(String honorId,String glId);

    Map<String, Object> updatLeagueHonor(String honorId,String glId,String zid);

    Map<String, Object> deleteLeagueHonor(String honorId,String zid);

    Map<String, Object> getLeagueHonorList(String leagueId);

    Map<String, Object> getLeagueTY(String leagueId,String honorId);

    Map<String, Object> deleteLeagueHonorById(String honorId);

}
