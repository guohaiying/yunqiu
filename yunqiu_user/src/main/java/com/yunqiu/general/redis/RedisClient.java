package com.yunqiu.general.redis;

import com.yunqiu.util.LoggerUtil;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * redis 操作类
 * @author 武尊
 * @time 2016-12-31
 * @version 1.0
 */
@Service
public class RedisClient {
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 根据key获取数据
     * @param key
     * @return
     */
    public String getString(String key){
        try {
            return stringRedisTemplate.opsForValue().get(key);
        }catch (Exception e){
            LoggerUtil.outError(RedisClient.class,"获取redis数据报错",e);
        }
        return null;
    }

    /**
     * 存储字符串数据，并设置有效时间
     * @param key  键
     * @param value  值
     * @param time  有效时间， 单位 秒
     * @return true 成功  false 失败
     */
    public Boolean setStringAndTime(String key,String value,long time){
        try {
            stringRedisTemplate.opsForValue().set(key,value,time, TimeUnit.SECONDS);
            return true;
        }catch (Exception e){
            LoggerUtil.outError(RedisClient.class,"存储字符串数据并设置有效时间时发生错误",e);
            return false;
        }
    }

    /**
     * 根据key删除数据
     * @param key
     * @return false 成功  true 失败
     */
    public Boolean delete(String key){
        try {
            stringRedisTemplate.delete(key);
            return false;
        }catch (Exception e){
            LoggerUtil.outError(RedisClient.class,"redis删除数据时发生错误",e);
            return true;
        }
    }
}
