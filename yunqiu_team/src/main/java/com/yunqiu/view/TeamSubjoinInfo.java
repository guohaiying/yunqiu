package com.yunqiu.view;

/**
 * 查看球队信息页面时，球队的附加信息，包括查询人在该球队的身份，球队平均年龄等
 * @author 武尊
 * @time 2017-01-18
 * @version 1.0
 */
public class TeamSubjoinInfo {
    private double mean_age;//球队平均年龄
    private int fans_number;//球队粉丝数
    private int identity;//身份 0：游客 1：队长 2：副队长 3：领队 4：教练 5：队员
    private int jurisdiction;//权限 0:无管理权限 1:超级管理员 2：普通管理员
    private int focus_status;//关注状态 1：未关注  2：已关注
    private String share_url;//分享url

    public double getMean_age() {
        return mean_age;
    }

    public void setMean_age(double mean_age) {
        this.mean_age = mean_age;
    }

    public int getFans_number() {
        return fans_number;
    }

    public void setFans_number(int fans_number) {
        this.fans_number = fans_number;
    }

    public int getIdentity() {
        return identity;
    }

    public void setIdentity(int identity) {
        this.identity = identity;
    }

    public int getJurisdiction() {
        return jurisdiction;
    }

    public void setJurisdiction(int jurisdiction) {
        this.jurisdiction = jurisdiction;
    }

    public int getFocus_status() {
        return focus_status;
    }

    public void setFocus_status(int focus_status) {
        this.focus_status = focus_status;
    }

    public String getShare_url() {
        return share_url;
    }

    public void setShare_url(String share_url) {
        this.share_url = share_url;
    }
}
