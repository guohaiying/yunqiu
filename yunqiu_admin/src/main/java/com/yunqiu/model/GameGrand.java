package com.yunqiu.model;

import java.io.Serializable;


public class GameGrand implements Serializable {

    private String grandId;//主键
    private String gameId;
    private int type;
    private String teamId;
    private String userId;
    private int result;
    private String parentId;
    private int extra;
    private String time;

    public int getExtra() {
        return extra;
    }

    public String getTime() {
        return time;
    }


    public void setExtra(int extra) {
        this.extra = extra;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getGrandId() {
        return grandId;
    }

    public String getGameId() {
        return gameId;
    }

    public int getType() {
        return type;
    }

    public String getTeamId() {
        return teamId;
    }

    public String getUserId() {
        return userId;
    }

    public int getResult() {
        return result;
    }

    public String getParentId() {
        return parentId;
    }

    public void setGrandId(String grandId) {
        this.grandId = grandId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
}
