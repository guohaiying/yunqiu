package com.yunqiu.controller;

import com.yunqiu.service.RegionService;
import com.yunqiu.util.ControllerReturnBase;
import com.yunqiu.util.LoggerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 省、市、区/县
 */

@RestController
@RequestMapping("/system")
@EnableAutoConfiguration
public class RegionController {
    @Autowired
    private RegionService regionService;

    /**
     * 查询省
     * @return
     */
    @RequestMapping(value = "/selectProvince",method = RequestMethod.GET)
    @Cacheable(value="province")
    public Map<String, Object> selectProvince() {
        try {
            return regionService.selectProvince();
        }catch (Exception e){
            LoggerUtil.outError(RegionController.class,"查询省份时错误",e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 查询市
     * @param province_id
     * @return
     */
    @RequestMapping(value = "/selectCity",method = RequestMethod.GET)
    @Cacheable(value="city")
    public Map<String, Object> selectCity(@RequestParam("province_id") String province_id) {
        try {
            return regionService.selectCity(province_id);
        }catch (Exception e){
            LoggerUtil.outError(RegionController.class,"查询市时错误",e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 查询县
     * @param city_id
     * @return
     */
    @RequestMapping(value = "/selectArea",method = RequestMethod.GET)
    @Cacheable(value="area")
    public Map<String, Object> selectArea(@RequestParam("city_id") String city_id) {
        try {
            return regionService.selectArea(city_id);
        }catch (Exception e){
            LoggerUtil.outError(RegionController.class,"查询区/县时错误",e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 查询场地
     * @return
     */
    @RequestMapping(value = "/selectPlace",method = RequestMethod.GET)
    public Map<String,Object> selectPlace(){
        return regionService.selectPlace();
    }
}
