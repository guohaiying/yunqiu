package com.yunqiu.model;

import java.io.Serializable;


public class City implements Serializable {

    private int id;//主键
    private String cityId;
    private String city;
    private String provinceId;

    public void setId(int id) {
        this.id = id;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public int getId() {
        return id;
    }

    public String getCityId() {
        return cityId;
    }

    public String getCity() {
        return city;
    }

    public String getProvinceId() {
        return provinceId;
    }
}
