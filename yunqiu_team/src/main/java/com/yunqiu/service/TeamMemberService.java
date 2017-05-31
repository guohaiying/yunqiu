package com.yunqiu.service;

import java.util.Map;

/**
 * Created by 11366 on 2017/1/18.
 */

public interface TeamMemberService {
    Map<String,Object> selectTeamMember(String teamId);
    Map<String,Object> handoverManagement(String operate_user,String teamId,String user_id);
    Map<String,Object> selectTeamMemberManageList(String teamId,String userId);
    Map<String,Object> updateJerseyNo(String teamId,int jerseyNo,String userId,String operate_user);
    Map<String,Object> updateIdentity(String teamId,String userId,int identity,int jurisdiction,String operate_user);
    Map<String,Object> updatePlace(String teamId,String userId,String place,String operate_user);
    Map<String,Object> removeMember(String teamId,String userId,String operate_user);
    Map<String,Object> getBestTeamMember(String team_id);
    Map<String,Object> invitationUser(String team_id,String user_id);
}
