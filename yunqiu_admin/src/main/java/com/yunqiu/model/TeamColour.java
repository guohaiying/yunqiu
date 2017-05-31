package com.yunqiu.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;


public class TeamColour implements Serializable {

    private String teamcolorId;//主键
    private String colour;//颜色
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;//创建时间


    public String getTeamcolorId() {
        return teamcolorId;
    }

    public String getColour() {
        return colour;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setTeamcolorId(String teamcolorId) {
        this.teamcolorId = teamcolorId;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
