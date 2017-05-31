package com.yunqiu.service;

import java.util.Map;

/**
 * Created by 11366 on 2017/2/8.
 */
public interface EventCollectionService {
    Map<String,Object> collect(String user_id,String event_id);
    Map<String,Object> cancelCollect(String user_id,String event_id);
    Map<String,Object> collectFiltrateConditions(String user_id);
    Map<String,Object> collectList(String user_id,int pageNum,int pageSize,int status,String time);
}
