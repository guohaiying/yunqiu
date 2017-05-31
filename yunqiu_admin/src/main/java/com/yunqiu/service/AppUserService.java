package com.yunqiu.service;

import com.yunqiu.model.AppUser;
import com.yunqiu.model.PageCrt;

import java.util.List;
import java.util.Map;

/**
 * Created by 11366 on 2017/1/14.
 */
public interface AppUserService {
    Integer selectCount(PageCrt page);

    List<Map> selectPaging(PageCrt page);

    Map<String, Object> addUser(Map<String, Object> dataMap );

    Map<String, Object> updateUser(AppUser user,String credential,String identifier);

    Map<String, Object> deleteUser(String adminId);

    Map<String, Object> getAllUser();

}
