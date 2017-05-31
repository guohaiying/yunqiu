package com.yunqiu.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 通用信息返回包装类
 * @author 武尊
 * @time 2016-12-31
 * @version 1.0
 */
public class ControllerReturnBase {

    /**
     * 通用请求成功提示信息
     * @return
     */
    public static Map<String,Object> successResule(){
        Map<String,Object> map = new HashMap<>();
        map.put("error_code",1200);
        map.put("msg","OK");
        return map;
    }

    /**
     * 通用请求成功提示信息
     * @return
     */
    public static Map<String,Object> successResule(Object data){
        Map<String,Object> map = new HashMap<>();
        map.put("error_code",1200);
        map.put("msg","OK");
        map.put("data",data);
        return map;
    }

    /**
     * 通用请求成功，但是业务错误提示
     * @return
     */
    public static Map<String,Object> errorResule(){
        Map<String,Object> map = new HashMap<>();
        map.put("error_code",1500);
        map.put("msg","系统发生错误");
        return map;
    }

    /**
     * 通用请求成功，但是业务错误提示
     * @param code
     * @param msg
     * @return
     */
    public static Map<String,Object> errorResule(int code,String msg){
        Map<String,Object> map = new HashMap<>();
        map.put("error_code",code);
        map.put("msg",msg);
        return map;
    }
}
