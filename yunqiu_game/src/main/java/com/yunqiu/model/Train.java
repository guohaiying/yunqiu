package com.yunqiu.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 训练
 */
public class Train implements Serializable {
    private String train_id;
    private String team_id;
    private String user_id;
    private String train_name;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date train_time;
    private double train_duration;
    private String train_site;
    private int status;
    private String cncel_reason;

    public String getTrain_id() {
        return train_id;
    }

    public void setTrain_id(String train_id) {
        this.train_id = train_id;
    }

    public String getTeam_id() {
        return team_id;
    }

    public void setTeam_id(String team_id) {
        this.team_id = team_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getTrain_name() {
        return train_name;
    }

    public void setTrain_name(String train_name) {
        this.train_name = train_name;
    }

    public Date getTrain_time() {
        return train_time;
    }

    public void setTrain_time(Date train_time) {
        this.train_time = train_time;
    }

    public double getTrain_duration() {
        return train_duration;
    }

    public void setTrain_duration(double train_duration) {
        this.train_duration = train_duration;
    }

    public String getTrain_site() {
        return train_site;
    }

    public void setTrain_site(String train_site) {
        this.train_site = train_site;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCncel_reason() {
        return cncel_reason;
    }

    public void setCncel_reason(String cncel_reason) {
        this.cncel_reason = cncel_reason;
    }
}
