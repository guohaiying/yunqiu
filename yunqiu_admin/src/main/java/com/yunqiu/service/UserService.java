package com.yunqiu.service;

import com.yunqiu.model.AdminUser;
import com.yunqiu.model.PageCrt;

import java.util.List;
import java.util.Map;

/**
 * Created by 11366 on 2017/1/14.
 */
public interface UserService {
    Map<String,Object> login(String userName,String password);

    Integer selectCount(PageCrt page);

    List<Map> selectPaging(PageCrt page);

    Map<String, Object> addUser(AdminUser adminUser,String role);

    Map<String, Object> updateUser(AdminUser adminUser,String role);

    Map<String, Object> deleteUser(String adminId);

    Map<String, Object> freezeUser(int status,String adminId);

    Map<String, Object> uppassword(String password,String adminId);
}
