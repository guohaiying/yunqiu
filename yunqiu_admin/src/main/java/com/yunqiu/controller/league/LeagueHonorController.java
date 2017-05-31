package com.yunqiu.controller.league;

import com.yunqiu.controller.common.BaseController;
import com.yunqiu.model.LeagueHonor;
import com.yunqiu.model.PageCrt;
import com.yunqiu.model.TeamColour;
import com.yunqiu.service.LeagueHonorService;
import com.yunqiu.service.TeamColourService;
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
@RequestMapping("/leagueHonor")
@EnableAutoConfiguration
public class LeagueHonorController extends BaseController {
    @Autowired
    private LeagueHonorService leagueHonorService;

    JSONKit json= new JSONKit();

    //查找所有
    @RequestMapping("/select")
    public void select( int page , int rows,String sidx,String sord,HttpServletResponse response,HttpSession httpSession) {
        Map<String, Object> map = new HashMap<String, Object>();
        String  leagueId= httpSession.getAttribute("leagueHonor_leagueId")+"";
        map.put("leagueId",leagueId);

        PageCrt page1 = new PageCrt();
        page1.setPage(page);
        page1.setRows(rows);
        page1.setMap(map);
        page1.setSidx(sidx);
        page1.setSord(sord);

        Integer totalRecord=leagueHonorService.selectCount(page1);
        int index = ((page - 1) * rows);
        page1.setIndex(index);
        page1.setRecords(totalRecord);
        page1.setTotal(totalRecord % rows == 0 ? totalRecord / rows : totalRecord / rows + 1);
        List<Map> list = leagueHonorService.selectPaging(page1);
        page1.setList(list);
        PrintWriter out;
        out = json.getPrintWriter(response);
        json.objToJson(page1, out);
    }


    @ResponseBody
    @RequestMapping("/addLeagueHonor")
    public Map<String,Object>  addLeagueHonor(HttpServletResponse response, LeagueHonor leagueHonor,HttpSession httpSession) {
        String  leagueId= httpSession.getAttribute("leagueHonor_leagueId")+"";
        leagueHonor.setLeagueId(leagueId);

        Map<String, Object> map = leagueHonorService.addLeagueHonor(leagueHonor);
        return map;
    }

    @ResponseBody
    @RequestMapping("/addLeagueHonorGL")
    public Map<String,Object>  addLeagueHonorGL(HttpServletResponse response, String honorId,String glId,HttpSession httpSession) {

        Map<String, Object> map = leagueHonorService.addLeagueHonorGL(honorId,glId);
        return map;
    }

    @ResponseBody
    @RequestMapping("/upLeagueHonorGL")
    public Map<String,Object>  upLeagueHonor(HttpServletResponse response, String honorId,String glId,String zid) {
        Map<String, Object> map = leagueHonorService.updatLeagueHonor(honorId,glId,zid);
        return map;
    }

    @ResponseBody
    @RequestMapping("/delLeagueHonor")
    public Map<String,Object>  delLeagueHonor(HttpServletResponse response, @RequestParam("honorId") String honorId) {
        Map<String, Object> map = leagueHonorService.deleteLeagueHonorById(honorId);
        return map;
    }

    @ResponseBody
    @RequestMapping("/getLeagueHonorList")
    public Map<String,Object>  getLeagueHonorList(HttpServletResponse response,HttpSession httpSession) {
        String  leagueId= httpSession.getAttribute("leagueHonor_leagueId")+"";

        Map<String, Object> map = leagueHonorService.getLeagueHonorList(leagueId);
        return map;
    }

    @ResponseBody
    @RequestMapping("/getLeagueTY")
    public Map<String,Object>  getLeagueTY(HttpServletResponse response,String honorId,HttpSession httpSession) {
        String  leagueId= httpSession.getAttribute("leagueHonor_leagueId")+"";

        Map<String, Object> map = leagueHonorService.getLeagueTY(leagueId,honorId);
        return map;
    }



}
