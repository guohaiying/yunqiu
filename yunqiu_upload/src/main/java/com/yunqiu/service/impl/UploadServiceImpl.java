package com.yunqiu.service.impl;

import com.yunqiu.controller.UploadController;
import com.yunqiu.service.UploadService;
import com.yunqiu.util.ControllerReturnBase;
import com.yunqiu.util.LoggerUtil;
import com.yunqiu.util.UploadImgs;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 11366 on 2017/1/2.
 */

@Service
public class UploadServiceImpl implements UploadService {
    @Override
    public Map<String, Object> single(MultipartFile file) {
        try {
            if (file.isEmpty()){
                return ControllerReturnBase.errorResule(1501,"图片不存在");
            }
            String url = UploadImgs.upload(file);
            if (url == null){
                return ControllerReturnBase.errorResule();
            }
            Map<String,Object> map = new HashMap<>();
            map.put("url",url);
            return ControllerReturnBase.successResule(map);
        }catch (Exception e){
            LoggerUtil.outError(UploadController.class,"单图上传Service发生错误",e);
            return ControllerReturnBase.errorResule();
        }
    }

    @Override
    public Map<String, Object> more(MultipartFile[] files_list) {
        try {
            if (files_list.length == 0){
                return ControllerReturnBase.errorResule(1501,"图片不存在");
            }
            List<String> url_list = new ArrayList<>();
            for (int item = 0;item < files_list.length;item++){
                String url = UploadImgs.upload(files_list[item]);
                url_list.add(url);
            }
            Map<String,Object> map = new HashMap<>();
            map.put("url",url_list);
            return ControllerReturnBase.successResule(map);
        }catch (Exception e){
            LoggerUtil.outError(UploadController.class,"多图上传Service发生错误",e);
            return ControllerReturnBase.errorResule();
        }
    }
}
