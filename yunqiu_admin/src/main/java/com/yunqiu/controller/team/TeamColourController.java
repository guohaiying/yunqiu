package com.yunqiu.controller.team;

import com.yunqiu.controller.common.BaseController;
import com.yunqiu.model.PageCrt;
import com.yunqiu.model.TeamColour;
import com.yunqiu.model.TeamTags;
import com.yunqiu.service.TeamColourService;
import com.yunqiu.service.TeamtagsService;
import com.yunqiu.util.JSONKit;
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
@RequestMapping("/teamcolour")
@EnableAutoConfiguration
public class TeamColourController extends BaseController {
    @Autowired
    private TeamColourService teamColourService;

    JSONKit json= new JSONKit();

    //查找所有
    @RequestMapping("/select")
    public void select( int page , int rows,String sidx,String sord,String colour,HttpServletResponse response) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("colour",colour);

        PageCrt page1 = new PageCrt();
        page1.setPage(page);
        page1.setRows(rows);
        page1.setMap(map);
        page1.setSidx(sidx);
        page1.setSord(sord);

        Integer totalRecord=teamColourService.selectCount(page1);
        int index = ((page - 1) * rows);
        page1.setIndex(index);
        page1.setRecords(totalRecord);
        page1.setTotal(totalRecord % rows == 0 ? totalRecord / rows : totalRecord / rows + 1);
        List<Map> list = teamColourService.selectPaging(page1);
        page1.setList(list);
        PrintWriter out;
        out = json.getPrintWriter(response);
        json.objToJson(page1, out);
    }


    //队服颜色添加
    @ResponseBody
    @RequestMapping("/addTeamColour")
    public Map<String,Object>  addTeamColour(HttpServletResponse response, TeamColour teamColour) {
        Map<String, Object> map = teamColourService.addTeamColour(teamColour);
        return map;
    }

    //队服颜色修改
    @ResponseBody
    @RequestMapping("/upTeamColour")
    public Map<String,Object>  upTeamColour(HttpServletResponse response, TeamColour teamColour) {
        Map<String, Object> map = teamColourService.updatTeamColour(teamColour);
        return map;
    }

    //队服颜色删除
    @ResponseBody
    @RequestMapping("/delTeamColour")
    public Map<String,Object>  delTeamColour(HttpServletResponse response, @RequestParam("teamcolorId") String teamcolorId) {
        Map<String, Object> map = teamColourService.deleteTeamColour(teamcolorId);
        return map;
    }

    //查询所有队服颜色
    @ResponseBody
    @RequestMapping("/getTeamColourList")
    public Map<String,Object>  getTeamColourList(HttpServletResponse response) {
        Map<String, Object> map = teamColourService.getTeamColourList();
        return map;
    }


}
