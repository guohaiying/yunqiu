package com.yunqiu.service;

import java.util.Map;

/**
 * Created by 11366 on 2017/1/21.
 */
public interface JoinTeamService {
    Map<String,Object> applyJoinTeam(String teamId,String userId,String remark);
    Map<String,Object> applyJoinTeamByInvite(String teamId,String userId,String invite);
    Map<String,Object> auditJoin(String join_id,String audit_msg,int status,String user_id);
}
