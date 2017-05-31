package com.yunqiu.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;


public class Team implements Serializable {

    private String teamId;//主键
    private String teamName;//球队名称
    private String badge;//队徽
    private int teamType;//球队类型 1：校园球队、2：业余爱好、3：公司球队、4：青少年球队
    private String background;//球队的背景图
    @DateTimeFormat( pattern = "yyyy-MM-dd HH:mm" )
    private Date establishTime;//球队成立时间
    private int fansNumber;//球队粉丝数
    private String invite;//球队邀请码
    private String province;//省
    private String city;//市
    private String area;//区/县
    private String home;//主场
    @DateTimeFormat( pattern = "yyyy-MM-dd HH:mm" )
    private Date enterTime;//球队入驻时间
    private String label;//球队标签
    private int status;//球队状态：1：正常、2：解散


    public String getTeamId() {
        return teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public String getBadge() {
        return badge;
    }

    public int getTeamType() {
        return teamType;
    }

    public String getBackground() {
        return background;
    }

    public Date getEstablishTime() {
        return establishTime;
    }

    public int getFansNumber() {
        return fansNumber;
    }

    public String getInvite() {
        return invite;
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

    public String getHome() {
        return home;
    }

    public Date getEnterTime() {
        return enterTime;
    }

    public String getLabel() {
        return label;
    }

    public int getStatus() {
        return status;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public void setBadge(String badge) {
        this.badge = badge;
    }

    public void setTeamType(int teamType) {
        this.teamType = teamType;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public void setEstablishTime(Date establishTime) {
        this.establishTime = establishTime;
    }

    public void setFansNumber(int fansNumber) {
        this.fansNumber = fansNumber;
    }

    public void setInvite(String invite) {
        this.invite = invite;
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

    public void setHome(String home) {
        this.home = home;
    }

    public void setEnterTime(Date enterTime) {
        this.enterTime = enterTime;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
