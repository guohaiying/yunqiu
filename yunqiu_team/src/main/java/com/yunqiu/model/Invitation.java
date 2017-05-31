package com.yunqiu.model;

import java.util.Date;

/**
 * 邀请球员加入
 * @author 武尊
 * @time 2017-03-27
 */
public class Invitation {
    private String invitation_id;
    private String user_id;
    private String team_id;
    private int audit_status;
    private Date invitation_time;

    public String getInvitation_id() {
        return invitation_id;
    }

    public void setInvitation_id(String invitation_id) {
        this.invitation_id = invitation_id;
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

    public int getAudit_status() {
        return audit_status;
    }

    public void setAudit_status(int audit_status) {
        this.audit_status = audit_status;
    }

    public Date getInvitation_time() {
        return invitation_time;
    }

    public void setInvitation_time(Date invitation_time) {
        this.invitation_time = invitation_time;
    }
}
