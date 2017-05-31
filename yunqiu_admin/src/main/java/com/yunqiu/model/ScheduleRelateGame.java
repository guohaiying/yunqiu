package com.yunqiu.model;

import java.io.Serializable;


public class ScheduleRelateGame implements Serializable {
    private String relateId;
    private String scheduleId;
    private String gameId;
    private int rounds;

    public String getRelateId() {
        return relateId;
    }

    public String getScheduleId() {
        return scheduleId;
    }

    public String getGameId() {
        return gameId;
    }

    public int getRounds() {
        return rounds;
    }

    public void setRelateId(String relateId) {
        this.relateId = relateId;
    }

    public void setScheduleId(String scheduleId) {
        this.scheduleId = scheduleId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public void setRounds(int rounds) {
        this.rounds = rounds;
    }
}
