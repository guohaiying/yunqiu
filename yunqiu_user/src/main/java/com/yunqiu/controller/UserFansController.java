package com.yunqiu.controller;

import com.yunqiu.service.UserFansService;
import com.yunqiu.util.ControllerReturnBase;
import com.yunqiu.util.LoggerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 用户关注用户
 */

@RestController
@RequestMapping("/fans")
@EnableAutoConfiguration
public class UserFansController {
    @Autowired
    private UserFansService userFansService;

    /**
     * 用户添加关注
     * @param focus
     * @param request
     * @return
     */
    @RequestMapping(value = "/inte/attention",method = RequestMethod.POST)
    public Map<String,Object> attention(@RequestParam("focus") String focus, HttpServletRequest request){
        try {
            String user_id = request.getHeader("user_id");
            return userFansService.attention(user_id,focus);
        }catch (Exception e){
            LoggerUtil.outError(UserFansController.class,"添加关注用户时发生错误",e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 取消关注
     * @param focus
     * @param request
     * @return
     */
    @RequestMapping(value = "/inte/cancelAttention",method = RequestMethod.POST)
    public Map<String,Object> cancelAttention(@RequestParam("focus") String focus, HttpServletRequest request){
        try {
            String user_id = request.getHeader("user_id");
            return userFansService.cancelAttention(user_id,focus);
        }catch (Exception e){
            LoggerUtil.outError(UserFansController.class,"取消关注用户时发生错误",e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 获取粉丝列表
     * @param pageNum  页码
     * @param pageSize 每页显示多少条
     * @param request
     * @return
     */
    @RequestMapping(value = "/inte/fansList",method = RequestMethod.GET)
    public Map<String,Object> fansList(@RequestParam("pageNum") int pageNum,@RequestParam("pageSize") int pageSize,HttpServletRequest request){
        try {
            String user_id = request.getHeader("user_id");
            return userFansService.fansList(pageNum,pageSize,user_id);
        }catch (Exception e){
            LoggerUtil.outError(UserFansController.class,"获取粉丝列表时发生错误",e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 获取关注球员列表
     * @param pageNum
     * @param pageSize
     * @param request
     * @return
     */
    @RequestMapping(value = "/inte/focusList",method = RequestMethod.GET)
    public Map<String,Object> focusList(@RequestParam("pageNum") int pageNum,@RequestParam("pageSize") int pageSize,HttpServletRequest request){
        try {
            String user_id = request.getHeader("user_id");
            return userFansService.focusList(pageNum,pageSize,user_id);
        }catch (Exception e){
            LoggerUtil.outError(UserFansController.class,"获取关注球员列表时发生错误",e);
            return ControllerReturnBase.errorResule();
        }
    }

}
