package com.yunqiu.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;


public class League implements Serializable {

    private String leagueId;//主键
    private String leagueAbbreviation;//联赛名称（简称）
    private String leagueName;//联赛全称
    private String introduce;//联赛介绍
    private String news;//赛事新闻
    private String leagueIcon;//联赛图标
    private String background;//联赛背景图
    private String direct;//主办方，多个以”,”号隔开
    private String sponsor;//赞助商，多个以”,”号隔开
    private String undertake;//承办方，多个以”,”号隔开
    private String assisting;//协办方，多个以”,”号隔开
    //@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @DateTimeFormat( pattern = "yyyy-MM-dd HH:mm" )
    private Date startTime;//比赛开始时间,yyyy-MM-dd HH：mm
    //@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @DateTimeFormat( pattern = "yyyy-MM-dd HH:mm" )
    private Date endTime;//比赛结束时间,yyyy-MM-dd HH：mm
    //@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @DateTimeFormat( pattern = "yyyy-MM-dd HH:mm" )
    private Date applyStartTime;//报名开始时间,yyyy-MM-dd HH：mm：ss
    //@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @DateTimeFormat( pattern = "yyyy-MM-dd HH:mm" )
    private Date applyEndTime;//报名结束时间,yyyy-MM-dd HH：mm：ss
    private int gameSystem;//赛制 1:3人 2:5人 3:7人 4:8人 5:9人 6：11人
    private int leagueType;//联赛类型 1:联赛 2:杯赛 3:自定义赛
    private String leagueSite;//场地，多个场地以”,”隔开
    private String province;//省
    private String city;//	市
    private String area;//区/县
    private int status;//1：报名中 2：进行中 3：已结束 4：已取消
    private int ifNotice;//是否通知 1：是 2：否
    private int ifOpen;  //是否启用 1：是 2：否

    public void setLeagueId(String leagueId) {
        this.leagueId = leagueId;
    }

    public void setLeagueAbbreviation(String leagueAbbreviation) {
        this.leagueAbbreviation = leagueAbbreviation;
    }

    public void setLeagueName(String leagueName) {
        this.leagueName = leagueName;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public void setLeagueIcon(String leagueIcon) {
        this.leagueIcon = leagueIcon;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public void setDirect(String direct) {
        this.direct = direct;
    }

    public void setSponsor(String sponsor) {
        this.sponsor = sponsor;
    }

    public void setUndertake(String undertake) {
        this.undertake = undertake;
    }

    public void setAssisting(String assisting) {
        this.assisting = assisting;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public void setApplyStartTime(Date applyStartTime) {
        this.applyStartTime = applyStartTime;
    }

    public void setApplyEndTime(Date applyEndTime) {
        this.applyEndTime = applyEndTime;
    }

    public void setGameSystem(int gameSystem) {
        this.gameSystem = gameSystem;
    }

    public void setLeagueType(int leagueType) {
        this.leagueType = leagueType;
    }

    public void setLeagueSite(String leagueSite) {
        this.leagueSite = leagueSite;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setIfNotice(int ifNotice) {
        this.ifNotice = ifNotice;
    }

    public void setIfOpen(int ifOpen) {
        this.ifOpen = ifOpen;
    }

    public String getLeagueId() {
        return leagueId;
    }

    public String getLeagueAbbreviation() {
        return leagueAbbreviation;
    }

    public String getLeagueName() {
        return leagueName;
    }

    public String getIntroduce() {
        return introduce;
    }

    public String getLeagueIcon() {
        return leagueIcon;
    }

    public String getBackground() {
        return background;
    }

    public String getDirect() {
        return direct;
    }

    public String getSponsor() {
        return sponsor;
    }

    public String getUndertake() {
        return undertake;
    }

    public String getAssisting() {
        return assisting;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public Date getApplyStartTime() {
        return applyStartTime;
    }

    public Date getApplyEndTime() {
        return applyEndTime;
    }

    public int getGameSystem() {
        return gameSystem;
    }

    public int getLeagueType() {
        return leagueType;
    }

    public String getLeagueSite() {
        return leagueSite;
    }

    public String getProvince() {
        return province;
    }

    public String getCity() {
        return city;
    }

    public String getArea() {
        return area;
    }

    public int getStatus() {
        return status;
    }

    public int getIfNotice() {
        return ifNotice;
    }

    public int getIfOpen() {
        return ifOpen;
    }

    public void setNews(String news) {
        this.news = news;
    }

    public String getNews() {
        return news;
    }
}
