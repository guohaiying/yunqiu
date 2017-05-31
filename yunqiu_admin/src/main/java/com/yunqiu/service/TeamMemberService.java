package com.yunqiu.service;

import com.yunqiu.model.PageCrt;
import com.yunqiu.model.TeamMember;

import java.util.List;
import java.util.Map;

/**
 * Created by 11366 on 2017/1/14.
 */
public interface TeamMemberService {
    Integer selectCount(PageCrt page);

    List<Map> selectPaging(PageCrt page);

    Map<String,Object> addTeamMember(TeamMember teamMember);

    Integer selectAllTeamUserCount(PageCrt page);

    List<Map> selectAllTeamUserPaging(PageCrt page);

    Map<String, Object> updateTeamMember(TeamMember teamMember);

    Map<String, Object> updateTeamMemberStatus(TeamMember teamMember);

    Map<String, Object> updateShenpi(TeamMember teamMember);

    Map<String, Object> getTeamName(String teamId);

    Map<String, Object> getUserName(String userId);
}
