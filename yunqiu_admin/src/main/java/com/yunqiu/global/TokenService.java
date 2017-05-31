package com.yunqiu.global;

import com.yunqiu.util.LoggerUtil;
import com.yunqiu.util.Utils;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * token管理维护
 * @author 1.0
 * @time 2017-01-13
 * @version 1.0
 */
@Service
public class TokenService {
    //toekn存储
    private Map<String,String> tokenMap = new HashMap<>();
    //定时器存储
    private Map<String,Timer> timerMap = new HashMap<>();

    /**
     * 管理员登录时，生成token，并指定失效时间
     * @param userId
     * @return
     */
    public String storageToken(String userId){
        try {
            String token = Utils.getID(22);
            Timer timer = new Timer(true);
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    timerMap.get(userId).cancel();
                    tokenMap.remove(userId);
                    timerMap.remove(userId);
                    System.out.println("定时执行,控制token失效=="+new Date()+"===="+token);
                }
            };
            timer.schedule(task,60*1000*30);
            tokenMap.put(userId,token);
            timerMap.put(userId,timer);
            return token;
        }catch (Exception e){
            LoggerUtil.outError(TokenService.class,"创建并存储token时发生错误",e);
            return null;
        }
    }

    /**
     * 验证用户是否已登录
     * @param userId
     * @return  true 已登录  false：未登录
     */
    public boolean validationLogin(String userId){
        try {
            if (Utils.isNull(tokenMap.get(userId))){
                return false;
            }
            return true;
        }catch (Exception e){
            LoggerUtil.outError(TokenService.class,"验证token是否存在时发生错误",e);
            return false;
        }
    }

    /**
     * 更新token失效时间
     * @param userId
     */
    public void updateTokenTime(String userId){
        try {
            Timer timer = timerMap.get(userId);
            timer.cancel();
            timer = new Timer(true);
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    timerMap.get(userId).cancel();
                    tokenMap.remove(userId);
                    timerMap.remove(userId);
                    System.out.println("定时执行,更新token失效时间=="+new Date());
                }
            };
            timer.schedule(task,60*1000*30);
            timerMap.put(userId,timer);
        }catch (Exception e){
            LoggerUtil.outError(TokenService.class,"更新token失效时间时发生错误",e);
        }
    }

    /**
     * 验证token是否正确并更新token失效时间
     * @param token
     * @param userId
     * @return
     */
    public boolean validationToken(String token,String userId){
        try {
            if (!token.equals(tokenMap.get(userId))){
                return false;
            }else {
                updateTokenTime(userId);
                return true;
            }
        }catch (Exception e){
            LoggerUtil.outError(TokenService.class,"验证token是否正确",e);
            return false;
        }
    }
}
