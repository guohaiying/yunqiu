package com.yunqiu.tournament.controller;

import com.yunqiu.tournament.model.Tournament;
import com.yunqiu.tournament.service.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 比赛控制类
 * @author 武尊
 * @time 2017-02-23
 */

@RestController
@RequestMapping("/tournament")
@EnableAutoConfiguration
public class TournamentController {
    @Autowired
    private TournamentService tournamentService;

    /**
     * 创建比赛
     * @param tournament
     * @param request
     * @return
     */
    @RequestMapping(value = "/inte/createTournament",method = RequestMethod.POST)
    public Map<String,Object> createTournament(Tournament tournament, HttpServletRequest request){
        String userId = request.getHeader("user_id");
        return tournamentService.createTournament(tournament,userId);
    }

    /**
     * 获取筛选比赛列表的赛选条件
     * @param request
     * @return
     */
    @RequestMapping(value = "/inte/gameFiltrateConditions",method = RequestMethod.GET)
    public Map<String,Object> gameFiltrateConditions(HttpServletRequest request){
        String userId = request.getHeader("user_id");
        return tournamentService.gameFiltrateConditions(userId);
    }

    /**
     * 获取用户所在的球队的所有比赛、训练
     * @param pageNum
     * @param pageSize
     * @param game_status
     * @param game_time
     * @param team_id
     * @param request
     * @return
     */
    @RequestMapping(value = "/inte/selectGameList",method = RequestMethod.GET)
    public Map<String,Object> selectGameList(@RequestParam(value = "pageNum",required = false,defaultValue = "0") int pageNum,
                                             @RequestParam(value = "pageSize",required = false,defaultValue = "0") int pageSize,
                                             @RequestParam(value = "game_status",required = false,defaultValue = "0") int game_status,
                                             @RequestParam(value = "game_time",required = false,defaultValue = "") String game_time,
                                             @RequestParam(value = "team_id",required = false,defaultValue = "") String team_id,
                                             HttpServletRequest request){
        String user_id = request.getHeader("user_id");
        return tournamentService.selectGameList(user_id,pageNum,pageSize,game_status,game_time,team_id);
    }

    /**
     * 获取用户收藏的比赛列表
     * @param pageNum
     * @param pageSize
     * @param request
     * @return
     */
    @RequestMapping(value = "/inte/selectCollectionGameList",method = RequestMethod.GET)
    public Map<String,Object> selectCollectionGameList(@RequestParam(value = "pageNum",required = false,defaultValue = "0") int pageNum,
                                                       @RequestParam(value = "pageSize",required = false,defaultValue = "0") int pageSize,
                                                        HttpServletRequest request){
        String user_id = request.getHeader("user_id");
        return tournamentService.selectCollectionGameList(user_id,pageNum,pageSize);
    }

    /**
     * 查询比赛概况
     * @param game_id
     * @param request
     * @return
     */
    @RequestMapping(value = "/inte/selectGameInfo",method = RequestMethod.GET)
    public Map<String,Object> selectGameInfo(@RequestParam("game_id") String game_id,HttpServletRequest request){
        String user_id = request.getHeader("user_id");
        return tournamentService.selectGameInfo(user_id,game_id);
    }
    @RequestMapping(value = "/web/selectGameInfo",method = RequestMethod.GET)
    public Map<String,Object> selectGameInfoWeb(@RequestParam("game_id") String game_id,HttpServletRequest request){
        String user_id = request.getHeader("user_id");
        return tournamentService.selectGameInfo(user_id,game_id);
    }

    /**
     * 审核或者应战比赛
     * @param game_id
     * @param type 1:审核 2 应战
     * @param comment 1 同意/应战  2：拒绝/拒绝应战
     * @param cause 拒绝理由
     * @param request
     * @return
     */
    @RequestMapping(value = "/inte/auditOrFightGame",method = RequestMethod.POST)
    public Map<String,Object> auditOrFightGame(@RequestParam("game_id") String game_id,@RequestParam("type") int type,
                                               @RequestParam("comment") int comment, HttpServletRequest request,
                                               @RequestParam(value = "cause",required = false,defaultValue = "") String cause,
                                               @RequestParam(value = "uniform_teamB",required = false,defaultValue = "0") int uniform_teamB){
        String user_id = request.getHeader("user_id");
        return tournamentService.auditOrFightGame(game_id,type,comment,cause,user_id,uniform_teamB);
    }

    /**
     * 取消比赛
     * @param game_id
     * @param request
     * @return
     */
    @RequestMapping(value = "/inte/cancelGame",method = RequestMethod.POST)
    public Map<String,Object> cancelGame(@RequestParam("game_id") String game_id,@RequestParam("cause") String cause, HttpServletRequest request){
        String user_id = request.getHeader("user_id");
        return tournamentService.cancelGame(game_id,user_id,cause);
    }

    /**
     * 修改比赛
     * @param tournament
     * @param request
     * @return
     */
    @RequestMapping(value = "/inte/updateGame",method = RequestMethod.POST)
    public Map<String,Object> updateGame(Tournament tournament,HttpServletRequest request){
        String user_id = request.getHeader("user_id");
        return tournamentService.updateGame(tournament,user_id);
    }

    /**
     * 获取约战列表
     * @param pageNum 页码
     * @param pageSize 获取条数
     * @param name 搜索条件
     * @param game_time 时间筛选条件
     * @param province 省份筛选条件
     * @param city 市筛选条件
     * @param area 区/县筛选条件
     * @param game_system 赛制筛选条件
     * @return
     */
    @RequestMapping(value = "/getAboutGameList",method = RequestMethod.GET)
    public Map<String,Object> getAboutGameList(@RequestParam(value = "pageNum",required = false,defaultValue = "1") int pageNum,
                                               @RequestParam(value = "pageSize",required = false,defaultValue = "20") int pageSize,
                                               @RequestParam(value = "name",required = false,defaultValue = "") String name,
                                               @RequestParam(value = "game_time",required = false,defaultValue = "") String game_time,
                                               @RequestParam(value = "province",required = false,defaultValue = "") String province,
                                               @RequestParam(value = "city",required = false,defaultValue = "") String city,
                                               @RequestParam(value = "area",required = false,defaultValue = "") String  area,
                                               @RequestParam(value = "game_system",required = false,defaultValue = "0") int game_system){
        return tournamentService.getAboutGameList(pageNum,pageSize,name,game_time,province,city,area,game_system);
    }
}
