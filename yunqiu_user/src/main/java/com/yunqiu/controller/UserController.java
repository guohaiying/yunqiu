package com.yunqiu.controller;

import com.yunqiu.service.UserService;
import com.yunqiu.util.ControllerReturnBase;
import com.yunqiu.util.LoggerUtil;
import com.yunqiu.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 用户管理
 *
 * @author 武尊
 * @version 1.0
 * @time 2016-12-31
 */

@RestController
@RequestMapping("/user")
@EnableAutoConfiguration
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 用户使用手机号注册接口
     *
     * @param phone
     * @param verifyCode
     * @param request
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Map<String, Object> register(@RequestParam("phone") String phone, @RequestParam("verifyCode") String verifyCode, HttpServletRequest request) {
        try {
            return userService.register(phone, verifyCode, Utils.getIpAddress(request));
        } catch (Exception e) {
            LoggerUtil.outError(UserController.class, "用户注册时发生错误", e);
            return ControllerReturnBase.errorResule(1500, "系统发生错误");
        }
    }

    /**
     * 注册完成后，填写资料
     *
     * @return
     */
    @RequestMapping(value = "/perfectInfo", method = RequestMethod.POST)
    public Map<String, Object> perfectInfo(@RequestParam("user_id") String userId, @RequestParam("credential") String credential,
                                           @RequestParam("nickname") String nickname, @RequestParam("portrait") String portrait) {
        try {
            return userService.perfectInfo(userId, credential, nickname, portrait);
        } catch (Exception e) {
            LoggerUtil.outError(UserController.class, "用户注册完成完善资料时发生错误", e);
            return ControllerReturnBase.errorResule();
        }
    }


    /**
     * 用户登录
     *
     * @param identityType
     * @param identifier
     * @param credential
     * @return
     */
    @RequestMapping(value = "/token", method = RequestMethod.GET)
    public Map<String, Object> login(@RequestParam("identity_type") int identityType, @RequestParam("identifier") String identifier, @RequestParam("credential") String credential,HttpServletRequest request) {
        try {
            return userService.login(identityType, identifier, credential,Utils.getIpAddress(request));
        } catch (Exception e) {
            LoggerUtil.outError(UserController.class, "用户登录时发生错误", e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 忘记密码
     *
     * @param phone
     * @param verifycode
     * @param password
     * @return
     */
    @RequestMapping(value = "/updatePasswordByPhone", method = RequestMethod.POST)
    public Map<String, Object> updatePasswordByPhone(@RequestParam("phone") String phone, @RequestParam("verifycode") String verifycode, @RequestParam("password") String password) {
        try {
            return userService.updatePasswordByPhone(phone, password, verifycode);
        } catch (Exception e) {
            LoggerUtil.outError(UserController.class, "用户修改手机号登录密码时发生错误", e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 第三方登录绑定手机号接口
     *
     * @param phone
     * @param openId
     * @param openIdType
     * @param verifycode
     * @param request
     * @return
     */
    @RequestMapping(value = "/bound", method = RequestMethod.POST)
    public Map<String, Object> bound(@RequestParam("phone") String phone, @RequestParam("openId") String openId,
                                     @RequestParam("openIdType") int openIdType, @RequestParam("verifycode") String verifycode,
                                     @RequestParam("accessToken") String accessToken,@RequestParam("nickname") String nickname,@RequestParam("portrait") String portrait,
                                     HttpServletRequest request) {
        try {
            return userService.bound(phone, openId, openIdType, verifycode, Utils.getIpAddress(request), accessToken,nickname,portrait);
        } catch (Exception e) {
            LoggerUtil.outError(UserController.class, "用户使用第三方账户登录时绑定手机号发生错误", e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 刷新access_token失效时间
     * @param user_id
     * @param refresh_token
     * @return
     */
    @RequestMapping(value = "/refreshToken",method = RequestMethod.GET)
    public Map<String,Object> refreshToken(@RequestParam("user_id") String user_id,@RequestParam("refresh_token") String refresh_token){
        try {
            return userService.refreshToken(user_id,refresh_token);
        }catch (Exception e){
            LoggerUtil.outError(UserController.class, "刷新登录token时发生错误", e);
            return ControllerReturnBase.errorResule();
        }
    }
}
