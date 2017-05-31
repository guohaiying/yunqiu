package com.yunqiu.service;

import com.yunqiu.model.PageCrt;
import com.yunqiu.model.TeamTags;

import java.util.List;
import java.util.Map;

/**
 * Created by 11366 on 2017/1/14.
 */
public interface TeamtagsService {
    Integer selectCount(PageCrt page);

    List<Map> selectPaging(PageCrt page);

    Map<String, Object> addTeamtags(TeamTags teamTags);

    Map<String, Object> updatTeamtags(TeamTags teamTags);

    Map<String, Object> deleteTeamtags(String teamtagsId);

    Map<String, Object> getTeamtagsList();

}
