package com.yunqiu.controller.tournament;

import com.yunqiu.controller.common.BaseController;
import com.yunqiu.model.Global;
import com.yunqiu.model.PageCrt;
import com.yunqiu.model.Tournament;
import com.yunqiu.service.JoinLeagueService;
import com.yunqiu.service.TournamentService;
import com.yunqiu.util.JSONKit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/tournament")
@EnableAutoConfiguration
public class TournamentController extends BaseController {
    @Autowired
    private TournamentService tournamentService;

    @Autowired
    private JoinLeagueService joinLeagueService;

    JSONKit json= new JSONKit();

    //查找所有赛程
    @RequestMapping("/select")
    public void select( int page , int rows,String sidx,String sord,HttpServletResponse response,HttpSession httpSession) {
        String  leagueId= httpSession.getAttribute("tournament_leagueId")+"";
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("leagueId",leagueId);

        PageCrt page1 = new PageCrt();
        page1.setPage(page);
        page1.setRows(rows);
        page1.setMap(map);
        page1.setSidx(sidx);
        page1.setSord(sord);

        Integer totalRecord=tournamentService.selectCount(page1);
        int index = ((page - 1) * rows);
        page1.setIndex(index);
        page1.setRecords(totalRecord);
        page1.setTotal(totalRecord % rows == 0 ? totalRecord / rows : totalRecord / rows + 1);
        List<Map> list = tournamentService.selectPaging(page1);
        page1.setList(list);
        PrintWriter out;
        out = json.getPrintWriter(response);
        json.objToJson(page1, out);
    }


    //查找所有比赛
    @RequestMapping("/selectTournament")
    public void selectTournament( int page , int rows,String sidx,String sord,HttpServletResponse response,HttpSession httpSession) {
        Map<String, Object> map = new HashMap<String, Object>();

        PageCrt page1 = new PageCrt();
        page1.setPage(page);
        page1.setRows(rows);
        page1.setMap(map);
        page1.setSidx(sidx);
        page1.setSord(sord);

        Integer totalRecord=tournamentService.selectCountTournament(page1);
        int index = ((page - 1) * rows);
        page1.setIndex(index);
        page1.setRecords(totalRecord);
        page1.setTotal(totalRecord % rows == 0 ? totalRecord / rows : totalRecord / rows + 1);
        List<Map> list = tournamentService.selectPagingTournament(page1);
        page1.setList(list);
        PrintWriter out;
        out = json.getPrintWriter(response);
        json.objToJson(page1, out);
    }


    //添加赛程
    @ResponseBody
    @RequestMapping("/addTournament")
    public Map<String,Object>  addTournament(HttpServletResponse response, Tournament tournament,HttpSession httpSession) {

        Map<String,Object> map = (Map<String,Object>)httpSession.getAttribute(Global.USER_SESSION_KEY);
        Map<String,Object> data = (Map<String,Object>)map.get("data");
        String userId = data.get("userId")+"";
        String  leagueId= httpSession.getAttribute("tournament_leagueId")+"";
        tournament.setUserId(userId);
        tournament.setLeagueId(leagueId);
        Map<String, Object> datamap = tournamentService.addTournament(tournament);
        return datamap;
    }

    //场地修改
    @ResponseBody
    @RequestMapping("/upTournament")
    public Map<String,Object>  upTournament(HttpServletResponse response, Tournament tournament) {
        Map<String, Object> map = tournamentService.updateTournament(tournament);
        return map;
    }

    //赛程删除
    @ResponseBody
    @RequestMapping("/delTournament")
    public Map<String,Object>  delTournament(HttpServletResponse response, @RequestParam("gameId") String gameId) {
        Map<String, Object> map = tournamentService.deleteTournament(gameId);
        return map;
    }

    //查询所有比赛
    @ResponseBody
    @RequestMapping("/getTournamentList")
    public Map<String,Object>  getTournamentList(HttpServletResponse response) {
        Map<String, Object> map = tournamentService.getTournamentList();
        return map;
    }


