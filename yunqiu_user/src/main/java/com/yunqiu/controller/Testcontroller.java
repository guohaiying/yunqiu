package com.yunqiu.controller;


import com.yunqiu.dao.UserAuthsMapper;
import com.yunqiu.dao.UsersMapper;
import com.yunqiu.general.redis.RedisClient;
import com.yunqiu.util.LoggerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 11366 on 2016/12/30.
 */

@RestController
@EnableAutoConfiguration
public class Testcontroller {
    @Autowired
    private UsersMapper usersMapper;

    @Autowired
    private RedisClient redisClient;

    @Autowired
    private UserAuthsMapper userAuthsMapper;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @RequestMapping(value = "/index/{msg}",method = RequestMethod.GET)
    public Map<String,String> index(@PathVariable String msg) throws Exception {
        LoggerUtil.outDebug(Testcontroller.class,"dkfjkdsjf");
        Map<String,String> map = new HashMap<>();
        map.put("key",msg);
        return map;
    }
}
