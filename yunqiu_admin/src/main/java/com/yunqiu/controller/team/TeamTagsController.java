package com.yunqiu.controller.team;

import com.yunqiu.controller.common.BaseController;
import com.yunqiu.model.PageCrt;
import com.yunqiu.model.PlayerTags;
import com.yunqiu.model.TeamTags;
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
@RequestMapping("/teamtags")
@EnableAutoConfiguration
public class TeamTagsController extends BaseController {
    @Autowired
    private TeamtagsService teamtagsService;

    JSONKit json= new JSONKit();

    //查找所有
    @RequestMapping("/select")
    public void select( int page , int rows,String sidx,String sord,String tagsName,HttpServletResponse response) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("tagsName",tagsName);

        PageCrt page1 = new PageCrt();
        page1.setPage(page);
        page1.setRows(rows);
        page1.setMap(map);
        page1.setSidx(sidx);
        page1.setSord(sord);

        Integer totalRecord=teamtagsService.selectCount(page1);
        int index = ((page - 1) * rows);
        page1.setIndex(index);
        page1.setRecords(totalRecord);
        page1.setTotal(totalRecord % rows == 0 ? totalRecord / rows : totalRecord / rows + 1);
        List<Map> list = teamtagsService.selectPaging(page1);
        page1.setList(list);
        PrintWriter out;
        out = json.getPrintWriter(response);
        json.objToJson(page1, out);
    }


    //球队伍标签添加
    @ResponseBody
    @RequestMapping("/addTeamTags")
    public Map<String,Object>  addTeamTags(HttpServletResponse response, TeamTags teamTags) {
        Map<String, Object> map = teamtagsService.addTeamtags(teamTags);
        return map;
    }

    //球队标签修改
    @ResponseBody
    @RequestMapping("/upTeamTags")
    public Map<String,Object>  upTeamTags(HttpServletResponse response, TeamTags teamTags) {
        Map<String, Object> map = teamtagsService.updatTeamtags(teamTags);
        return map;
    }

    //标签删除
    @ResponseBody
    @RequestMapping("/delTeamTags")
    public Map<String,Object>  delTeamTags(HttpServletResponse response, @RequestParam("teamtagsId") String teamtagsId) {
        Map<String, Object> map = teamtagsService.deleteTeamtags(teamtagsId);
        return map;
    }

    //查询所有球队标签
    @ResponseBody
    @RequestMapping("/getTeamTagsList")
    public Map<String,Object>  getTeamTagsList(HttpServletResponse response) {
        Map<String, Object> map = teamtagsService.getTeamtagsList();
        return map;
    }


}
