package com.yunqiu.general.sendMsg.service.impl;

import com.yunqiu.dao.UserAuthsMapper;
import com.yunqiu.general.redis.RedisClient;
import com.yunqiu.general.sendMsg.util.SendSMS;
import com.yunqiu.general.sendMsg.service.SendSMSService;
import com.yunqiu.model.UserAuths;
import com.yunqiu.util.ControllerReturnBase;
import com.yunqiu.util.LoggerUtil;
import com.yunqiu.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 发送验证码实现类
 * @author 武尊
 * @time 2016-12-31
 * @version 1.0
 */
@Service
public class SendSMSServiceImpl implements SendSMSService {

    @Autowired
    private RedisClient redisClient;
    @Autowired
    private UserAuthsMapper authsMapper;

    /**
     * 发送验证码
     * @param type
     * @param mobile
     * @return
     */
    @Override
    public Map<String, Object> sendCode(int type, String mobile) {
        try {
            //验证参数是否合法
            if (Utils.isNull(mobile)){
                return ControllerReturnBase.errorResule(1501,"手机号码为空");
            }
            if (!Utils.isMobilephone(mobile)){
                return ControllerReturnBase.errorResule(1501,"手机号码不正确");
            }
            //根据业务，判断操作是否合法
            switch (type){
                case 1:
                    int reg_verify = this.registerVerify(mobile);
                    if (reg_verify == 1200){
                        break;
                    }else if (reg_verify == 1500){
                        return ControllerReturnBase.errorResule(1500,"系统发生错误");
                    }else{
                        return  ControllerReturnBase.errorResule(1502,"该手机号已注册，请直接登录");
                    }
                case 2:

                    break;
                case 3:

                    break;
                case 4:

                    break;
                case 5:

                    break;
                case 6:

                    break;
                default:
                    return ControllerReturnBase.errorResule(1501,"业务类型错误");
            }
            //发送验证码
            String code =Utils.getRandow(6);
            Map<String,Object> send_result = SendSMS.sendCode(type,mobile, code);
            if (send_result == null){
                LoggerUtil.outError(SendSMSServiceImpl.class,"短信发送失败,请排查",null);
                return ControllerReturnBase.errorResule(1502,"发送短信失败，请稍后重试");
            }
            //如果发送成功，则在redis中存储数据并返回成功信息,否则返回错误信息
            if ((Double)(send_result.get("code")) == 0){
                String key = mobile+"_"+type;//key 以发送的手机号+"_"+业务类型
                Boolean redis_result = redisClient.setStringAndTime(key,String.valueOf(code),60*2);
                //存储redis成功 返回成功信息，失败则返回失败信息
                if (redis_result){
                    return ControllerReturnBase.successResule();
                }else {
                    LoggerUtil.outDebug(SendSMSServiceImpl.class,"验证码存储失败");
                    return ControllerReturnBase.errorResule(1502,"发送失败，请稍后重试");
                }
            }else{
                LoggerUtil.outDebug(SendSMSServiceImpl.class,"短信验证码发送失败");
                return ControllerReturnBase.errorResule(1502,"发送短信太频繁，请1小时后再试");
            }
        }catch (Exception e){
            LoggerUtil.outError(SendSMSServiceImpl.class,"发送短信验证码服务发送错误",e);
            return ControllerReturnBase.errorResule(1500,"系统发生错误");
        }
    }

    /**
     * 注册验证
     * @param phone
     */
    private int registerVerify(String phone){
        try {
            UserAuths userAuths = authsMapper.selectUserAuthsByIdentifier(phone,1);
            if (userAuths != null){
                return 1501;
            }
            return 1200;
        }catch (Exception e){
            LoggerUtil.outError(SendSMSServiceImpl.class,"发送验证码注册验证发生错误",e);
            return 1500;
        }
    }
}
