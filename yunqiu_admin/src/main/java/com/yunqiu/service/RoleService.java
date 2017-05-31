package com.yunqiu.service;

import com.yunqiu.model.PageCrt;
import com.yunqiu.model.Role;

import java.util.List;
import java.util.Map;

/**
 * Created by 11366 on 2017/1/14.
 */
public interface RoleService {

    Integer selectCount();

    List<Map> selectPaging(PageCrt page);

    Map<String, Object> addRole(Role role);

    Map<String, Object> updateRole(Role role);

    Map<String, Object> deleteRole(String roleId);

    Map<String, Object> getRoleList();

}
