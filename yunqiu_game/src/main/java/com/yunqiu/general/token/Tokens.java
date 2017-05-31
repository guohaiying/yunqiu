package com.yunqiu.general.token;

import com.yunqiu.general.redis.RedisClient;
import com.yunqiu.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Token 管理
 * @author 武尊
 * @time 2016-12-31
 * @version 1.0
 */

@Service
public class Tokens {
    @Autowired
    private RedisClient redisClient;

    /**
     * 验证token并更新token失效时间
     * @param user_id
     * @param access_token
     * @return true 不通过  false 通过
     */
    public boolean verifyToken(String user_id,String access_token){
        String toekn = redisClient.getString(user_id+"_access_token");
        if (Utils.isNull(toekn)){
            //token已过时
            return true;
        }else if (!toekn.equals(access_token)){
            //token不正确
            return true;
        }else {
            //正确，更新token失效时间
            redisClient.setStringAndTime(user_id+"_access_token",access_token,60*30);
            return false;
        }
    }
}
