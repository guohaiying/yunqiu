package com.yunqiu.controller.permissions;

import com.yunqiu.controller.common.BaseController;
import com.yunqiu.service.ZoneService;
import com.yunqiu.util.JSONKit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 省市区
 */
@Controller
@RequestMapping("/zone")
@EnableAutoConfiguration
public class ZoneController extends BaseController {
    @Autowired
    private ZoneService zoneService;

    JSONKit json= new JSONKit();

    //查询所有省
    @ResponseBody
    @RequestMapping("/getProvinceList")
    public Map<String,Object>  getProvinceList(HttpServletResponse response) {
        Map<String, Object> map = zoneService.getProvinceList();
        return map;
    }

    //查询市
    @ResponseBody
    @RequestMapping("/getCityList")
    public Map<String,Object>  getCityList(HttpServletResponse response,String provinceId) {
        Map<String, Object> map = zoneService.getCityList(provinceId);
        return map;
    }

    //查询区/县
    @ResponseBody
    @RequestMapping("/getAreaList")
    public Map<String,Object>  getAreaList(HttpServletResponse response,String cityId) {
        Map<String, Object> map = zoneService.getAreaList(cityId);
        return map;
    }



}
