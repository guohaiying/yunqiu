package com.yunqiu.model;

import java.io.Serializable;

/**
 * 用户与角色关联
 * @author 武尊
 * @time 2017-01-10
 * @version 1.0
 */
public class UserRole implements Serializable {
    private String urId;//主键
    private String adminId;//外键，管理员id
    private String roleId;//外键，角色id

    public String getUrId() {
        return urId;
    }

    public void setUrId(String urId) {
        this.urId = urId;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}
