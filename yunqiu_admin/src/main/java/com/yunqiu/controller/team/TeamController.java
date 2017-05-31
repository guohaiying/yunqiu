package com.yunqiu.controller.team;

import com.yunqiu.controller.common.BaseController;
import com.yunqiu.model.PageCrt;
import com.yunqiu.model.Team;
import com.yunqiu.service.TeamService;
import com.yunqiu.util.JSONKit;
import com.yunqiu.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/team")
@EnableAutoConfiguration
public class TeamController extends BaseController {
    @Autowired
    private TeamService teamService;

    JSONKit json= new JSONKit();

    //查找所有
    @RequestMapping("/select")
    public void select( int page , int rows,String sidx,String sord,String teamName,Integer teamType,Integer status,String province,String city,String area,HttpServletResponse response) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("teamName",teamName);
        map.put("teamType",teamType);
        map.put("status",status);
        if(Utils.isNull(province) || province.equals("全部")){
            map.put("province",null);
        }else{
            map.put("province",province);
        }

        if(Utils.isNull(city) || city.equals("全部")){
            map.put("city",null);
        }else{
            map.put("city",city);
        }

        if(Utils.isNull(area) || area.equals("全部")){
            map.put("area",null);
        }else{
            map.put("area",area);
        }

        PageCrt page1 = new PageCrt();
        page1.setPage(page);
        page1.setRows(rows);
        page1.setMap(map);
        page1.setSidx(sidx);
        page1.setSord(sord);

        Integer totalRecord=teamService.selectCount(page1);
        int index = ((page - 1) * rows);
        page1.setIndex(index);
        page1.setRecords(totalRecord);
        page1.setTotal(totalRecord % rows == 0 ? totalRecord / rows : totalRecord / rows + 1);
        List<Map> list = teamService.selectPaging(page1);
        page1.setList(list);
        PrintWriter out;
        out = json.getPrintWriter(response);
        json.objToJson(page1, out);
    }


    //球队添加
    @ResponseBody
    @RequestMapping("/addTeam")
    public Map<String,Object>  addPlace(HttpServletResponse response, Team team) {
        Map<String, Object> map = teamService.addTeam(team);
        return map;
    }

    //球队修改
    @ResponseBody
    @RequestMapping("/upTeam")
    public Map<String,Object>  upTeam(HttpServletResponse response, Team team) {
        Map<String, Object> map = teamService.updatePlace(team);
        return map;
    }

    //解散球队
    @ResponseBody
    @RequestMapping("/disbandTeam")
    public Map<String,Object>  disbandTeam(HttpServletResponse response, @RequestParam("teamId") String teamId) {
        Map<String, Object> map = teamService.disbandTeam(teamId);
        return map;
    }


    //查询所有未解散球队
    @ResponseBody
    @RequestMapping("/getTeamList")
    public Map<String,Object>  getTeamList(HttpServletResponse response) {
        Map<String, Object> map = teamService.getTeamList();
        return map;
    }


}
