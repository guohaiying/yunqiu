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
@RequestMapping("/league")
@EnableAutoConfiguration
public class LeagueController extends BaseController {
    @Autowired
    private LeagueService leagueService;

    JSONKit json= new JSONKit();

    //查找所有
    @RequestMapping("/select")
    public void select( int page , int rows,String sidx,String sord,String leagueName,String leagueAbbreviation,String leagueType,String gameSystem,String status,Integer ifOpen,HttpServletResponse response) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("leagueName",leagueName);
        map.put("leagueAbbreviation",leagueAbbreviation);
        //map.put("leagueType",leagueType);
        if(leagueType==""){
            map.put("leagueType",null);
        }else{
            if(leagueType!=null){
                leagueType = leagueType.substring(0,leagueType.length()-1);
            }
            map.put("leagueType",leagueType);
        }
        //map.put("gameSystem",gameSystem);
        if(gameSystem==""){
            map.put("gameSystem",null);
        }else{
            if(gameSystem!=null){
                gameSystem = gameSystem.substring(0,gameSystem.length()-1);
            }
            map.put("gameSystem",gameSystem);
        }
        //map.put("status",status);
        if(status==""){
            map.put("status",null);
        }else{
            if(status!=null){
                status = status.substring(0,status.length()-1);
            }
            map.put("status",status);
        }
        map.put("ifOpen",ifOpen);

        PageCrt page1 = new PageCrt();
        page1.setPage(page);
        page1.setRows(rows);
        page1.setMap(map);
        page1.setSidx(sidx);
        page1.setSord(sord);

        Integer totalRecord=leagueService.selectCount(page1);
        int index = ((page - 1) * rows);
        page1.setIndex(index);
        page1.setRecords(totalRecord);
        page1.setTotal(totalRecord % rows == 0 ? totalRecord / rows : totalRecord / rows + 1);
        List<Map> list = leagueService.selectPaging(page1);
        page1.setList(list);
        PrintWriter out;
        out = json.getPrintWriter(response);
        json.objToJson(page1, out);
    }


    //赛事添加
    @ResponseBody
    @RequestMapping("/addLeague")
    public Map<String,Object>  addLeague(HttpServletResponse response, League league) {
        Map<String, Object> map = leagueService.addLeague(league);
        return map;
    }

    //修改赛事举办单位
    @ResponseBody
    @RequestMapping("updateHostUnit")
    public Map<String,Object>  updateHostUnit(HttpServletResponse response, League league) {
        Map<String, Object> map = leagueService.updateHostUnit(league);
        return map;
    }

    //修改赛事场地
    @ResponseBody
    @RequestMapping("updateLeagueSite")
    public Map<String,Object>  updateLeagueSite(HttpServletResponse response, League league) {
        Map<String, Object> map = leagueService.updateLeagueSite(league);
        return map;
    }


    //获取赛事信息
    @ResponseBody
    @RequestMapping("getHostUnit")
    public League  getHostUnit(HttpServletResponse response,String leagueId) {
        League league = leagueService.getHostUnit(leagueId);
        return league;
    }

    //赛事修改
    @ResponseBody
    @RequestMapping("/upLeague")
    public Map<String,Object>  upPlace(HttpServletResponse response, League league) {
        Map<String, Object> map = leagueService.updateLeague(league);
        return map;
    }

    //赛事关闭
    @ResponseBody
    @RequestMapping("/closeLeague")
    public Map<String,Object>  closeLeague(HttpSession httpSession, HttpServletResponse response, @RequestParam("leagueId") String leagueId, @RequestParam("username") String username, @RequestParam("password") String password) {
        Map<String,Object> map = (Map<String,Object>)httpSession.getAttribute(Global.USER_SESSION_KEY);
        Map<String,Object> data = (Map<String,Object>)map.get("data");
        String userId = data.get("userId")+"";
        Map<String, Object> datamap = leagueService.closeLeague(leagueId,username,password,userId);
        return datamap;
    }


    //获取所有赛事
    @ResponseBody
    @RequestMapping("/getLeagueList")
    public Map<String,Object>  getLeagueList(HttpServletResponse response) {
        Map<String, Object> map = leagueService.getLeagueList();
        return map;
    }

}
