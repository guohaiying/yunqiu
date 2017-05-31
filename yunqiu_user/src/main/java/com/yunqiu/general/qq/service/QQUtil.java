package com.yunqiu.general.qq.service;

/**
 * Created by 11366 on 2017/1/3.
 */

import com.yunqiu.general.qq.config.QQConfigure;
import com.yunqiu.util.LoggerUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * QQ处理API
 * @author 武尊
 * @time 2017-01-01
 * @version 1.0
 */
public class QQUtil {

    /**
     * 获取qq授权token
     * @param code
     * @return
     */
    public static Map<String,Object> getToken(String code){
        try {
            Map<String,Object> parm_map = new HashMap<>();
            parm_map.put("grant_type","authorization_code");
            parm_map.put("client_id", QQConfigure.APPID);
            parm_map.put("client_secret",QQConfigure.APP_KEY);
            parm_map.put("code",code);
            return null;
        }catch (Exception e){
            LoggerUtil.outError(QQUtil.class,"获取QQ授权token时发生错误",e);
            return null;
        }
    }

    public static Map<String,Object> getUserInfo(){
        try {

            return null;
        }catch (Exception e){
            LoggerUtil.outError(QQUtil.class,"获取QQ用户信息时发生错误",e);
            return null;
        }
    }


}
