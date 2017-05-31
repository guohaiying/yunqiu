package com.yunqiu.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;


public class LeagueHonor implements Serializable {

    private String honorId;//主键
    private String honorName;
    private String imgUrl;
    @DateTimeFormat( pattern = "yyyy-MM-dd HH:mm" )
    private Date creationTime;
    private int sorting;
    private String leagueId;
    private int honorType;

    public String getHonorId() {
        return honorId;
    }

    public String getHonorName() {
        return honorName;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public int getSorting() {
        return sorting;
    }

    public String getLeagueId() {
        return leagueId;
    }

    public int getHonorType() {
        return honorType;
    }

    public void setHonorId(String honorId) {
        this.honorId = honorId;
    }

    public void setHonorName(String honorName) {
        this.honorName = honorName;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public void setSorting(int sorting) {
        this.sorting = sorting;
    }

    public void setLeagueId(String leagueId) {
        this.leagueId = leagueId;
    }

    public void setHonorType(int honorType) {
        this.honorType = honorType;
    }
}
