package com.yunqiu.service;

import com.yunqiu.model.PageCrt;
import com.yunqiu.model.Team;

import java.util.List;
import java.util.Map;

/**
 * Created by 11366 on 2017/1/14.
 */
public interface TeamService {
    Integer selectCount(PageCrt page);

    List<Map> selectPaging(PageCrt page);

    Map<String, Object> addTeam(Team team);

    Map<String, Object> updatePlace(Team team);

    Map<String, Object> disbandTeam(String teamId);

    Map<String, Object> getTeamList();

}
