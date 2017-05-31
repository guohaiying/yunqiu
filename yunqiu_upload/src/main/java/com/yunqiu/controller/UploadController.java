package com.yunqiu.controller;

import com.yunqiu.service.UploadService;
import com.yunqiu.util.ControllerReturnBase;
import com.yunqiu.util.LoggerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * Created by 11366 on 2017/1/2.
 */

@RestController
@RequestMapping("/uploads")
@EnableAutoConfiguration
public class UploadController {
    @Autowired
    private UploadService uploadService;

    /**
     * 单图上传
     * @param file
     * @return
     */
    @RequestMapping(value = "/single",method = RequestMethod.POST)
    public Map<String,Object> single(MultipartFile file){
        try {
            return uploadService.single(file);
        }catch (Exception e){
            LoggerUtil.outError(UploadController.class,"单图上传控制器发生错误",e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 多图上传
     * @param file
     * @return
     */
    @RequestMapping(value = "/more",method = RequestMethod.POST)
    public Map<String,Object> more(MultipartFile[] file){
        try {
            return uploadService.more(file);
        }catch (Exception e){
            LoggerUtil.outError(UploadController.class,"多图上传控制器发生错误",e);
            return ControllerReturnBase.errorResule();
        }
    }
}
