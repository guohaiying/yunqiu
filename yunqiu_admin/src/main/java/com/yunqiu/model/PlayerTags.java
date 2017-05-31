package com.yunqiu.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;


public class PlayerTags implements Serializable {

    private String playertagsId;//主键
    private String tagsName;//标签名称
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;//创建时间


    public void setPlayertagsId(String playertagsId) {
        this.playertagsId = playertagsId;
    }

    public String getPlayertagsId() {
        return playertagsId;
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
