package com.yunqiu.service.impl;

import com.yunqiu.dao.UserAuthsMapper;
import com.yunqiu.dao.UsersMapper;
import com.yunqiu.model.UserAuths;
import com.yunqiu.service.RegisterService;
import com.yunqiu.util.LoggerUtil;
import com.yunqiu.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 注册服务
 * @author 武尊
 * @time 2017-01-03
 * @version 1.0
 */
@Service
public class RegisterServiceImpl implements RegisterService {
    @Autowired
    private UsersMapper usersMapper;
    @Autowired
    private UserAuthsMapper authsMapper;

    /**
     * 注册账户
     * @param phone  账户 手机号/openid
     * @param password 密码/null
     * @param ip
     * @param type 账户类型 1 手机号 2 微信 3 QQ
     * @param userid
     * @return
     */
    @Override
    public int register(String phone, String password, String ip, int type,String userid) {
        try {
            UserAuths userAuths = new UserAuths();
            userAuths.setAuth_id(Utils.getUUID());
            userAuths.setUser_id(userid);
            userAuths.setRegistration_time(new Date());
            userAuths.setLogin_time(new Date());
            userAuths.setRegistration_ip(ip);
            userAuths.setIdentity_type(type);
            userAuths.setIdentifier(phone);
            if (!Utils.isNull(password) && type == 1){
                userAuths.setCredential(Utils.SHAEncrypt(password));
            }
            authsMapper.insertUserAuths(userAuths);
            return 1;
        }catch (Exception e){
            LoggerUtil.outError(RegisterServiceImpl.class,"用户注册Service发生错误",e);
            return 0;
        }
    }
}
