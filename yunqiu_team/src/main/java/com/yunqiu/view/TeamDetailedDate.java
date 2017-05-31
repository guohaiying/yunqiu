package com.yunqiu.view;

import com.yunqiu.model.Team;
import com.yunqiu.model.TeamCloudData;

import java.util.Map;

/**
 * 查询球队详细数据返回子模型
 *@author 武尊
 * @time 2017-01-18
 * @version 1.0
 */

public class TeamDetailedDate {
    Team team_info;//球队详情
    TeamSubjoinInfo subjoin_info;//附加信息
    TeamCloudData power;//战力值数据
    Map<String,Object> grade;//成绩
    Map<String,Object> member;//球队成员
    Map<String,Object> honor;//球队荣誉
    Map<String,Object> league;//赛事

    public Team getTeam_info() {
        return team_info;
    }

    public void setTeam_info(Team team_info) {
        this.team_info = team_info;
    }

    public TeamSubjoinInfo getSubjoin_info() {
        return subjoin_info;
    }

    public void setSubjoin_info(TeamSubjoinInfo subjoin_info) {
        this.subjoin_info = subjoin_info;
    }

    public TeamCloudData getPower() {
        return power;
    }

    public void setPower(TeamCloudData power) {
        this.power = power;
    }

    public Map<String, Object> getGrade() {
        return grade;
    }

    public void setGrade(Map<String, Object> grade) {
        this.grade = grade;
    }

    public Map<String, Object> getMember() {
        return member;
    }

    public void setMember(Map<String, Object> member) {
        this.member = member;
    }

    public Map<String, Object> getHonor() {
        return honor;
    }

    public void setHonor(Map<String, Object> honor) {
        this.honor = honor;
    }

    public Map<String, Object> getLeague() {
        return league;
    }

    public void setLeague(Map<String, Object> league) {
        this.league = league;
    }
}