package com.yunqiu.model;

import java.io.Serializable;

/**
 * 省份数据
 */

public class Province implements Serializable {
    private int id;
    private String province_id;
    private String province;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProvince_id() {
        return province_id;
    }

    public void setProvince_id(String province_id) {
        this.province_id = province_id;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }
}
