package com.yunqiu.service;

import java.util.Map;

/**
 * Created by 11366 on 2017/1/14.
 */
public interface ZoneService {

    Map<String, Object> getProvinceList();

    Map<String, Object> getCityList(String provinceId);

    Map<String, Object> getAreaList(String cityId);
}
