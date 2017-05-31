package com.yunqiu.service;

import com.yunqiu.model.PageCrt;
import com.yunqiu.model.TeamColour;

import java.util.List;
import java.util.Map;

/**
 * Created by 11366 on 2017/1/14.
 */
public interface TeamColourService {
    Integer selectCount(PageCrt page);

    List<Map> selectPaging(PageCrt page);

    Map<String, Object> addTeamColour(TeamColour teamColour);

    Map<String, Object> updatTeamColour(TeamColour teamColour);

    Map<String, Object> deleteTeamColour(String teamcolorId);

    Map<String, Object> getTeamColourList();

}
