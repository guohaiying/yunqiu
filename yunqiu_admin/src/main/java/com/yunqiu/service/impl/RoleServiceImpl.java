package com.yunqiu.service.impl;

import com.yunqiu.dao.RoleMapper;
import com.yunqiu.model.PageCrt;
import com.yunqiu.model.Role;
import com.yunqiu.service.RoleService;
import com.yunqiu.util.ControllerReturnBase;
import com.yunqiu.util.LoggerUtil;
import com.yunqiu.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;

    // 查询总条数
    public Integer selectCount() {
        return roleMapper.selectTotalPage();
    }

    //分页查询
    public List<Map> selectPaging(PageCrt page) {
        return roleMapper.selectePaging(page);
    }


    /**
     * 角色添加
     * @return
     */
    @Override
    public Map<String, Object> addRole(Role role) {
        try {
            //验证参数是否为空
            if (Utils.isNull(role.getRoleName())) {
                return ControllerReturnBase.errorResule(1501, "角色名未填写");
            }
            role.setRoleId(Utils.getID(22));
            role.setCreateTime(new Date());
            int result = roleMapper.insertRole(role);
            //返回参数封装
            Map<String, String> requst_parm = new HashMap<>();
            requst_parm.put("result", result+"");
            return ControllerReturnBase.successResule(result);
        } catch (Exception e) {
            LoggerUtil.outError(RoleService.class, "用户添加时发生错误", e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 用户修改
     * @return
     */
    @Override
    public Map<String, Object> updateRole(Role role) {
        try {
            int result = roleMapper.updateRole(role);
            //返回参数封装
            Map<String, String> requst_parm = new HashMap<>();
            requst_parm.put("result", result+"");
            return ControllerReturnBase.successResule(result);
        } catch (Exception e) {
            LoggerUtil.outError(RoleService.class, "用户修改时发生错误", e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 用户删除
     * @return
     */
    @Override
    public Map<String, Object> deleteRole(String roleId) {
        try {
            int result = roleMapper.deleteRoleById(roleId);
            //返回参数封装
            Map<String, String> requst_parm = new HashMap<>();
            requst_parm.put("result", result+"");
            return ControllerReturnBase.successResule(result);
        } catch (Exception e) {
            LoggerUtil.outError(RoleService.class, "用户删除时发生错误", e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 查询所有角色 用于用户授权
     * @return
     */
    @Override
    public Map<String, Object> getRoleList() {
        try {
            List<Map<String, String>> map = roleMapper.selecteAllRole();
            return ControllerReturnBase.successResule(map);
        } catch (Exception e) {
            LoggerUtil.outError(RoleService.class, "查询角色时发生错误", e);
            return ControllerReturnBase.errorResule();
        }
    }

}
