package com.yunqiu.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;


public class TeamHonor implements Serializable {

    private String teonId;//主键
    private String teamId;
    private String honorId;
    @DateTimeFormat( pattern = "yyyy-MM-dd HH:mm" )
    private Date gain_time;

    public String getTeonId() {
        return teonId;
    }

    public String getTeamId() {
        return teamId;
    }

    public String getHonorId() {
        return honorId;
    }

    public Date getGain_time() {
        return gain_time;
    }

    public void setTeonId(String teonId) {
        this.teonId = teonId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public void setHonorId(String honorId) {
        this.honorId = honorId;
    }

    public void setGain_time(Date gain_time) {
        this.gain_time = gain_time;
    }
}
