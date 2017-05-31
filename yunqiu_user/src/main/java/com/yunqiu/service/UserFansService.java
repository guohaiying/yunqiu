package com.yunqiu.service;

import java.util.Map;

/**
 * Created by 11366 on 2017/2/7.
 */
public interface UserFansService {
    Map<String,Object> attention(String user_id,String focus);
    Map<String,Object> cancelAttention(String user_id,String focus);
    Map<String,Object> fansList(int pageNum,int pageSize,String user_id);
    Map<String,Object> focusList(int pageNum,int pageSize,String user_id);
}
