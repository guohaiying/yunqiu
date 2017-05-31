package com.yunqiu.general.wechat.service;

import com.yunqiu.general.wechat.config.Configure;
import com.yunqiu.util.GsonUtil;
import com.yunqiu.util.HttpsClientUtil;
import com.yunqiu.util.LoggerUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 微信处理API
 * @author 武尊
 * @time 2017-01-01
 * @version 1.0
 */

public class WechatUtil {

    /**
     * 获取token跟openid
     * @param code
     * @return
     */
    public static Map<String,Object> getOpenid(String code) {
        try {
            Map<String,Object> param_map = new HashMap<>();
            param_map.put("appid", Configure.appid);
            param_map.put("secret",Configure.appSecret);
            param_map.put("code",code);
            param_map.put("grant_type","authorization_code");
            String result = HttpsClientUtil.doGet(Configure.access_token,param_map);
            Map<String,Object> map = GsonUtil.stringToMap(result);
            if (map.get("openid") == null || map.get("openid") == ""){
                System.out.println("微信返回信息==="+result);
                LoggerUtil.outDebug(WechatUtil.class,"获取微信的token时发生错误，微信返回信息==="+result);
                return null;
            }
            return map;
        }catch (Exception e){
            LoggerUtil.outError(WechatUtil.class,"获取微信openid时发生错误",e);
            return null;
        }
    }

    /**
     * 获取微信用户信息
     * @param access_token
     * @param openid
     */
    public static Map<String,Object> getUserInfo(String access_token,String  openid){
        try {
            Map<String,Object> param_map = new HashMap<>();
            param_map.put("access_token",access_token);
            param_map.put("openid",openid);
            param_map.put("lang","zh_CN ");
            String result = HttpsClientUtil.doGet(Configure.userinfo,param_map);
            return GsonUtil.stringToMap(result);
        }catch (Exception e){
            LoggerUtil.outError(WechatUtil.class,"获取微信用户信息时发生错误",e);
            return null;
        }
    }
}
