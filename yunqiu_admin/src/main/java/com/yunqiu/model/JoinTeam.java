package com.yunqiu.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;


public class JoinTeam implements Serializable {

    private String joinId;//主键
    private String userId;
    private String teamId;
    private String remark;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm")
    private Date applyTime;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm")
    private Date auditTime;
    private int status;
    private String auditMsg;

    public String getJoinId() {
        return joinId;
    }

    public String getUserId() {
        return userId;
    }

    public String getTeamId() {
        return teamId;
    }

    public String getRemark() {
        return remark;
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public Date getAuditTime() {
        return auditTime;
    }

    public int getStatus() {
        return status;
    }

    public String getAuditMsg() {
        return auditMsg;
    }

    public void setJoinId(String joinId) {
        this.joinId = joinId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setAuditMsg(String auditMsg) {
        this.auditMsg = auditMsg;
    }
}
