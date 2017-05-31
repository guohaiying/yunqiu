package com.yunqiu.service;

import com.yunqiu.model.Power;

import java.util.List;
import java.util.Map;

/**
 * Created by 11366 on 2017/1/14.
 */
public interface PowerService {

    List<String> getMenuRoleByRoleId(String roleId);

    Map<String, Object> addPower(Power power);

    Map<String, Object> deleteMenuRole(String roleId);

    List<Power> getMenuRoleByRoleIdList(String userId);

}
