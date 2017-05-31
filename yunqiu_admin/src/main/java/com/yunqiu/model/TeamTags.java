package com.yunqiu.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;


public class TeamTags implements Serializable {

    private String teamtagsId;//主键
    private String tagsName;//标签名称
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;//创建时间

    public String getTeamtagsId() {
        return teamtagsId;
    }

    public void setTeamtagsId(String teamtagsId) {
        this.teamtagsId = teamtagsId;
    }

    public void setTagsName(String tagsName) {
        this.tagsName = tagsName;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getTagsName() {
        return tagsName;
    }

    public Date getCreateTime() {
        return createTime;
    }
}
