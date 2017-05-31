package com.yunqiu.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;


public class Place implements Serializable {

    private String placeId;//主键
    private String placeName;//场地名称
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;//创建时间

    public String getPlaceId() {
        return placeId;
    }

    public String getPlaceName() {
        return placeName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
