package com.yunqiu.controller;

import com.yunqiu.service.MatchService;
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
 * Created by 11366 on 2017/1/25.
 */

@RestController
@RequestMapping("/game")
@EnableAutoConfiguration
public class MatchController {
    @Autowired
    private MatchService matchService;

    /**
     * 参与比赛
     * @param team_id
     * @param event_id
     * @param status
     * @param request
     * @return
     */
    @RequestMapping(value = "/inte/participationGame",method = RequestMethod.POST)
    public Map<String,Object> participationGame(@RequestParam("team_id") String team_id, @RequestParam("event_id") String event_id,
                                        @RequestParam("status") int status, HttpServletRequest request){
        try {
            String user_id = request.getHeader("user_id");
            return matchService.participationGame(team_id,event_id,status,user_id);
        }catch (Exception e){
            LoggerUtil.outError(MatchController.class,"参与比赛时发生错误",e);
            return ControllerReturnBase.errorResule();
        }
    }
}
