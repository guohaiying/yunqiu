package com.yunqiu.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by guohaiying on 2017/2/22.
 */
public class JoinLeague {

    private String joinId;//主键
    private String leagueId;//外键，关联赛事（参加的赛事）
    private String teamId;//外键，关联球队（报名的球队）
    @DateTimeFormat( pattern = "yyyy-MM-dd HH:mm" )
    private Date joinTime;//报名时间
    private int auditStatus;//	审核状态 1：待审核 2：通过 3：拒绝
    @DateTimeFormat( pattern = "yyyy-MM-dd HH:mm" )
    private Date auditTime;//审核时间
    private String auditOpinion;//审核意见

    public void setJoinId(String joinId) {
        this.joinId = joinId;
    }

    public void setLeagueId(String leagueId) {
        this.leagueId = leagueId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public void setJoinTime(Date joinTime) {
        this.joinTime = joinTime;
    }

    public void setAuditStatus(int auditStatus) {
        this.auditStatus = auditStatus;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    public void setAuditOpinion(String auditOpinion) {
        this.auditOpinion = auditOpinion;
    }

    public String getJoinId() {
        return joinId;
    }

    public String getLeagueId() {
        return leagueId;
    }

    public String getTeamId() {
        return teamId;
    }

    public Date getJoinTime() {
        return joinTime;
    }

    public int getAuditStatus() {
        return auditStatus;
    }

    public Date getAuditTime() {
        return auditTime;
    }

    public String getAuditOpinion() {
        return auditOpinion;
    }
}

