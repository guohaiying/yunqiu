package com.yunqiu.controller.league;

import com.yunqiu.controller.common.BaseController;
import com.yunqiu.model.Global;
import com.yunqiu.model.League;
import com.yunqiu.model.PageCrt;
import com.yunqiu.service.LeagueService;
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
@RequestMapping("/leagueLIST")
@EnableAutoConfiguration
public class LeagueLISTController extends BaseController {
    @Autowired
    private LeagueService leagueService;

    JSONKit json= new JSONKit();

    //查找所有
    @RequestMapping("/selectTeam")
    public void selectTeam( int page , int rows,String sidx,String sord,HttpServletResponse response,int type, HttpSession httpSession) {
        Map<String, Object> map = new HashMap<String, Object>();

        //获取leagueId
        String leagueId = httpSession.getAttribute("leagueLIST_leagueId")+"";
        map.put("type",type);
        map.put("leagueId",leagueId);

        PageCrt page1 = new PageCrt();
        page1.setPage(page);
        page1.setRows(rows);
        page1.setMap(map);
        page1.setSidx(sidx);
        page1.setSord(sord);

        Integer totalRecord=leagueService.selectCountTeam(page1);
        int index = ((page - 1) * rows);
        page1.setIndex(index);
        page1.setRecords(totalRecord);
        page1.setTotal(totalRecord % rows == 0 ? totalRecord / rows : totalRecord / rows + 1);
        List<Map> list = leagueService.selectPagingTeam(page1);
        page1.setList(list);
        PrintWriter out;
        out = json.getPrintWriter(response);
        json.objToJson(page1, out);
    }

    //查找所有
    @RequestMapping("/selectUser")
    public void selectUser( int page , int rows,String sidx,String sord,HttpServletResponse response,int type, HttpSession httpSession) {
        Map<String, Object> map = new HashMap<String, Object>();

        //获取leagueId
        String leagueId = httpSession.getAttribute("leagueLIST_leagueId")+"";
        map.put("type",type);
        map.put("leagueId",leagueId);

        PageCrt page1 = new PageCrt();
        page1.setPage(page);
        page1.setRows(rows);
        page1.setMap(map);
        page1.setSidx(sidx);
        page1.setSord(sord);

        Integer totalRecord=leagueService.selectCountUser(page1);
        int index = ((page - 1) * rows);
        page1.setIndex(index);
        page1.setRecords(totalRecord);
        page1.setTotal(totalRecord % rows == 0 ? totalRecord / rows : totalRecord / rows + 1);
        List<Map> list = leagueService.selectPagingUser(page1);
        page1.setList(list);
        PrintWriter out;
        out = json.getPrintWriter(response);
        json.objToJson(page1, out);
    }

}
