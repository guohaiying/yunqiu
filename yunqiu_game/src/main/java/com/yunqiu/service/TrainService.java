package com.yunqiu.service;

import com.yunqiu.model.Train;

import java.util.Map;

/**
 * Created by 11366 on 2017/2/12.
 */
public interface TrainService {
    Map<String,Object> createTrain(Train train);//创建训练
    Map<String,Object> updateTrain(Train train);//修改训练
    Map<String,Object> cancelTrain(String train_id,String cncel_reason,String user_id);//取消比赛
    Map<String,Object> participateTrain(String train_id,int status,String user_id);//报名训练
    Map<String,Object> trainInfo(String train_id,String user_id);//获取训练详情
}
