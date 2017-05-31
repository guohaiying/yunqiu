package com.yunqiu.service;

import com.yunqiu.model.Users;

import java.util.Map;

/**
 * Created by 11366 on 2017/1/11.
 */
public interface PersonalCenterService {
    Map<String,Object> modifyInfo(Users users);
    Map<String,Object> updatePassword(String new_password,String worn_password,String user_id,int type);
    Map<String,Object> selectBound(String user_id);
    Map<String,Object> logout(String user_id);
    Map<String,Object> selectInfo(String user_id);
    Map<String,Object> updatePhone(String worn_phone,String new_phone,String verifyCode,String user_id);
    Map<String,Object> selectPassword(String user_id);
    Map<String,Object> unwrap(String user_id,String identifier,int type);
    Map<String,Object> bound(String user_id,String identifier,int type,String ip);
    Map<String,Object> selectUserInfo(String user_id,String operate_user);
}
