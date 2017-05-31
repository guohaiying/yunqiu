package com.yunqiu.service;

import java.util.Map;

/**
 * 省、市、区/县
 */

public interface RegionService {
    Map<String,Object> selectProvince();
    Map<String,Object> selectCity(String province_id);
    Map<String,Object> selectArea(String city_id);
    Map<String,Object> selectPlace();
}
