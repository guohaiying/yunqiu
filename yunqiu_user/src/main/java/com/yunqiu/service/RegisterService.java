package com.yunqiu.service;

/**
 * 注册服务
 * @author 武尊
 * @time 2017-01-03
 * @version 1.0
 */
public interface RegisterService {
    int register(String phone,String password,String ip,int type,String userid);
}
