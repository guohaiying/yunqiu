package com.yunqiu.controller;


import com.yunqiu.general.sendMsg.service.SendSMSService;
import com.yunqiu.util.LoggerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 发送短信验证码
 * @author 武尊
 * @time 2016-12-31
 * @version 1.0
 */

@RestController
@RequestMapping("/general")
@EnableAutoConfiguration
public class SendSMSController {
    @Autowired
    private SendSMSService sendSMSService;

    @RequestMapping(value = "/getVerifycode",method = RequestMethod.POST)
    public Map<String,Object> sendCode(@RequestParam("type") int type, @RequestParam("phone") String phone){
        try {
            return sendSMSService.sendCode(type,phone);
        }catch (Exception e){
            LoggerUtil.outError(SendSMSController.class,"发送短信验证码控制器发送错误",e);
            return null;
        }
    }
}
