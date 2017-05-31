package com.yunqiu.model;

import java.io.Serializable;

/**
 * 权限（角色与菜单目录关联）
 * @author 武尊
 * @time 2017-01-10
 * @version 1.0
 */
public class Power implements Serializable {
    private String powerId;//主键
    private String roleId;//外键，角色id
    private String menuId;//外键，目录id

    public String getPowerId() {
        return powerId;
    }

    public void setPowerId(String powerId) {
        this.powerId = powerId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }
}
