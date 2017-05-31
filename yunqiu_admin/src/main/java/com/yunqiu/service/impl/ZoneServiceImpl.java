package com.yunqiu.service.impl;

import com.yunqiu.dao.ZoneMapper;
import com.yunqiu.model.Area;
import com.yunqiu.model.City;
import com.yunqiu.model.Province;
import com.yunqiu.service.ZoneService;
import com.yunqiu.util.ControllerReturnBase;
import com.yunqiu.util.LoggerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ZoneServiceImpl implements ZoneService {
    @Autowired
    private ZoneMapper zoneMapper;

    /**
     * 查询所有省
     * @return
     */
    @Override
    public Map<String, Object> getProvinceList() {
        try {
            List<Province> map = zoneMapper.selecteAllProvince();
            return ControllerReturnBase.successResule(map);
        } catch (Exception e) {
            LoggerUtil.outError(ZoneService.class, "查询省时发生错误", e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 查询市
     * @return
     */
    @Override
    public Map<String, Object> getCityList(String provinceId) {
        try {
            List<City> map = zoneMapper.getCityList(provinceId);
            return ControllerReturnBase.successResule(map);
        } catch (Exception e) {
            LoggerUtil.outError(ZoneService.class, "查询市时发生错误", e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 查询区/县
     * @return
     */
    @Override
    public Map<String, Object> getAreaList(String cityId) {
        try {
            List<Area> map = zoneMapper.getAreaList(cityId);
            return ControllerReturnBase.successResule(map);
        } catch (Exception e) {
            LoggerUtil.outError(ZoneService.class, "查询区/县时发生错误", e);
            return ControllerReturnBase.errorResule();
        }
    }

}
