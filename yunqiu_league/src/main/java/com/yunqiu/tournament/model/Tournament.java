package com.yunqiu.tournament.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 比赛/赛程实体类
 */
public class Tournament implements Serializable {
    private String game_id;
    private String user_id;
    private String game_name;
    private String entry_teamA;
    private String entry_teamB;
    private int uniform_teamA;
    private int uniform_teamB;
    private int score_teamA;
    private int score_teamB;
    private int spot_teamA;
    private int spot_teamB;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date game_time;
    private double continue_time;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date apply_end_time;
    private int game_system;
    private String game_site;
    private double game_cost;
    private int value_added;
    private int game_status;
    private int score_status;
    private String audit_reason;
    private String refuse_reason;
    private String cancel_reason;
    private int classify;

    public String getGame_id() {
        return game_id;
    }

    public void setGame_id(String game_id) {
        this.game_id = game_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getGame_name() {
        return game_name;
    }

    public void setGame_name(String game_name) {
        this.game_name = game_name;
    }

    public String getEntry_teamA() {
        return entry_teamA;
    }

    public void setEntry_teamA(String entry_teamA) {
        this.entry_teamA = entry_teamA;
    }

    public String getEntry_teamB() {
        return entry_teamB;
    }

    public void setEntry_teamB(String entry_teamB) {
        this.entry_teamB = entry_teamB;
    }

    public int getUniform_teamA() {
        return uniform_teamA;
    }

    public void setUniform_teamA(int uniform_teamA) {
        this.uniform_teamA = uniform_teamA;
    }

    public int getUniform_teamB() {
        return uniform_teamB;
    }

    public void setUniform_teamB(int uniform_teamB) {
        this.uniform_teamB = uniform_teamB;
    }

    public int getScore_teamA() {
        return score_teamA;
    }

    public void setScore_teamA(int score_teamA) {
        this.score_teamA = score_teamA;
    }

    public int getScore_teamB() {
        return score_teamB;
    }

    public void setScore_teamB(int score_teamB) {
        this.score_teamB = score_teamB;
    }

    public int getSpot_teamA() {
        return spot_teamA;
    }

    public void setSpot_teamA(int spot_teamA) {
        this.spot_teamA = spot_teamA;
    }

    public int getSpot_teamB() {
        return spot_teamB;
    }

    public void setSpot_teamB(int spot_teamB) {
        this.spot_teamB = spot_teamB;
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

    public Date getApply_end_time() {
        return apply_end_time;
    }

    public void setApply_end_time(Date apply_end_time) {
        this.apply_end_time = apply_end_time;
    }

    public int getGame_system() {
        return game_system;
    }

    public void setGame_system(int game_system) {
        this.game_system = game_system;
    }

    public String getGame_site() {
        return game_site;
    }

    public void setGame_site(String game_site) {
        this.game_site = game_site;
    }

    public double getGame_cost() {
        return game_cost;
    }

    public void setGame_cost(double game_cost) {
        this.game_cost = game_cost;
    }

    public int getValue_added() {
        return value_added;
    }

    public void setValue_added(int value_added) {
        this.value_added = value_added;
    }

    public int getGame_status() {
        return game_status;
    }

    public void setGame_status(int game_status) {
        this.game_status = game_status;
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

    public int getClassify() {
        return classify;
    }

    public void setClassify(int classify) {
        this.classify = classify;
    }
}