    //获取赛事名称
    @ResponseBody
    @RequestMapping("/getLeagueName")
    public String  getLeagueName(HttpServletResponse response, HttpSession httpSession) {
        //获取leagueId
        String leagueId = httpSession.getAttribute("tournament_leagueId")+"";

        String name = joinLeagueService.getJoinLeagueName(leagueId);
        return name;
    }


    //添加比赛
    @ResponseBody
    @RequestMapping("/addTournamentList")
    public Map<String,Object>  addTournamentList(HttpServletResponse response, Tournament tournament,HttpSession httpSession) {

        Map<String,Object> map = (Map<String,Object>)httpSession.getAttribute(Global.USER_SESSION_KEY);
        Map<String,Object> data = (Map<String,Object>)map.get("data");
        String userId = data.get("userId")+"";
        tournament.setUserId(userId);
        Map<String, Object> datamap = tournamentService.addTournamentList(tournament);
        return datamap;
    }

    //比赛信息
    @ResponseBody
    @RequestMapping("/upTournamentList")
    public Map<String,Object>  upTournamentList(HttpServletResponse response, Tournament tournament,HttpSession httpSession) {
        Map<String, Object> map = tournamentService.updateTournamentList(tournament);
        return map;
    }

    //获取A队所有球员
    @ResponseBody
    @RequestMapping("/getEntryTeamAList")
    public Map<String,Object>  getEntryTeamAList(HttpServletResponse response,HttpSession httpSession) {
        String gameId = httpSession.getAttribute("tournamedata_gameId")+"";

        Map<String, Object> map = tournamentService.getEntryTeamAList(gameId);
        return map;
    }

    //获取A队队名
    @ResponseBody
    @RequestMapping("/getEntryTeamAName")
    public Map<String,Object>  getEntryTeamAName(HttpServletResponse response,HttpSession httpSession) {
        String gameId = httpSession.getAttribute("tournamedata_gameId")+"";

        Map<String, Object> map = tournamentService.getEntryTeamAName(gameId);
        return map;
    }

    //获取B队所有球员
    @ResponseBody
    @RequestMapping("/getEntryTeamBList")
    public Map<String,Object>  getEntryTeamBList(HttpServletResponse response,HttpSession httpSession) {
        String gameId = httpSession.getAttribute("tournamedata_gameId")+"";

        Map<String, Object> map = tournamentService.getEntryTeamBList(gameId);
        return map;
    }

    //获取B队队名
    @ResponseBody
    @RequestMapping("/getEntryTeamBName")
    public Map<String,Object>  getEntryTeamBName(HttpServletResponse response,HttpSession httpSession) {
        String gameId = httpSession.getAttribute("tournamedata_gameId")+"";

        Map<String, Object> map = tournamentService.getEntryTeamBName(gameId);
        return map;
    }


    //获取比赛的两队队员
    @ResponseBody
    @RequestMapping("/getEntryTeamNameAB")
    public Map<String,Object>  getEntryTeamNameAB(HttpServletResponse response,HttpSession httpSession) {
        String gameId = httpSession.getAttribute("tournamedata_gameId")+"";

        Map<String, Object> map = tournamentService.getEntryTeamNameAB(gameId);
        return map;
    }

    //获取比赛的队员
    @ResponseBody
    @RequestMapping("/getEntryTeamAllUser")
    public Map<String,Object>  getEntryTeamAllUser(HttpServletResponse response,HttpSession httpSession) {
        String gameId = httpSession.getAttribute("tournamedata_gameId")+"";

        Map<String, Object> map = tournamentService.getEntryTeamAllUser(gameId);
        return map;
    }

    //根据球队id获取球队队员
    @ResponseBody
    @RequestMapping("/getEntryTeamByTeamId")
    public Map<String,Object>  getEntryTeamByTeamId(HttpServletResponse response,HttpSession httpSession,@RequestParam("teamId") String teamId) {
        String gameId = httpSession.getAttribute("tournamedata_gameId")+"";

        Map<String, Object> map = tournamentService.getEntryTeamByTeamId(gameId,teamId);
        return map;
    }


}
