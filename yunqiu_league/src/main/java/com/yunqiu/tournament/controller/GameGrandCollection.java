package com.yunqiu.tournament.controller;

import com.yunqiu.tournament.service.GameGrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 比赛战报路由
 */
@RestController
@RequestMapping("/gameGrand")
@EnableAutoConfiguration
public class GameGrandCollection {
    @Autowired
    private GameGrandService gameGrandService;

    /**
     * 录入比赛比分
     * @param data_json
     * @param request
     * @return
     */
    @RequestMapping(value = "/inte/enterGrand",method = RequestMethod.POST)
    public Map<String,Object> enterGrand(@RequestParam("data_json") String data_json, HttpServletRequest request){
        String user_id = request.getHeader("user_id");
        return gameGrandService.enterGrand(data_json,user_id);
    }

    /**
     * 获取比赛比分
     * @param game_id
     * @return
     */
    @RequestMapping(value = "/inte/selectGrand",method = RequestMethod.GET)
    public Map<String,Object> selectGrand(@RequestParam("game_id") String game_id){
        return gameGrandService.selectGrand(game_id);
    }
    @RequestMapping(value = "/web/selectGrand",method = RequestMethod.GET)
    public Map<String,Object> selectGrandWeb(@RequestParam("game_id") String game_id){
        return gameGrandService.selectGrand(game_id);
    }
}
