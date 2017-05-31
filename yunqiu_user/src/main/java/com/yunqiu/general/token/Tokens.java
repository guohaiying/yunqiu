package com.yunqiu.general.token;

import com.yunqiu.general.redis.RedisClient;
import com.yunqiu.util.ControllerReturnBase;
import com.yunqiu.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

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
     * 创建并存储token
     * @param user_id
     */
    public Map<String,Object> tokenOperate(String user_id){
        String access_token = Utils.getUUID();
        String refresh_token = Utils.getUUID();
        redisClient.setStringAndTime(user_id+"_access_token",access_token,60*30);
        redisClient.setStringAndTime(user_id+"_refresh_token",refresh_token,60*60*24*30);
        Map<String,Object> map_return = new HashMap<>();
        map_return.put("access_token",access_token);
        map_return.put("expires_in",60*30);
        map_return.put("refresh_token",refresh_token);
        map_return.put("user_id",user_id);
        return map_return;
    }

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

    /**
     * 刷新access_token
     * @return
     */
    public Map<String,Object> refreshToken(String user_id,String refresh_token){
        Map<String,Object> requst_parm = new HashMap<>();
        //判断refresh_token是否正确
        if (!refresh_token.equals(redisClient.getString(user_id+"_refresh_token"))){
            return ControllerReturnBase.errorResule(1502,"refresh_token不正确或已无效");
        }
        //判断access_token是否已失效，失效则生成新的token，否则直接刷新失效时间
        String access_token =redisClient.getString(user_id+"_access_token");
        if (access_token == null){
            access_token = Utils.getUUID();
            redisClient.setStringAndTime(user_id+"_access_token",access_token,60*30);
            requst_parm.put("access_token",access_token);
            requst_parm.put("expires_in",60*30);
            return ControllerReturnBase.successResule(requst_parm);
        }else {
            redisClient.setStringAndTime(user_id+"_access_token",access_token,60*30);
            requst_parm.put("expires_in",60*30);
            return ControllerReturnBase.successResule(requst_parm);
        }
    }

    /**
     * 注销删除token
     * @param user_id
     * @return
     */
    public boolean logout(String user_id){
        redisClient.delete(user_id+"_refresh_token");
        boolean boo = redisClient.delete(user_id+"_access_token");
        return boo;
    }
}
