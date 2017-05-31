package com.yunqiu.tournament.model;

import java.io.Serializable;

/**
 * 比赛收藏
 */
public class GameCollection implements Serializable {
    private String collect_id;
    private String user_id;
    private String game_id;

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

    public String getGame_id() {
        return game_id;
    }

    public void setGame_id(String game_id) {
        this.game_id = game_id;
    }
}
