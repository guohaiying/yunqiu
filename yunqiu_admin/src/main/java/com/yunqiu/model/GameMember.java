package com.yunqiu.model;

import java.io.Serializable;


public class GameMember implements Serializable {

    private String matchId;//主键
    private String userId;
    private String teamId;
    private String gameId;
    private int status;

    public String getMatchId() {
        return matchId;
    }

    public String getUserId() {
        return userId;
    }

    public String getTeamId() {
        return teamId;
    }

    public String getGameId() {
        return gameId;
    }

    public int getStatus() {
        return status;
    }

    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
