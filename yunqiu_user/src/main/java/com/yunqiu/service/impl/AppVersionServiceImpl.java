package com.yunqiu.service.impl;

import com.yunqiu.dao.AppVersionMapper;
import com.yunqiu.service.AppVersionService;
import com.yunqiu.util.ControllerReturnBase;
import com.yunqiu.util.LoggerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 11366 on 2017/3/11.
 */
@Service
public class AppVersionServiceImpl implements AppVersionService {
    @Autowired
    private AppVersionMapper appVersionMapper;

    @Override
    public Map<String, Object> checkVersion(int house_version_number) {
        try {
            Map<String,Object> result = new HashMap<>();
            //获取到最新版本
            Map<String,Object> version = appVersionMapper.selectAppVersion();
            System.out.println((int)version.get("house_version_number"));
            //验证是否需要更新
            if (version == null || (int)version.get("house_version_number") <= house_version_number){
                result.put("isUpdate",false);
            }else{
                result.put("isUpdate",true);
                result.put("versionInfo",version);
            }

            return ControllerReturnBase.successResule(result);
        }catch (Exception e){
            LoggerUtil.outError(AppVersionServiceImpl.class,"验证APP版本时发生错误",e);
            return ControllerReturnBase.errorResule();
        }
    }
}
