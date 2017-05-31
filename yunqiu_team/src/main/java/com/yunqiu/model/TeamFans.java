package com.yunqiu.model;

import java.io.Serializable;

/**
 * 球队粉丝
 */
public class TeamFans implements Serializable {
    private String fans_id;
    private String user_id;
    private String team_id;

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

    public String getTeam_id() {
        return team_id;
    }

    public void setTeam_id(String team_id) {
        this.team_id = team_id;
    }
}
