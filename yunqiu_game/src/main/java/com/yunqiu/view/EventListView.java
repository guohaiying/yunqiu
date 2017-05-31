package com.yunqiu.view;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * 比赛列表信息
 */
public class EventListView {
    private String event_id;//比赛id
    private int game_type;//类型，1：主队 2：客队
    private String sponsor_id;//主队id
    private String sponsor_name;//主队名称
    private String sponsor_badge;//主队队徽
    private int sponsor_score;//主队比分
    private int rival_score;//客队比分
    private String rival_id;//客队id
    private String rival_name;//客队名称
    private String rival_badge;//客队队徽
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date game_time;//比赛时间 yyyy-MM-dd HH:mm
    private String site;//场地
    private int event_status;//状态 1：待审核 2：待迎战 3：拒绝 4：待比赛 5：比赛结束 6：比赛取消
    private int score_status;//比分录入状态 1：未录入 2：已录入
    private int button;//0:无按钮 1 审核按钮 2 迎战按钮
    private int registration_status;//报名状态 1：已报名 2：待定 3：请假
    private int event_match;//比赛参与人数
    private int team_match;//球队总人数

    public String getEvent_id() {
        return event_id;
    }

    public void setEvent_id(String event_id) {
        this.event_id = event_id;
    }

    public int getGame_type() {
        return game_type;
    }

    public void setGame_type(int game_type) {
        this.game_type = game_type;
    }

    public String getSponsor_id() {
        return sponsor_id;
    }

    public void setSponsor_id(String sponsor_id) {
        this.sponsor_id = sponsor_id;
    }

    public String getSponsor_name() {
        return sponsor_name;
    }

    public void setSponsor_name(String sponsor_name) {
        this.sponsor_name = sponsor_name;
    }

    public String getSponsor_badge() {
        return sponsor_badge;
    }

    public void setSponsor_badge(String sponsor_badge) {
        this.sponsor_badge = sponsor_badge;
    }

    public String getRival_id() {
        return rival_id;
    }

    public void setRival_id(String rival_id) {
        this.rival_id = rival_id;
    }

    public String getRival_name() {
        return rival_name;
    }

    public void setRival_name(String rival_name) {
        this.rival_name = rival_name;
    }

    public String getRival_badge() {
        return rival_badge;
    }

    public void setRival_badge(String rival_badge) {
        this.rival_badge = rival_badge;
    }

    public Date getGame_time() {
        return game_time;
    }

    public void setGame_time(Date game_time) {
        this.game_time = game_time;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public int getEvent_status() {
        return event_status;
    }

    public void setEvent_status(int event_status) {
        this.event_status = event_status;
    }

    public int getButton() {
        return button;
    }

    public void setButton(int button) {
        this.button = button;
    }

    public int getRegistration_status() {
        return registration_status;
    }

    public void setRegistration_status(int registration_status) {
        this.registration_status = registration_status;
    }

    public int getEvent_match() {
        return event_match;
    }

    public void setEvent_match(int event_match) {
        this.event_match = event_match;
    }

    public int getTeam_match() {
        return team_match;
    }

    public void setTeam_match(int team_match) {
        this.team_match = team_match;
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

    public int getScore_status() {
        return score_status;
    }

    public void setScore_status(int score_status) {
        this.score_status = score_status;
    }
}
