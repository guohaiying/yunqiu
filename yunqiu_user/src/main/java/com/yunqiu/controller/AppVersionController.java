package com.yunqiu.controller;

import com.yunqiu.service.AppVersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * App版本查询
 */

@RestController
@RequestMapping("/version")
@EnableAutoConfiguration
public class AppVersionController {
    @Autowired
    private AppVersionService appVersionService;

    /**
     * 校验版本号
     * @param house_version_number
     * @return
     */
    @RequestMapping(value = "/v1/checkVersion",method = RequestMethod.GET)
    public Map<String,Object> checkVersion(@RequestParam("house_version_number") int house_version_number){
        return appVersionService.checkVersion(house_version_number);
    }
}
