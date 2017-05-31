package com.yunqiu.controller;

import com.yunqiu.service.EventCollectionService;
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
 * 收藏比赛
 */

@RestController
@RequestMapping("/game")
@EnableAutoConfiguration
public class EventCollectionConeroller {
    @Autowired
    private EventCollectionService eventCollectionService;

    /**
     * 收藏比赛
     * @param event_id
     * @param request
     * @return
     */
    @RequestMapping(value = "/inte/collect",method = RequestMethod.POST)
    public Map<String,Object> collect(@RequestParam("event_id") String event_id, HttpServletRequest request){
        try {
            String user_id = request.getHeader("user_id");
            return eventCollectionService.collect(user_id,event_id);
        }catch (Exception e){
            LoggerUtil.outError(EventCollectionConeroller.class,"收藏比赛时发生错误",e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 取消收藏比赛
     * @param event_id
     * @param request
     * @return
     */
    @RequestMapping(value = "/inte/cancelCollect",method = RequestMethod.POST)
    public Map<String,Object> cancelCollect(@RequestParam("event_id") String event_id, HttpServletRequest request){
        try {
            String user_id = request.getHeader("user_id");
            return eventCollectionService.cancelCollect(user_id,event_id);
        }catch (Exception e){
            LoggerUtil.outError(EventCollectionConeroller.class,"取消收藏比赛时发生错误",e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 获取收藏的筛选条件
     * @return
     */
    @RequestMapping(value = "/inte/collectFiltrateConditions",method = RequestMethod.GET)
    public Map<String,Object> collectFiltrateConditions(HttpServletRequest request){
        try {
            String user_id = request.getHeader("user_id");
            return eventCollectionService.collectFiltrateConditions(user_id);
        }catch (Exception e){
            LoggerUtil.outError(EventCollectionConeroller.class,"获取收藏筛选条件时发生错误",e);
            return ControllerReturnBase.errorResule();
        }
    }


    /**
     * 获取收藏列表
     * @param pageNum  当前页码
     * @param pageSize  获取条数
     * @param status  状态筛选条件
     * @param time   时间筛选条件
     * @param request
     * @return
     */
    @RequestMapping(value = "/inte/collectList",method = RequestMethod.GET)
    public Map<String,Object> collectList(@RequestParam("pageNum") int pageNum,@RequestParam("pageSize") int pageSize,
                                          @RequestParam("status") int status,@RequestParam("time") String time, HttpServletRequest request){
        try {
            String user_id = request.getHeader("user_id");
            return eventCollectionService.collectList(user_id,pageNum,pageSize,status,time);
      }catch (Exception e){
            LoggerUtil.outError(EventCollectionConeroller.class,"获取收藏比赛列表时发生错误",e);
            return ControllerReturnBase.errorResule();
        }
    }
}
