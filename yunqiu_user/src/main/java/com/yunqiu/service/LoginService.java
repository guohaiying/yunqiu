package com.yunqiu.service;

import java.util.Map;

/**
 * 登录业务
 * @author 武尊
 * @time 2016-12-31
 * @version 1.0
 */
public interface LoginService {
    Map<String,Object> loginPhone(String phone,String password,String ip);
    Map<String,Object> loginQQ(String qq_openid,String qq_token,String ip);
    Map<String,Object> loginWechat(String code,String credential,String ip);
    Map<String,Object> loginPhoneByCode(String phone,String verifyCode,String ip);
}
