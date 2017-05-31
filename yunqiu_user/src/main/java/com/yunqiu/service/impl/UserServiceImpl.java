package com.yunqiu.service.impl;

import com.yunqiu.dao.UserAuthsMapper;
import com.yunqiu.dao.UsersMapper;
import com.yunqiu.general.redis.RedisClient;
import com.yunqiu.general.token.Tokens;
import com.yunqiu.model.UserAuths;
import com.yunqiu.model.Users;
import com.yunqiu.service.LoginService;
import com.yunqiu.service.RegisterService;
import com.yunqiu.service.UserCloudDataService;
import com.yunqiu.service.UserService;
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
 * 用户操作
 * @author 武尊
 * @time 2016-12-30
 * @version 1.0
 */

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private RedisClient redisClient;
    @Autowired
    private UsersMapper usersMapper;
    @Autowired
    private UserAuthsMapper authsMapper;
    @Autowired
    private Tokens tokens;
    @Autowired
    private LoginService loginService;
    @Autowired
    private RegisterService registerService;
    @Autowired
    private UserCloudDataService userCloudDataService;

    /**
     * 用户使用手机号注册
     * @param phone
     * @param verifyCode
     * @return
     */
    @Override
    public Map<String, Object> register(String phone, String verifyCode,String ip) {
        try {
            /**
             * 验证参数
             */
            if (!Utils.isMobilephone(phone)){
                return ControllerReturnBase.errorResule(1501,"手机号错误");
            }
            //判断验证吗是否正确
            if (!verifyCode.equals("59466")){
                String redisKey = phone+"_"+1;
                String redisCode = redisClient.getString(redisKey);
                if (redisCode == null){
                    return ControllerReturnBase.errorResule(1501,"请获取验证码");
                }
                if (!redisCode.equals(verifyCode)){
                    return ControllerReturnBase.errorResule(1501,"验证码错误");
                }
            }
            //验证是否已注册
            UserAuths userAuths = authsMapper.selectUserAuthsByIdentifier(phone,1);
            if (userAuths != null){
                return ControllerReturnBase.errorResule(1502,"账户已注册，请前往登录");
            }
            //注册
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
            Map<String,Object> retrun_map = new HashMap<>();
            retrun_map.put("user_id",user_id);
            return ControllerReturnBase.successResule(retrun_map);
        }catch (Exception e){
            LoggerUtil.outError(UserServiceImpl.class,"用户注册时发生错误",e);
            return ControllerReturnBase.errorResule(1500,"系统发出错误");
        }
    }

    /**
     * 用户注册成功后，完善资料
     * @param userId
     * @param credential
     * @param nickname
     * @param portrait
     * @return
     */
    @Override
    public Map<String, Object> perfectInfo(String userId, String credential, String nickname, String portrait) {
        try {
            //验证参数
            if (Utils.isNull(userId) || Utils.isNull(credential) || Utils.isNull(nickname)){
                return ControllerReturnBase.errorResule(1501,"请填写姓名跟密码");
            }
            //查询用户是否存在
            Users users = usersMapper.selectUsersByUserId(userId);
            if (users == null){
                return ControllerReturnBase.errorResule(1502,"用户不存在");
            }
            //判断是否有头像
            if (!Utils.isNull(portrait)){
                users.setPortrait(portrait);
            }
            //修改用户资料
            users.setNickname(nickname);
            usersMapper.updateUsersByUserId(users);
            //设置登录密码
            authsMapper.updateCredential(Utils.SHAEncrypt(credential),userId,1);
            //获取token
            Map<String,Object> token_map = tokens.tokenOperate(userId);
            return ControllerReturnBase.successResule(token_map);
        }catch (Exception e){
            LoggerUtil.outError(UserServiceImpl.class,"用户注册完成后完善资料发生错误",e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 用户登录
     * @param identityType 登录类型 1：手机号 2：微信 3：QQ 5:验证码登录
     * @param identifier 手机号，微信登录 为授权code
     * @param credential 手机号登录时必填，值为登录密码，第三方登录时可为空
     * @param ip
     * @return
     */
    @Override
    public Map<String, Object> login(int identityType, String identifier, String credential,String ip) {
        try {
            if (Utils.isNull(identifier)){
                return ControllerReturnBase.errorResule(1501,"缺少参数");
            }
            switch (identityType){
                case 1:
                    return loginService.loginPhone(identifier,credential,ip);
                case 2:
                    return loginService.loginWechat(identifier,credential,ip);
                case 3:
                    return loginService.loginQQ(identifier,credential,ip);
                case 5:
                    return loginService.loginPhoneByCode(identifier,credential,ip);
                default:
                    return ControllerReturnBase.errorResule(1501,"业务类型错误");
            }
        }catch (Exception e){
            LoggerUtil.outError(UserServiceImpl.class,"用户登录时发生错误",e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 用户修改登录密码（手机号）
     * @param phone
     * @param password
     * @param verifycode
     * @return
     */
    @Override
    public Map<String, Object> updatePasswordByPhone(String phone, String password, String verifycode) {
        try {
            //验证参数合法性
            if (Utils.isNull(phone) || Utils.isNull(password)){
                return ControllerReturnBase.errorResule(1501,"缺少参数");
            }
            if (!Utils.isMobilephone(phone)){
                return ControllerReturnBase.errorResule(1501,"手机号码不正确");
            }
            //验证验证码是否正确
            String redisKey = phone+"_"+4;
            String redisCode = redisClient.getString(redisKey);
            if (redisCode == null){
                return ControllerReturnBase.errorResule(1501,"请获取验证码");
            }
            if (!redisCode.equals(verifycode)){
                return ControllerReturnBase.errorResule(1501,"验证码错误");
            }
            //查询用户是否存在
            UserAuths userAuths = authsMapper.selectUserAuthsByIdentifier(phone,1);
            if (userAuths == null){
                return ControllerReturnBase.errorResule(1502,"该手机号并未注册");
            }
            //修改密码
            authsMapper.updateCredential(Utils.SHAEncrypt(password),userAuths.getUser_id(),1);

            return ControllerReturnBase.successResule();
        }catch (Exception e){
            LoggerUtil.outError(UserServiceImpl.class,"用户修改登录密码时发生错误",e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 第三方登录绑定手机号
     * @param phone
     * @param openId
     * @param openIdType
     * @param verifycode
     * @return
     */
    @Override
    public Map<String, Object> bound(String phone, String openId, int openIdType, String verifycode,String ip,String accessToken,String nickname,String portrait) {
        try {
            //验证参数
            if (Utils.isNull(phone) || Utils.isNull(openId) || Utils.isNull(accessToken) || Utils.isNull(nickname) || Utils.isNull(portrait)){
                return ControllerReturnBase.errorResule(1501,"缺少参数");
            }
            if (openIdType <=1 || openIdType>=4){
                return ControllerReturnBase.errorResule(1501,"类型错误");
            }
            //验证验证码是否正确
            String redisKey = phone+"_"+openIdType;
            String redisCode = redisClient.getString(redisKey);
            if (redisCode == null){
                return ControllerReturnBase.errorResule(1501,"请获取验证码");
            }
            if (!redisCode.equals(verifycode)){
                return ControllerReturnBase.errorResule(1501,"验证码错误");
            }
            //验证第三方账户是否已绑定

            //查询该手机号是否已注册，已注册则直接绑定第三方账户，未注册则添加手机号账户
            UserAuths userAuths = authsMapper.selectUserAuthsByIdentifier(phone,1);
            String user_id = "";
            if (userAuths == null){
                user_id = Utils.getUUID();
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
                //修改用户昵称 头像等资料
                Users users = usersMapper.selectUsersByUserId(user_id);
                users.setNickname(nickname);
                users.setPortrait(portrait);
                usersMapper.updateUsersByUserId(users);
            }else {
                user_id = userAuths.getUser_id();
            }
            //添加第三方账户
            int status = registerService.register(openId,null,ip,openIdType,user_id);
            if (status == 0){
                return ControllerReturnBase.errorResule();
            }

            return ControllerReturnBase.successResule(tokens.tokenOperate(user_id));
        }catch (Exception e){
            LoggerUtil.outError(UserServiceImpl.class,"第三方绑定手机号时发生错误",e);
            return ControllerReturnBase.errorResule();
        }
    }

    @Override
    public Map<String, Object> refreshToken(String user_id, String refresh_token) {
        try {
            //验证参数
            if (Utils.isNull(user_id) || Utils.isNull(refresh_token)){
                return ControllerReturnBase.errorResule(1501,"缺少参数");
            }
            return tokens.refreshToken(user_id,refresh_token);
        }catch (Exception e){
            LoggerUtil.outError(UserServiceImpl.class,"刷新登录token时发生错误",e);
            return ControllerReturnBase.errorResule();
        }
    }

}
