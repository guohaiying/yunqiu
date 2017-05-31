package com.yunqiu.model;

import java.io.Serializable;

/**
 * 菜单目录
 * @author 武尊
 * @time 2017-01-10
 * @version 1.0
 */
public class Menu implements Serializable {
    private String menuId;//主键
    private String name;//目录名称
    private String url;//目录链接
    private String icon;//目录图标
    private int type;//类型 1：一级目录 2：二级目录 3：按钮
    private String father;//父节点
    private int sort;//排序

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getFather() {
        return father;
    }

    public void setFather(String father) {
        this.father = father;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.type = sort;
    }
}
