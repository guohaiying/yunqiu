package com.yunqiu.service.impl;

import com.yunqiu.dao.RegionMapper;
import com.yunqiu.model.Area;
import com.yunqiu.model.City;
import com.yunqiu.model.Province;
import com.yunqiu.service.RegionService;
import com.yunqiu.util.ControllerReturnBase;
import com.yunqiu.util.LoggerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 省、市、区/县
 */

@Service
public class RegionServiceImpl implements RegionService {
    @Autowired
    private RegionMapper regionMapper;

    /**
     * 查询省
     * @return
     */
    @Override
    public Map<String, Object> selectProvince() {
        try {
            List<Province> provinceList = regionMapper.selectProvince();
            return ControllerReturnBase.successResule(provinceList);
        }catch (Exception e){
            LoggerUtil.outError(RegionServiceImpl.class,"查询省份时错误",e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 查询市
     * @param province_id
     * @return
     */
    @Override
    public Map<String, Object> selectCity(String province_id) {
        try {
            List<City> cityList = regionMapper.selectCity(province_id);
            return ControllerReturnBase.successResule(cityList);
        }catch (Exception e){
            LoggerUtil.outError(RegionServiceImpl.class,"查询市时错误",e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 查询县
     * @param city_id
     * @return
     */
    @Override
    public Map<String, Object> selectArea(String city_id) {
        try {
            List<Area> areaList = regionMapper.selectArea(city_id);
            return ControllerReturnBase.successResule(areaList);
        }catch (Exception e){
            LoggerUtil.outError(RegionServiceImpl.class,"查询区/县时错误",e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 查询场地
     * @return
     */
    @Override
    public Map<String, Object> selectPlace() {
        try {
            List<String> plactList = regionMapper.selectPlace();
            return ControllerReturnBase.successResule(plactList);
        }catch (Exception e){
            LoggerUtil.outError(RegionServiceImpl.class,"查询场地shi时发生错误",e);
            return ControllerReturnBase.errorResule();
     }
    }
}
