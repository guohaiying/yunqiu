package com.yunqiu.service;

import java.util.Map;

/**
 * 用户操作
 * @author 武尊
 * @time 2016-12-30
 * @version 1.0
 */
public interface UserService {
    Map<String,Object> register(String phone,String verifyCode,String ip);
    Map<String,Object> perfectInfo(String userId, String credential, String nickname, String portrait);
    Map<String,Object> login(int identityType,String identifier,String credential,String ip);
    Map<String,Object> updatePasswordByPhone(String phone,String password,String verifycode);
    Map<String,Object> bound(String phone,String openId,int openIdType,String verifycode,String ip,String accessToken,String nickname,String portrait);
    Map<String,Object> refreshToken(String user_id,String refresh_token);
}
