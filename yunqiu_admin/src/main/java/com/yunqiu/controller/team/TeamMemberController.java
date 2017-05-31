package com.yunqiu.controller.team;

import com.yunqiu.controller.common.BaseController;
import com.yunqiu.model.PageCrt;
import com.yunqiu.model.TeamMember;
import com.yunqiu.service.TeamMemberService;
import com.yunqiu.util.JSONKit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/teamMember")
@EnableAutoConfiguration
public class TeamMemberController extends BaseController {
    @Autowired
    private TeamMemberService teamMemberService;

    JSONKit json= new JSONKit();

    //查找所有
    @RequestMapping("/select")
    public void select( int page , int rows,String sidx,String sord,HttpServletResponse response,HttpSession httpSession) {

        String  userId= httpSession.getAttribute("team_userId")+"";
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userId",userId);

        PageCrt page1 = new PageCrt();
        page1.setPage(page);
        page1.setRows(rows);
        page1.setMap(map);
        page1.setSidx(sidx);
        page1.setSord(sord);

        Integer totalRecord=teamMemberService.selectCount(page1);
        int index = ((page - 1) * rows);
        page1.setIndex(index);
        page1.setRecords(totalRecord);
        page1.setTotal(totalRecord % rows == 0 ? totalRecord / rows : totalRecord / rows + 1);
        List<Map> list = teamMemberService.selectPaging(page1);
        page1.setList(list);
        PrintWriter out;
        out = json.getPrintWriter(response);
        json.objToJson(page1, out);
    }


    //查找所有球员
    @RequestMapping("/selectAllTeamUser")
    public void selectAllTeamUser( int page , int rows,String sidx,String sord,HttpServletResponse response,HttpSession httpSession) {

        String  teamId= httpSession.getAttribute("team_teamId")+"";
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("teamId",teamId);

        PageCrt page1 = new PageCrt();
        page1.setPage(page);
        page1.setRows(rows);
        page1.setMap(map);
        page1.setSidx(sidx);
        page1.setSord(sord);

        Integer totalRecord=teamMemberService.selectAllTeamUserCount(page1);
        int index = ((page - 1) * rows);
        page1.setIndex(index);
        page1.setRecords(totalRecord);
        page1.setTotal(totalRecord % rows == 0 ? totalRecord / rows : totalRecord / rows + 1);
        List<Map> list = teamMemberService.selectAllTeamUserPaging(page1);
        page1.setList(list);
        PrintWriter out;
        out = json.getPrintWriter(response);
        json.objToJson(page1, out);
    }

    //加入球队
    @ResponseBody
    @RequestMapping("/addTeamMember")
    public Map<String,Object>  addTeamMember(HttpServletResponse response, TeamMember teamMember,HttpSession httpSession) {

        String  userId= httpSession.getAttribute("team_userId")+"";
        teamMember.setUserId(userId);

        Map<String, Object> datamap = teamMemberService.addTeamMember(teamMember);
        return datamap;
    }


    //加入球队
    @ResponseBody
    @RequestMapping("/addTeamMemberU")
    public Map<String,Object>  addTeamMemberU(HttpServletResponse response, TeamMember teamMember,HttpSession httpSession) {
        String  teamId= httpSession.getAttribute("team_teamId")+"";
        teamMember.setTeamId(teamId);

        Map<String, Object> datamap = teamMemberService.addTeamMember(teamMember);
        return datamap;
    }

    //修改
    @ResponseBody
    @RequestMapping("/updateTeamMember")
    public Map<String,Object>  updateTeamMember(HttpServletResponse response, TeamMember teamMember,HttpSession httpSession) {
        String  teamId= httpSession.getAttribute("team_teamId")+"";
        teamMember.setTeamId(teamId);
        Map<String, Object> datamap = teamMemberService.updateTeamMember(teamMember);
        return datamap;
    }

    //修改队员状态
    @ResponseBody
    @RequestMapping("/updateTeamMemberStatus")
    public Map<String,Object>  updateTeamMemberStatus(HttpServletResponse response, TeamMember teamMember,HttpSession httpSession) {

        String  teamId= httpSession.getAttribute("team_teamId")+"";
        teamMember.setTeamId(teamId);

        Map<String, Object> datamap = teamMemberService.updateTeamMemberStatus(teamMember);
        return datamap;
    }

    //审批队员
    @ResponseBody
    @RequestMapping("/updateShenpi")
    public Map<String,Object>  updateShenpi(HttpServletResponse response, TeamMember teamMember,HttpSession httpSession) {

        String  teamId= httpSession.getAttribute("team_teamId")+"";
        teamMember.setTeamId(teamId);

        Map<String, Object> datamap = teamMemberService.updateShenpi(teamMember);
        return datamap;
    }


    //获取球队名称
    @ResponseBody
    @RequestMapping("/getTeamName")
    public Map<String,Object> getTeamName(HttpServletResponse response, TeamMember teamMember,HttpSession httpSession) {

        String  teamId= httpSession.getAttribute("team_teamId")+"";
        Map<String, Object> datamap = teamMemberService.getTeamName(teamId);
        return datamap;
    }

    //获取球队名称
    @ResponseBody
    @RequestMapping("/getUserName")
    public Map<String,Object> getUserName(HttpServletResponse response, TeamMember teamMember,HttpSession httpSession) {

        String  userId= httpSession.getAttribute("team_userId")+"";
        Map<String, Object> datamap = teamMemberService.getUserName(userId);
        return datamap;
    }


}
