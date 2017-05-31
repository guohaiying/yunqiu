package com.yunqiu.tournament.model;

import java.io.Serializable;

/**
 * 比赛战报
 */
public class GameGrand implements Serializable {
    private String grand_id;
    private String game_id;
    private int type;
    private String team_id;
    private String user_id;
    private int result;
    private String parent_id;

    public String getGrand_id() {
        return grand_id;
    }

    public void setGrand_id(String grand_id) {
        this.grand_id = grand_id;
    }

    public String getGame_id() {
        return game_id;
    }

    public void setGame_id(String game_id) {
        this.game_id = game_id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTeam_id() {
        return team_id;
    }

    public void setTeam_id(String team_id) {
        this.team_id = team_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getParent_id() {
        return parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }
}
