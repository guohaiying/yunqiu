package com.yunqiu.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;


public class UserHonor implements Serializable {

    private String usonId;//主键
    private String userId;
    private String honorId;
    @DateTimeFormat( pattern = "yyyy-MM-dd HH:mm" )
    private Date gain_time;

    public String getUsonId() {
        return usonId;
    }

    public String getUserId() {
        return userId;
    }

    public String getHonorId() {
        return honorId;
    }

    public Date getGain_time() {
        return gain_time;
    }

    public void setUsonId(String usonId) {
        this.usonId = usonId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setHonorId(String honorId) {
        this.honorId = honorId;
    }

    public void setGain_time(Date gain_time) {
        this.gain_time = gain_time;
    }
}
