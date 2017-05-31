package com.yunqiu.model;

import java.io.Serializable;


public class Province implements Serializable {

    private int id;//主键
    private String provinceId;
    private String province;

    public void setId(int id) {
        this.id = id;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public int getId() {
        return id;
    }

    public String getProvinceId() {
        return provinceId;
    }

    public String getProvince() {
        return province;
    }
}
