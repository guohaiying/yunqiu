package com.yunqiu.tournament.controller;

import com.yunqiu.tournament.service.GameCollectionService;
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
@RequestMapping("/gameCollection")
@EnableAutoConfiguration
public class GameCollectionController {
    @Autowired
    private GameCollectionService gameCollectionService;

    /**
     * 收藏比赛
     * @param game_id
     * @param request
     * @return
     */
    @RequestMapping(value = "/inte/collect",method = RequestMethod.POST)
    public Map<String,Object> collect(@RequestParam("game_id") String game_id, HttpServletRequest request){
        try {
            String user_id = request.getHeader("user_id");
            return gameCollectionService.collect(user_id,game_id);
        }catch (Exception e){
            LoggerUtil.outError(GameCollectionController.class,"收藏比赛时发生错误",e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 取消收藏比赛
     * @param game_id
     * @param request
     * @return
     */
    @RequestMapping(value = "/inte/cancelCollect",method = RequestMethod.POST)
    public Map<String,Object> cancelCollect(@RequestParam("game_id") String game_id, HttpServletRequest request){
        try {
            String user_id = request.getHeader("user_id");
            return gameCollectionService.cancelCollect(user_id,game_id);
        }catch (Exception e){
            LoggerUtil.outError(GameCollectionController.class,"取消收藏比赛时发生错误",e);
            return ControllerReturnBase.errorResule();
        }
    }
}
