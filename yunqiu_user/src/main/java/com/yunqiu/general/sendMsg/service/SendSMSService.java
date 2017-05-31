package com.yunqiu.general.sendMsg.service;

import java.util.Map;

/**
 * Created by 11366 on 2016/12/31.
 */
public interface SendSMSService {
    Map<String,Object> sendCode(int type,String mobile);
}
