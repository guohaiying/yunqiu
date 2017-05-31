package com.yunqiu.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;


public class TeamMember implements Serializable {

    private String memberId;//主键
    private String userId;
    private String teamId;
    private int identity;
    private int jurisdiction;
    @DateTimeFormat( pattern = "yyyy-MM-dd HH:mm" )
    private Date enqueueTime;
    private int jerseyNo;
    private String place;
    private int status;

    public String getMemberId() {
        return memberId;
    }

    public String getUserId() {
        return userId;
    }

    public String getTeamId() {
        return teamId;
    }

    public int getIdentity() {
        return identity;
    }

    public int getJurisdiction() {
        return jurisdiction;
    }

    public Date getEnqueueTime() {
        return enqueueTime;
    }

    public int getJerseyNo() {
        return jerseyNo;
    }

    public String getPlace() {
        return place;
    }

    public int getStatus() {
        return status;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public void setIdentity(int identity) {
        this.identity = identity;
    }

    public void setJurisdiction(int jurisdiction) {
        this.jurisdiction = jurisdiction;
    }

    public void setEnqueueTime(Date enqueueTime) {
        this.enqueueTime = enqueueTime;
    }

    public void setJerseyNo(int jerseyNo) {
        this.jerseyNo = jerseyNo;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
