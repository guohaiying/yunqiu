package com.yunqiu.model;

import java.io.Serializable;


public class Area implements Serializable {

    private int id;//主键
    private String areaId;
    private String area;
    private String cityId;

    public void setId(int id) {
        this.id = id;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public int getId() {
        return id;
    }

    public String getAreaId() {
        return areaId;
    }

    public String getArea() {
        return area;
    }

    public String getCityId() {
        return cityId;
    }


}
