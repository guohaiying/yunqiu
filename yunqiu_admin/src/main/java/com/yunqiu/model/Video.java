package com.yunqiu.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;


public class Video implements Serializable {

    private String videoId;//主键
    private String videoName;
    private String videoAddressHigh;
    private String videoAddressOrdinary;
    private String videoAddressSmooth;
    private String screenshots;
    private int videoTag;
    private String leagueId;
    private String gameId;
    private int ifShow;
    private String userId;
    @DateTimeFormat( pattern = "yyyy-MM-dd HH:mm" )
    private Date createTime;
    private int classify;//所属分类：1:赛事视频 2:比赛视频 3:发现(精彩)视频
    private String brief;//简介
    private int videoType;//1视频  2GIF
    private String vid;//视频ID
    private int duration;

    public String getVideoId() {
        return videoId;
    }

    public String getVideoName() {
        return videoName;
    }

    public String getVideoAddressHigh() {
        return videoAddressHigh;
    }

    public String getVideoAddressOrdinary() {
        return videoAddressOrdinary;
    }

    public String getVideoAddressSmooth() {
        return videoAddressSmooth;
    }

    public String getScreenshots() {
        return screenshots;
    }

    public int getVideoTag() {
        return videoTag;
    }

    public String getLeagueId() {
        return leagueId;
    }

    public String getGameId() {
        return gameId;
    }

    public int getIfShow() {
        return ifShow;
    }

    public String getUserId() {
        return userId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public int getClassify() {
        return classify;
    }

    public String getBrief() {
        return brief;
    }

    public int getVideoType() {
        return videoType;
    }

    public String getVid() {
        return vid;
    }

    public int getDuration() {
        return duration;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public void setVideoAddressHigh(String videoAddressHigh) {
        this.videoAddressHigh = videoAddressHigh;
    }

    public void setVideoAddressOrdinary(String videoAddressOrdinary) {
        this.videoAddressOrdinary = videoAddressOrdinary;
    }

    public void setVideoAddressSmooth(String videoAddressSmooth) {
        this.videoAddressSmooth = videoAddressSmooth;
    }

    public void setScreenshots(String screenshots) {
        this.screenshots = screenshots;
    }

    public void setVideoTag(int videoTag) {
        this.videoTag = videoTag;
    }

    public void setLeagueId(String leagueId) {
        this.leagueId = leagueId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public void setIfShow(int ifShow) {
        this.ifShow = ifShow;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setClassify(int classify) {
        this.classify = classify;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public void setVideoType(int videoType) {
        this.videoType = videoType;
    }

    public void setVid(String vid) {
        this.vid = vid;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
