package com.yunqiu.model;

import java.io.Serializable;

/**
 * Created by 11366 on 2017/2/7.
 */
public class UserFans implements Serializable {
    private String fans_id;
    private String user_id;
    private String focus;

    public String getFans_id() {
        return fans_id;
    }

    public void setFans_id(String fans_id) {
        this.fans_id = fans_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getFocus() {
        return focus;
    }

    public void setFocus(String focus) {
        this.focus = focus;
    }
}
