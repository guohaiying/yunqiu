package com.yunqiu.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 申请加入球队
 */
public class JoinTeam implements Serializable{
    private String join_id;
    private String user_id;
    private String team_id;
    private String remark;
    private Date apply_time;
    private Date audit_time;
    private int status;
    private String audit_msg;

    public String getJoin_id() {
        return join_id;
    }

    public void setJoin_id(String join_id) {
        this.join_id = join_id;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getApply_time() {
        return apply_time;
    }

    public void setApply_time(Date apply_time) {
        this.apply_time = apply_time;
    }

    public Date getAudit_time() {
        return audit_time;
    }

    public void setAudit_time(Date audit_time) {
        this.audit_time = audit_time;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getAudit_msg() {
        return audit_msg;
    }

    public void setAudit_msg(String audit_msg) {
        this.audit_msg = audit_msg;
    }
}
