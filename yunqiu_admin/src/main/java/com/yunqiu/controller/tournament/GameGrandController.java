package com.yunqiu.controller.tournament;

import com.yunqiu.controller.common.BaseController;
import com.yunqiu.model.GameGrand;
import com.yunqiu.model.PageCrt;
import com.yunqiu.service.GameGrandService;
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
@RequestMapping("/gameGrand")
@EnableAutoConfiguration
public class GameGrandController extends BaseController {
    @Autowired
    private GameGrandService gameGrandService;

    @Autowired
    private TournamentService tournamentService;


    JSONKit json= new JSONKit();

    //更新云五数据
    @ResponseBody
    @RequestMapping("/upUserCloudData")
    public Map<String,Object>  upUserCloudData(HttpServletResponse response,String gameId) {
        Map<String, Object> map = gameGrandService.upUserCloudData(gameId);
        return map;
    }


    //比赛数据添加
    @ResponseBody
    @RequestMapping("/addGameGrand")
    public Map<String,Object>  addGameGrand(HttpServletResponse response, GameGrand gameGrand,HttpSession httpSession) {
        String  gameId= httpSession.getAttribute("tournamedata_gameId")+"";
        gameGrand.setGameId(gameId);
        Map<String, Object> map = gameGrandService.addGameGrand(gameGrand);
        return map;
    }

    //比赛数据添加2
    @ResponseBody
    @RequestMapping("/addGameGrand2")
    public Map<String,Object>  addGameGrand2(HttpServletResponse response, GameGrand gameGrand,HttpSession httpSession,@RequestParam("gltype") int gltype,@RequestParam("gluserId") String gluserId) {
        String  gameId= httpSession.getAttribute("tournamedata_gameId")+"";
        gameGrand.setGameId(gameId);
        Map<String, Object> map = gameGrandService.addGameGrand2(gameGrand,gltype,gluserId);
        return map;
    }

    //比赛数据添加
    @ResponseBody
    @RequestMapping("/addGameGrand3")
    public Map<String,Object>  addGameGrand3(HttpServletResponse response, GameGrand gameGrand,HttpSession httpSession) {
        String  gameId= httpSession.getAttribute("tournamedata_gameId")+"";
        gameGrand.setGameId(gameId);
        gameGrand.setType(17);
        Map<String, Object> map = gameGrandService.addGameGrand(gameGrand);
        return map;
    }


    //计算控球率
    @ResponseBody
    @RequestMapping("/addGameGrandKongqiu")
    public Map<String,Object>  addGameGrandKongqiu(HttpServletResponse response, GameGrand gameGrand,int result2,HttpSession httpSession) {
        String  gameId= httpSession.getAttribute("tournamedata_gameId")+"";
        gameGrand.setGameId(gameId);

        int count = gameGrandService.deleteGameGrandByGameId(gameId);

        Map<String, Object> datamap = tournamentService.getEntryTeamNameABMap(gameId);
        String entryTeamAId = datamap.get("entryTeamAId")+"";
        String entryTeamBId = datamap.get("entryTeamBId")+"";

        gameGrand.setTeamId(entryTeamAId);
        Map<String, Object> map = gameGrandService.addGameGrand(gameGrand);

        gameGrand.setTeamId(entryTeamBId);
        gameGrand.setResult(result2);
        map = gameGrandService.addGameGrand(gameGrand);
        return map;
    }

    //比赛数据修改
    @ResponseBody
    @RequestMapping("/upGameGrand")
    public Map<String,Object>  upGameGrand(HttpServletResponse response, GameGrand gameGrand,@RequestParam("gltype") int gltype,@RequestParam("gluserId") String gluserId) {
        Map<String, Object> map = gameGrandService.updateGameGrand(gameGrand,gltype,gluserId);
        return map;
    }

    //比赛数据删除
    @ResponseBody
    @RequestMapping("/delGameGrand")
    public Map<String,Object>  delGameGrand(HttpServletResponse response, @RequestParam("grandId") String grandId) {
        Map<String, Object> map = gameGrandService.deleteGameGrandById(grandId);
        return map;
    }

    //查找所有
    @RequestMapping("/select")
    public void select( int page , int rows,String sidx,String sord,HttpServletResponse response,HttpSession httpSession) {
        Map<String, Object> map = new HashMap<String, Object>();
        String  gameId= httpSession.getAttribute("tournamedata_gameId")+"";
        map.put("gameId",gameId);

        PageCrt page1 = new PageCrt();
        page1.setPage(page);
        page1.setRows(rows);
        page1.setMap(map);
        page1.setSidx(sidx);
        page1.setSord(sord);

        Integer totalRecord=gameGrandService.selectCount(page1);
        int index = ((page - 1) * rows);
        page1.setIndex(index);
        page1.setRecords(totalRecord);
        page1.setTotal(totalRecord % rows == 0 ? totalRecord / rows : totalRecord / rows + 1);
        List<Map> list = gameGrandService.selectPaging(page1);
        page1.setList(list);
        PrintWriter out;
        out = json.getPrintWriter(response);
        json.objToJson(page1, out);
    }

    //查找所有
    @RequestMapping("/selectLie")
    public void selectLie( int page , int rows,String sidx,String sord,HttpServletResponse response,HttpSession httpSession) {
        Map<String, Object> map = new HashMap<String, Object>();
        String  gameId= httpSession.getAttribute("tournamedata_gameId")+"";
        map.put("gameId",gameId);

        PageCrt page1 = new PageCrt();
        page1.setPage(page);
        page1.setRows(rows);
        page1.setMap(map);
        page1.setSidx(sidx);
        page1.setSord(sord);

        Integer totalRecord=gameGrandService.selectCountLie(page1);
        int index = ((page - 1) * rows);
        page1.setIndex(index);
        page1.setRecords(totalRecord);
        page1.setTotal(totalRecord % rows == 0 ? totalRecord / rows : totalRecord / rows + 1);
        List<Map> list = gameGrandService.selectPagingLie(page1);
        page1.setList(list);
        PrintWriter out;
        out = json.getPrintWriter(response);
        json.objToJson(page1, out);
    }


}
