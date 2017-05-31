package com.yunqiu.league.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 赛事报名
 */

public class JoinLeague implements Serializable {
    private String join_id;
    private String league_id;
    private String team_id;
    private Date join_time;
    private int audit_status;
    private Date audit_time;
    private String audit_opinion;

    public String getJoin_id() {
        return join_id;
    }

    public void setJoin_id(String join_id) {
        this.join_id = join_id;
    }

    public String getLeague_id() {
        return league_id;
    }

    public void setLeague_id(String league_id) {
        this.league_id = league_id;
    }

    public String getTeam_id() {
        return team_id;
    }

    public void setTeam_id(String team_id) {
        this.team_id = team_id;
    }

    public Date getJoin_time() {
        return join_time;
    }

    public void setJoin_time(Date join_time) {
        this.join_time = join_time;
    }

    public int getAudit_status() {
        return audit_status;
    }

    public void setAudit_status(int audit_status) {
        this.audit_status = audit_status;
    }

    public Date getAudit_time() {
        return audit_time;
    }

    public void setAudit_time(Date audit_time) {
        this.audit_time = audit_time;
    }

    public String getAudit_opinion() {
        return audit_opinion;
    }

    public void setAudit_opinion(String audit_opinion) {
        this.audit_opinion = audit_opinion;
    }
}
