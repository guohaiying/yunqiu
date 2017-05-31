package com.yunqiu.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;


public class Schedule implements Serializable {
    private String scheduleId;
    private String leagueId;
    @DateTimeFormat( pattern = "yyyy-MM-dd HH:mm:ss" )
    private Date startTime;
    @DateTimeFormat( pattern = "yyyy-MM-dd HH:mm:ss" )
    private Date endTime;
    private int leagueType;
    private String scheduleName;
    private int totalRounds;
    private int scheduleStatus;

    public String getScheduleId() {
        return scheduleId;
    }

    public String getLeagueId() {
        return leagueId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public int getLeagueType() {
        return leagueType;
    }

    public String getScheduleName() {
        return scheduleName;
    }

    public int getTotalRounds() {
        return totalRounds;
    }

    public int getScheduleStatus() {
        return scheduleStatus;
    }

    public void setScheduleId(String scheduleId) {
        this.scheduleId = scheduleId;
    }

    public void setLeagueId(String leagueId) {
        this.leagueId = leagueId;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public void setLeagueType(int leagueType) {
        this.leagueType = leagueType;
    }

    public void setScheduleName(String scheduleName) {
        this.scheduleName = scheduleName;
    }

    public void setTotalRounds(int totalRounds) {
        this.totalRounds = totalRounds;
    }

    public void setScheduleStatus(int scheduleStatus) {
        this.scheduleStatus = scheduleStatus;
    }
}
