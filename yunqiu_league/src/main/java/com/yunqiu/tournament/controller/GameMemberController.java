package com.yunqiu.tournament.controller;

import com.yunqiu.tournament.service.GameMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 比赛成员控制类
 * @time 2017-02-26
 */
@RestController
@RequestMapping("/gameMember")
@EnableAutoConfiguration
public class GameMemberController {
    @Autowired
    private GameMemberService gameMemberService;

    /**
     * 参与比赛
     * @param game_id
     * @param team_id
     * @param join_status
     * @param request
     * @return
     */
    @RequestMapping(value = "/inte/participationGame",method = RequestMethod.POST)
    public Map<String,Object> participationGame(@RequestParam("game_id") String game_id, @RequestParam("team_id") String team_id,
                                                @RequestParam("join_status") int join_status, HttpServletRequest request){
        String user_id = request.getHeader("user_id");
        return gameMemberService.participationGame(game_id,team_id,user_id,join_status);
    }
}
