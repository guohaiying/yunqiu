package com.yunqiu.service;

/**
 * 推动消息方法定义
 * @author 武尊
 * @time 2017-03-25
 */
public interface PushMessageService {
    /**
     * 申请加入球队时给球队管理员推送消息
     * @param user_id
     * @param team_id
     * @return
     */
    boolean applyJoinTeamPush(String user_id,String team_id);

    /**
     * 设置球员身份跟权限时给被设置的球员推送消息
     * @param user_id
     * @param team_id
     * @param identity
     * @param jurisdiction
     * @return
     */
    boolean updateIdentityPush(String user_id,String team_id, int identity, int jurisdiction);

    /**
     * 邀请球员加入球队时推送消息
     * @param team_id
     * @param user_id
     * @return
     */
    boolean insertInvitationPush(String team_id,String user_id);
}
