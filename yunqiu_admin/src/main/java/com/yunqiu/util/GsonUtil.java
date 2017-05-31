package com.yunqiu.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.Map;

/**
 * json 工具类
 * @author 武尊
 * @time 2016-12-31
 * @version 1.0
 */
public class GsonUtil {
    private static Gson gson = new Gson();

    /**
     * json字符串转Map对象
     * @param jsonString
     * @return
     */
    public static Map<String,Object> stringToMap(String jsonString){
        try {
            return gson.fromJson(jsonString,new TypeToken<Map<String, Object>>() {}.getType());
        }catch (Exception e){
            LoggerUtil.outError(GsonUtil.class,"json字符串转Map对象时发生错误",e);
        }
        return null;
    }
}
