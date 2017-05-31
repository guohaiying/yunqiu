package com.yunqiu.service;

/**
 * Created by 11366 on 2017/3/8.
 */
public interface TeamCloudDataService {
    //初始化球队云五数据（创建球队时）
    boolean initialization(String team_id);
    //计算云五数据（比赛结束后，录入战报时）
    boolean computeCloudDataByGame(String game_id);
    //计算云五数据（球队成员增减时）
    boolean computeCloudDataByMember(String team_id);
}
