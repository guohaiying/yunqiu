package com.yunqiu.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 球队成员
 */
public class TeamMember implements Serializable {
    private String member_id;
    private String user_id;
    private String team_id;
    private int identity;
    private int jurisdiction;
    private Date enqueue_time;
    private int jersey_no;
    private String place;
    private int status;

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
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

    public int getIdentity() {
        return identity;
    }

    public void setIdentity(int identity) {
        this.identity = identity;
    }

    public int getJurisdiction() {
        return jurisdiction;
    }

    public void setJurisdiction(int jurisdiction) {
        this.jurisdiction = jurisdiction;
    }

    public Date getEnqueue_time() {
        return enqueue_time;
    }

    public void setEnqueue_time(Date enqueue_time) {
        this.enqueue_time = enqueue_time;
    }

    public int getJersey_no() {
        return jersey_no;
    }

    public void setJersey_no(int jersey_no) {
        this.jersey_no = jersey_no;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
