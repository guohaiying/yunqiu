package com.yunqiu.controller.league;

import com.yunqiu.controller.common.BaseController;
import com.yunqiu.model.Global;
import com.yunqiu.model.PageCrt;
import com.yunqiu.model.Schedule;
import com.yunqiu.model.ScheduleRelateGame;
import com.yunqiu.service.ScheduleService;
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
@RequestMapping("/schedule")
@EnableAutoConfiguration
public class ScheduleController extends BaseController {
    @Autowired
    private ScheduleService scheduleService;

    JSONKit json= new JSONKit();

    //添加轮次
    @ResponseBody
    @RequestMapping("/addSchedule")
    public Map<String,Object>  addSchedule(HttpServletResponse response, Schedule schedule,HttpSession httpSession) {

        String  leagueId= httpSession.getAttribute("tournament_leagueId")+"";
        schedule.setLeagueId(leagueId);
        Map<String, Object> map = scheduleService.addSchedule(schedule);
        return map;
    }

    //轮次删除
    @ResponseBody
    @RequestMapping("/delSchedule")
    public Map<String,Object>  delSchedule(HttpServletResponse response, @RequestParam("scheduleId") String scheduleId) {
        Map<String, Object> map = scheduleService.deleteSchedule(scheduleId);
        return map;
    }

    //获取所有轮次
    @ResponseBody
    @RequestMapping("/getScheduleList")
    public Map<String,Object>  getScheduleList(HttpServletResponse response,HttpSession httpSession) {
        String  leagueId= httpSession.getAttribute("tournament_leagueId")+"";
        Map<String, Object> map = scheduleService.getScheduleList(leagueId);
        return map;
    }

    //添加赛程
    @ResponseBody
    @RequestMapping("/addScheduleRelateGame")
    public Map<String,Object>  addScheduleRelateGame(HttpServletResponse response, ScheduleRelateGame scheduleRelateGame, HttpSession httpSession) {

        Map<String, Object> map = scheduleService.addScheduleRelateGame(scheduleRelateGame);
        return map;
    }

    //查找所有
    @RequestMapping("/select")
    public void select( int page , int rows,String sidx,String sord,HttpServletResponse response,HttpSession httpSession) {
        Map<String, Object> map = new HashMap<String, Object>();
        String  leagueId= httpSession.getAttribute("tournament_leagueId")+"";
        map.put("leagueId",leagueId);

        PageCrt page1 = new PageCrt();
        page1.setPage(page);
        page1.setRows(rows);
        page1.setMap(map);
        page1.setSidx(sidx);
        page1.setSord(sord);

        Integer totalRecord=scheduleService.selectCount(page1);
        int index = ((page - 1) * rows);
        page1.setIndex(index);
        page1.setRecords(totalRecord);
        page1.setTotal(totalRecord % rows == 0 ? totalRecord / rows : totalRecord / rows + 1);
        List<Map> list = scheduleService.selectPaging(page1);
        page1.setList(list);
        PrintWriter out;
        out = json.getPrintWriter(response);
        json.objToJson(page1, out);
    }

    //修改赛程信息
    @ResponseBody
    @RequestMapping("updateScheduleRelateGame")
    public Map<String,Object>  updateScheduleRelateGame(HttpServletResponse response, ScheduleRelateGame scheduleRelateGame) {
        Map<String, Object> map = scheduleService.updateScheduleRelateGame(scheduleRelateGame);
        return map;
    }


    //赛程删除
    @ResponseBody
    @RequestMapping("/delScheduleRelateGame")
    public Map<String,Object>  delScheduleRelateGame(HttpSession httpSession,HttpServletResponse response, @RequestParam("relateId") String relateId,@RequestParam("username") String username,@RequestParam("password") String password) {

        Map<String,Object> map = (Map<String,Object>)httpSession.getAttribute(Global.USER_SESSION_KEY);
        Map<String,Object> data = (Map<String,Object>)map.get("data");
        String userId = data.get("userId")+"";
        Map<String, Object> datamap = scheduleService.deleteScheduleRelateGame(relateId,username,password,userId);
        return datamap;
    }



}
