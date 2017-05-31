package com.yunqiu.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 比赛实体
 */
public class Event implements Serializable {
    private String event_id;
    private String user_id;
    private String event_name;
    private String sponsor_team;
    private String rival_team;
    private int sponsor_score;
    private int rival_score;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date game_time;
    private double continue_time;
    private int game_system;
    private String site;
    private String sponsor_uniform;
    private String rival_uniform;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date apply_end_time;
    private double event_cost;
    private int value_added;
    private int audit_status;
    private int event_status;
    private int score_status;
    private String audit_reason;
    private String refuse_reason;
    private String cancel_reason;

    public String getEvent_id() {
        return event_id;
    }

    public void setEvent_id(String event_id) {
        this.event_id = event_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getSponsor_team() {
        return sponsor_team;
    }

    public void setSponsor_team(String sponsor_team) {
        this.sponsor_team = sponsor_team;
    }

    public String getRival_team() {
        return rival_team;
    }

    public void setRival_team(String rival_team) {
        this.rival_team = rival_team;
    }

    public int getSponsor_score() {
        return sponsor_score;
    }

    public void setSponsor_score(int sponsor_score) {
        this.sponsor_score = sponsor_score;
    }

    public int getRival_score() {
        return rival_score;
    }

    public void setRival_score(int rival_score) {
        this.rival_score = rival_score;
    }

    public Date getGame_time() {
        return game_time;
    }

    public void setGame_time(Date game_time) {
        this.game_time = game_time;
    }

    public double getContinue_time() {
        return continue_time;
    }

    public void setContinue_time(double continue_time) {
        this.continue_time = continue_time;
    }

    public int getGame_system() {
        return game_system;
    }

    public void setGame_system(int game_system) {
        this.game_system = game_system;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getSponsor_uniform() {
        return sponsor_uniform;
    }

    public void setSponsor_uniform(String sponsor_uniform) {
        this.sponsor_uniform = sponsor_uniform;
    }

    public String getRival_uniform() {
        return rival_uniform;
    }

    public void setRival_uniform(String rival_uniform) {
        this.rival_uniform = rival_uniform;
    }

    public Date getApply_end_time() {
        return apply_end_time;
    }

    public void setApply_end_time(Date apply_end_time) {
        this.apply_end_time = apply_end_time;
    }

    public int getAudit_status() {
        return audit_status;
    }

    public void setAudit_status(int audit_status) {
        this.audit_status = audit_status;
    }

    public int getEvent_status() {
        return event_status;
    }

    public void setEvent_status(int event_status) {
        this.event_status = event_status;
    }

    public int getScore_status() {
        return score_status;
    }

    public void setScore_status(int score_status) {
        this.score_status = score_status;
    }

    public String getAudit_reason() {
        return audit_reason;
    }

    public void setAudit_reason(String audit_reason) {
        this.audit_reason = audit_reason;
    }

    public String getRefuse_reason() {
        return refuse_reason;
    }

    public void setRefuse_reason(String refuse_reason) {
        this.refuse_reason = refuse_reason;
    }

    public String getCancel_reason() {
        return cancel_reason;
    }

    public void setCancel_reason(String cancel_reason) {
        this.cancel_reason = cancel_reason;
    }

    public String getEvent_name() {
        return event_name;
    }

    public void setEvent_name(String event_name) {
        this.event_name = event_name;
    }

    public double getEvent_cost() {
        return event_cost;
    }

    public void setEvent_cost(double event_cost) {
        this.event_cost = event_cost;
    }

    public int getValue_added() {
        return value_added;
    }

    public void setValue_added(int value_added) {
        this.value_added = value_added;
    }
}
