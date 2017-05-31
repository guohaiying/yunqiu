package com.yunqiu.controller.league;

import com.yunqiu.controller.common.BaseController;
import com.yunqiu.model.JoinLeague;
import com.yunqiu.model.PageCrt;
import com.yunqiu.service.JoinLeagueService;
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
@RequestMapping("/joinleague")
@EnableAutoConfiguration
public class JoinLeagueController extends BaseController {
    @Autowired
    private JoinLeagueService joinLeagueService;

    JSONKit json= new JSONKit();

    //查找所有
    @RequestMapping("/select")
    public void select(int page , int rows, String sidx, String sord, HttpServletResponse response, HttpSession httpSession) {
        //获取leagueId
        String leagueId = httpSession.getAttribute("joinleague_leagueId")+"";

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("leagueId",leagueId);

        PageCrt page1 = new PageCrt();
        page1.setPage(page);
        page1.setRows(rows);
        page1.setMap(map);
        page1.setSidx(sidx);
        page1.setSord(sord);

        Integer totalRecord=joinLeagueService.selectCount(page1);
        int index = ((page - 1) * rows);
        page1.setIndex(index);
        page1.setRecords(totalRecord);
        page1.setTotal(totalRecord % rows == 0 ? totalRecord / rows : totalRecord / rows + 1);
        List<Map> list = joinLeagueService.selectPaging(page1);
        page1.setList(list);
        PrintWriter out;
        out = json.getPrintWriter(response);
        json.objToJson(page1, out);
    }


    //赛事球队添加
    @ResponseBody
    @RequestMapping("/addjoinLeague")
    public Map<String,Object>  addjoinLeague(HttpServletResponse response, JoinLeague joinuleague, HttpSession httpSession) {
        //获取leagueId
        String leagueId = httpSession.getAttribute("joinleague_leagueId")+"";
        joinuleague.setLeagueId(leagueId);

        Map<String, Object> map = joinLeagueService.addjoinLeague(joinuleague);
        return map;
    }

    //赛事球队状态修改
    @ResponseBody
    @RequestMapping("/upjoinLeague")
   public Map<String,Object>  upPlace(HttpServletResponse response, JoinLeague joinuleague) {
        Map<String, Object> map = joinLeagueService.upjoinLeague(joinuleague);
        return map;
    }


    //赛事球队删除
    @ResponseBody
    @RequestMapping("/deljoinLeague")
    public Map<String,Object>  delPlace(HttpServletResponse response, @RequestParam("joinId") String joinId) {
        Map<String, Object> map = joinLeagueService.deletejoinLeague(joinId);
        return map;
    }

    //获取赛事名称
    @ResponseBody
    @RequestMapping("/getJoinLeagueName")
    public String  getJoinLeagueName(HttpServletResponse response, HttpSession httpSession) {
        //获取leagueId
        String leagueId = httpSession.getAttribute("leagueId")+"";

        String name = joinLeagueService.getJoinLeagueName(leagueId);
        return name;
    }





}
