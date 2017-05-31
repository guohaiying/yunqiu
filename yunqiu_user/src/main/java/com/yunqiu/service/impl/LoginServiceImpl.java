package com.yunqiu.service.impl;

import com.yunqiu.dao.UserAuthsMapper;
import com.yunqiu.dao.UsersMapper;
import com.yunqiu.general.redis.RedisClient;
import com.yunqiu.general.token.Tokens;
import com.yunqiu.model.UserAuths;
import com.yunqiu.service.LoginService;
import com.yunqiu.service.RegisterService;
import com.yunqiu.service.UserCloudDataService;
import com.yunqiu.util.ControllerReturnBase;
import com.yunqiu.util.DateUtil;
import com.yunqiu.util.LoggerUtil;
import com.yunqiu.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 登录业务
 * @author 武尊
 * @time 2016-12-31
 * @version 1.0
 */
@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private UserAuthsMapper authsMapper;
    @Autowired
    private UsersMapper usersMapper;
    @Autowired
    private Tokens tokens;
    @Autowired
    private RedisClient redisClient;
    @Autowired
    private RegisterService registerService;
    @Autowired
    private UserCloudDataService userCloudDataService;

    /**
     * 手机号登录
     * @param phone
     * @param passwod
     * @return
     */
    @Override
    public Map<String, Object> loginPhone(String phone, String passwod,String ip) {
        try {
            //验证手机格式
            if (!Utils.isMobilephone(phone)){
                return ControllerReturnBase.errorResule(1501,"手机号码不正确");
            }
            //查询用户
            UserAuths userAuths = authsMapper.selectUserAuthsByIdentifier(phone,1);
            if (userAuths == null){
                return ControllerReturnBase.errorResule(1502,"用户不存在，请前往注册");
            }
            //判断密码是否正确
            if (!userAuths.getCredential().equals(Utils.SHAEncrypt(passwod))){
                return ControllerReturnBase.errorResule(1502,"密码错误");
            }
            //获取token
            Map<String,Object> map = tokens.tokenOperate(userAuths.getUser_id());
            return ControllerReturnBase.successResule(map);
        }catch (Exception e){
            LoggerUtil.outError(LoginServiceImpl.class,"手机号登录时发生错误",e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * QQ登录
     * @param qq_openid
     * @param qq_token
     * @return
     */
    @Override
    public Map<String, Object> loginQQ(String qq_openid, String qq_token,String ip) {
        try {
            //查询用户,如果为空，则返回信息要求绑定手机号
            UserAuths userAuths = authsMapper.selectUserAuthsByIdentifier(qq_openid,3);
            if (userAuths == null){
                Map<String,Object> map = new HashMap<>();
                map.put("bound_status",2);
                map.put("openid",qq_openid);
                return ControllerReturnBase.successResule(map);
            }
            //验证第三方的token是否有效后期实现

            //验证第三方的token是否有效后期实现end
            //获取token
            Map<String,Object> token_map = tokens.tokenOperate(userAuths.getUser_id());
            return ControllerReturnBase.successResule(token_map);
        }catch (Exception e){
            LoggerUtil.outError(LoginServiceImpl.class,"QQ号登录时发生错误",e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 微信登录
     * @param wechat_openid
     * @param credential
     * @return
     */
    @Override
    public Map<String, Object> loginWechat(String wechat_openid,String credential,String ip) {
        try {
            //根据openid查询是否已绑定手机号，没有返回要求变更的手机号
            UserAuths userAuths = authsMapper.selectUserAuthsByIdentifier(wechat_openid,2);
            if (userAuths == null){
                Map<String,Object> map;
                map = new HashMap<>();
                map.put("bound_status",2);
                map.put("openid",wechat_openid);
                return ControllerReturnBase.successResule(map);
            }
            //验证第三方的token是否有效后期实现

            //验证第三方的token是否有效后期实现end
            return ControllerReturnBase.successResule(tokens.tokenOperate(userAuths.getUser_id()));
        }catch (Exception e){
            LoggerUtil.outError(LoginServiceImpl.class,"微信登录时发生错误",e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 使用验证码登录
     * @param phone
     * @param verifycode
     * @return
     */
    @Override
    public Map<String, Object> loginPhoneByCode(String phone, String verifycode,String ip) {
        try {
            //验证手机格式
            if (!Utils.isMobilephone(phone)){
                return ControllerReturnBase.errorResule(1501,"手机号码不正确");
            }
            //验证验证码是否正确
            String redisKey = phone+"_"+5;
            String redisCode = redisClient.getString(redisKey);
            if (redisCode == null){
                return ControllerReturnBase.errorResule(1501,"验证码不存在");
            }
            if (!redisCode.equals(verifycode)){
                return ControllerReturnBase.errorResule(1501,"验证码错误");
            }
            //查询用户是否存在
            UserAuths userAuths = authsMapper.selectUserAuthsByIdentifier(phone,1);
            if (userAuths == null){
                //注册该用户
                String user_id = Utils.getUUID();
                //初始化用户基本资料
                Date birthday = DateUtil.getDateTime("yyyy-MM-dd");
                usersMapper.insertUsers(user_id,birthday,1,new Date());
                //初始化云五数据
                userCloudDataService.initialization(user_id);
                //添加账户
                int status = registerService.register(phone,null,ip,1,user_id);
                if (status == 0){
                    return ControllerReturnBase.errorResule();
                }
            }
            return ControllerReturnBase.successResule(tokens.tokenOperate(userAuths.getUser_id()));
        }catch (Exception e){
            LoggerUtil.outError(LoginServiceImpl.class,"验证码登录时发生错误",e);
            return ControllerReturnBase.errorResule();
        }
    }
}
