package com.yunqiu.model;

import java.io.Serializable;

/**
 * 比赛收藏
 */
public class EventCollection implements Serializable{
    private String collect_id;
    private String user_id;
    private String event_id;

    public String getCollect_id() {
        return collect_id;
    }

    public void setCollect_id(String collect_id) {
        this.collect_id = collect_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getEvent_id() {
        return event_id;
    }

    public void setEvent_id(String event_id) {
        this.event_id = event_id;
    }
}
