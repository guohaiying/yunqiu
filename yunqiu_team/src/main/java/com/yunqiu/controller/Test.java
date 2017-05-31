package com.yunqiu.controller;

import com.yunqiu.service.TeamCloudDataService;
import com.yunqiu.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 11366 on 2017/3/10.
 */

@RestController
@RequestMapping("/test")
@EnableAutoConfiguration
public class Test {
    @Autowired
    private TestService testService;
    @Autowired
    private TeamCloudDataService teamCloudDataService;

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public boolean test(){
        try {
            testService.test();
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @RequestMapping(value = "/updatePassword",method = RequestMethod.GET)
    public boolean updatePassword(){
        try {
            testService.updatePassword();
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @RequestMapping(value = "/testCloudData",method = RequestMethod.GET)
    public boolean testCloudData(){
        System.out.println("33333333333333333333333333333");
        teamCloudDataService.computeCloudDataByGame("qWii6L1AYTpeG6FDopLKXV");
        return true;
    }
}
