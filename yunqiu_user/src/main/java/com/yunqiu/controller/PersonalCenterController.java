package com.yunqiu.controller;

import com.yunqiu.model.Users;
import com.yunqiu.service.PersonalCenterService;
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
 * 个人中心相关接口
 */

@RestController
@RequestMapping("/center")
@EnableAutoConfiguration
public class PersonalCenterController {
    @Autowired
    private PersonalCenterService personalCenterService;
    /**
     * 修改个人资料
     * @param users
     * @return
     */
    @RequestMapping(value = "/inte/modifyInfo",method = RequestMethod.POST)
    public Map<String,Object> modifyInfo(Users users){
        try {
            return personalCenterService.modifyInfo(users);
        }catch (Exception e){
            LoggerUtil.outError(PersonalCenterController.class, "用户修改个人资料时发生错误", e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 修改密码
     * @param worn_password
     * @param new_password
     * @param request
     * @return
     */
    @RequestMapping(value = "/inte/updatePassword",method = RequestMethod.POST)
    public Map<String,Object> updatePassword(@RequestParam("worn_password") String worn_password,@RequestParam("type") int type,
                                             @RequestParam("new_password") String new_password,HttpServletRequest request){
        try {
            String user_id = request.getHeader("user_id");
            return personalCenterService.updatePassword(new_password,worn_password,user_id,type);
        }catch (Exception e){
            LoggerUtil.outError(PersonalCenterController.class, "用户密码时发生错误", e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 查询绑定的账户
     * @param request
     * @return
     */
    @RequestMapping(value = "/inte/selectBound",method = RequestMethod.GET)
    public Map<String,Object> selectBound(HttpServletRequest request){
        try {
            String user_id = request.getHeader("user_id");
            return personalCenterService.selectBound(user_id);
        }catch (Exception e){
            LoggerUtil.outError(PersonalCenterController.class, "查询账户绑定信息发生错误", e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 注销登录
     * @param request
     * @return
     */
    @RequestMapping(value = "/inte/logout",method = RequestMethod.POST)
    public Map<String,Object> logout(HttpServletRequest request){
        try {
            String user_id = request.getHeader("user_id");
            return personalCenterService.logout(user_id);
        }catch (Exception e){
            LoggerUtil.outError(PersonalCenterController.class, "注销时发生错误", e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 查询个人基本资料
     * @param request
     * @return
     */
    @RequestMapping(value = "/inte/selectInfo",method = RequestMethod.GET)
    public Map<String,Object> selectInfo(HttpServletRequest request){
        try {
            String user_id = request.getHeader("user_id");
            return personalCenterService.selectInfo(user_id);
        }catch (Exception e){
            LoggerUtil.outError(PersonalCenterController.class, "查询个人基础信息时发生错误", e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 变更手机号
     * @return
     */
    @RequestMapping(value = "/inte/updatePhone",method = RequestMethod.POST)
    public Map<String,Object> updatePhone(@RequestParam("worn_phone") String worn_phone,@RequestParam("new_phone") String new_phone,
                                          @RequestParam("verifyCode") String verifyCode,HttpServletRequest request){
        try {
            String user_id = request.getHeader("user_id");
            return personalCenterService.updatePhone(worn_phone,new_phone,verifyCode,user_id);
        }catch (Exception e){
            LoggerUtil.outError(PersonalCenterController.class, "变更手机号时发生错误", e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 查询密码是否已创建
     * @param request
     * @return
     */
    @RequestMapping(value = "/inte/selectPassword",method = RequestMethod.GET)
    public Map<String,Object> selectPassword(HttpServletRequest request){
        try {
            String user_id = request.getHeader("user_id");
            return personalCenterService.selectPassword(user_id);
        }catch (Exception e){
            LoggerUtil.outError(PersonalCenterController.class, "查询是否已创建密码时发生错误", e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 第三方账户解除绑定
     * @param request
     * @param type
     * @param identifier
     * @return
     */
    @RequestMapping(value = "/inte/unwrap",method = RequestMethod.POST)
    public Map<String,Object> unwrap(HttpServletRequest request,@RequestParam("identifier") String identifier,@RequestParam("type") int type){
        try {
            String user_id = request.getHeader("user_id");
            return personalCenterService.unwrap(user_id,identifier,type);
        }catch (Exception e){
            LoggerUtil.outError(PersonalCenterController.class, "第三方账户解除时发生错误", e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 绑定第三方账户
     * @param request
     * @param identifier
     * @param type
     * @return
     */
    @RequestMapping(value = "/inte/bound",method = RequestMethod.POST)
    public Map<String,Object> bound(HttpServletRequest request,@RequestParam("identifier") String identifier,@RequestParam("type") int type){
        try {
            String user_id = request.getHeader("user_id");
            return personalCenterService.bound(user_id,identifier,type, Utils.getIpAddress(request));
        }catch (Exception e){
            LoggerUtil.outError(PersonalCenterController.class, "绑定第三方账户时发生错误", e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 查询用户详细信息
     * @param user_id
     * @param request
     * @return
     */
    @RequestMapping(value = "/inte/selectUserInfo",method = RequestMethod.GET)
    public Map<String,Object> selectUserInfo(@RequestParam("user_id") String user_id,HttpServletRequest request){
        try {
            String operate_user = request.getHeader("user_id");
            return personalCenterService.selectUserInfo(user_id,operate_user);
        }catch (Exception e){
            LoggerUtil.outError(PersonalCenterController.class, "查询用户详细信息时发生错误", e);
            return ControllerReturnBase.errorResule();
        }
    }


}
