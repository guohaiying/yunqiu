package com.yunqiu.controller;

import com.yunqiu.service.IndexService;
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
 * 首页数据
 */

@RestController
@RequestMapping("/index")
@EnableAutoConfiguration
public class IndexController {
    @Autowired
    private IndexService indexService;

    /**
     * 获取首页球队列表
     * @param request
     * @return
     */
    @RequestMapping(value = "/inte/indexTeamList",method = RequestMethod.GET)
    public Map<String,Object> indexTeamList(HttpServletRequest request){
        try {
            String user_id = request.getHeader("user_id");
            return indexService.indexTeamList(user_id);
        }catch (Exception e){
            LoggerUtil.outError(IndexController.class,"获取首页球队列表时发生错误",e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 根据球队id，首页获取球队详细信息
     * @param team_id
     * @return
     */
    @RequestMapping(value = "/inte/indexTeamInfo",method = RequestMethod.GET)
    public Map<String,Object> indexTeamInfo(@RequestParam("team_id") String team_id,HttpServletRequest request){
        try {
            String user_id = request.getHeader("user_id");
            return indexService.indexTeamInfo(team_id,user_id);
        }catch (Exception e){
            LoggerUtil.outError(IndexController.class,"获取首页球队详细数据时报错",e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 刷新首页球队推荐列表
     * @param number
     * @return
     */
    @RequestMapping(value = "/inte/indexRefreshRecommendedTeam",method = RequestMethod.GET)
    public Map<String,Object> indexRefreshRecommendedTeam(@RequestParam("number") int number){
        try {
            return indexService.indexRefreshRecommendedTeam(number);
        }catch (Exception e){
            LoggerUtil.outError(IndexController.class,"刷新首页推荐球队时发生错误",e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 获取推荐的球员
     * @param number
     * @return
     */
    @RequestMapping(value = "/inte/getRecommendationUser",method = RequestMethod.GET)
    public Map<String,Object> getRecommendationUser(@RequestParam("number") int number){
        return indexService.getRecommendationUser(number);
    }

    /**
     * 获取消息列表
     * @return
     */
    @RequestMapping(value = "/inte/getMessageList",method = RequestMethod.GET)
    public Map<String,Object> getMessageList(HttpServletRequest request){
        String user_id = request.getHeader("user_id");
        return indexService.getMessageList(user_id);
    }

    /**
     * 获取消息
     * @param message_type 消息类型 1：球队消息 2：赛事消息 3：个人消息 4：系统消息
     * @param request
     * @param type_id  根据message_type传值，球队消息 传球队id ， 赛事消息可不传， 个人消息传user_id ， 系统消息不传
     * @param pageNum  页码，最小值1
     * @param pageSize 条数
     * @return
     */
    @RequestMapping(value = "/inte/getMessage",method = RequestMethod.GET)
    public Map<String,Object> getMessage(@RequestParam("message_type") int message_type,HttpServletRequest request,
                                         @RequestParam(value = "type_id",required = false,defaultValue = "") String type_id,
                                         @RequestParam("pageNum") int pageNum,@RequestParam("pageSize") int pageSize){
        String user_id = request.getHeader("user_id");
        return indexService.getMessage(user_id,message_type,type_id,pageNum,pageSize);
    }

    /**
     * 修改消息阅读状态
     * @param message_id
     * @param message_type
     * @param type_id
     * @param request
     * @return
     */
    @RequestMapping(value = "/inte/updateMessageStatus",method = RequestMethod.POST)
    public Map<String,Object> updateMessageStatus(@RequestParam(value = "message_id",required = false,defaultValue = "") String message_id,
                                                  @RequestParam(value = "message_type",required = false,defaultValue = "0") int message_type,
                                                  @RequestParam(value = "type_id",required = false,defaultValue = "") String type_id,
                                                  HttpServletRequest request){
        String user_id = request.getHeader("user_id");
        return indexService.updateMessageStatus(user_id,message_id,message_type,type_id);
    }
}
