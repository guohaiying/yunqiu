package com.yunqiu.controller;

import com.yunqiu.model.Event;
import com.yunqiu.service.EventService;
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
 * Created by 11366 on 2017/1/23.
 */

@RestController
@RequestMapping("/game")
@EnableAutoConfiguration
public class EventController {
    @Autowired
    private EventService eventService;


    /**
     * 创建比赛
     * @param event
     * @param request
     * @return
     */
    @RequestMapping(value = "/inte/createGame",method = RequestMethod.POST)
    public Map<String,Object> createGame(Event event, HttpServletRequest request){
        try {
            String user_id = request.getHeader("user_id");
            return eventService.createGame(event,user_id);
        }catch (Exception e){
            LoggerUtil.outError(EventController.class,"创建比赛时发生错误",e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 查询比赛列表
     * @param request
     * @return
     */
    @RequestMapping(value = "/inte/selectGameList",method = RequestMethod.GET)
    public Map<String,Object> selectGameList(@RequestParam(value = "pageNum",required = false,defaultValue = "0") int pageNum,
                                             @RequestParam(value = "pageSize",required = false,defaultValue = "0") int pageSize,
                                             @RequestParam(value = "status",required = false,defaultValue = "0") int status,
                                             @RequestParam(value = "time",required = false,defaultValue = "") String time,
                                             @RequestParam(value = "team_id",required = false,defaultValue = "") String team_id,
                                             HttpServletRequest request){
        try {
            String user_id = request.getHeader("user_id");
            return eventService.selectGameList(user_id,pageNum,pageSize,status,time,team_id);
        }catch (Exception e){
            LoggerUtil.outError(EventController.class,"查询比赛列表时发生错误",e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 审核比赛
     * @param event_id
     * @param audit_status
     * @param audit_reason
     * @param request
     * @return
     */
    @RequestMapping(value = "/inte/auditEvent",method = RequestMethod.POST)
    public Map<String,Object> auditEvent(@RequestParam("event_id") String event_id,@RequestParam("audit_status") int audit_status,
                                         @RequestParam("audit_reason") String audit_reason,HttpServletRequest request){
        try {
            String user_id = request.getHeader("user_id");
            return eventService.auditEvent(event_id,audit_status,audit_reason,user_id);
        }catch (Exception e){
            LoggerUtil.outError(EventController.class,"审核比赛时发生错误",e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     *应战比赛
     * @param event_id
     * @param battle_status
     * @param refuse_reason
     * @param request
     * @return
     */
    @RequestMapping(value = "/inte/battleEvent",method = RequestMethod.POST)
    public Map<String,Object> battleEvent(@RequestParam("event_id") String event_id,@RequestParam("battle_status") int battle_status,
                                          @RequestParam("refuse_reason") String refuse_reason,HttpServletRequest request){
        try {
            String user_id = request.getHeader("user_id");
            return eventService.battleEvent(event_id,battle_status,refuse_reason,user_id);
        }catch (Exception e){
            LoggerUtil.outError(EventController.class,"比赛迎战时发生错误",e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 取消比赛
     * @param event_id
     * @param cancel_reason
     * @param request
     * @return
     */
    @RequestMapping(value = "/inte/cancelEvent",method = RequestMethod.POST)
    public Map<String,Object> cancelEvent(@RequestParam("event_id") String event_id,@RequestParam("cancel_reason") String cancel_reason,HttpServletRequest request){
        try {
            String user_id = request.getHeader("user_id");
            return eventService.cancelEvent(event_id,cancel_reason,user_id);
        }catch (Exception e){
            LoggerUtil.outError(EventController.class,"取消比赛时发生错误",e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 获取比赛详情
     * @param event_id
     * @param request
     * @param type
     * @return
     */
    @RequestMapping(value = "/inte/selectEventInfo",method = RequestMethod.GET)
    public Map<String,Object> selectEventInfo(@RequestParam("event_id") String event_id,@RequestParam("type") int type,
                                              HttpServletRequest request){
        try {
            String user_id = request.getHeader("user_id");
            return eventService.selectEventInfo(event_id,user_id,type);
        }catch (Exception e){
            LoggerUtil.outError(EventController.class,"获取比赛详情时发生错误",e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 获取比赛基础信息
     * @param event_id
     * @return
     */
    @RequestMapping(value = "/inte/selectEventBasis",method = RequestMethod.GET)
    public Map<String,Object> selectEventBasis(@RequestParam("event_id") String event_id){
        try {
            return eventService.selectEventBasis(event_id);
        }catch (Exception e){
            LoggerUtil.outError(EventController.class,"获取比赛基础信息时发生错误",e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 获取查询比赛列表时的筛选条件
     * @return
     */
    @RequestMapping(value = "/inte/eventFiltrateConditions",method = RequestMethod.GET)
    public Map<String,Object> eventFiltrateConditions(HttpServletRequest request){
        try {
            String user_id = request.getHeader("user_id");
            return eventService.eventFiltrateConditions(user_id);
        }catch (Exception e){
            LoggerUtil.outError(EventCollectionConeroller.class,"获取查询比赛列表时的筛选条件发生错误",e);
            return ControllerReturnBase.errorResule();
        }
    }


}
