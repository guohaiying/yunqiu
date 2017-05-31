package com.yunqiu.controller.team;

import com.yunqiu.controller.common.BaseController;
import com.yunqiu.model.GameMember;
import com.yunqiu.model.PageCrt;
import com.yunqiu.service.GameMemberService;
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
@RequestMapping("/gameMember")
@EnableAutoConfiguration
public class GameMemberController extends BaseController {
    @Autowired
    private GameMemberService gameMemberService;

    JSONKit json= new JSONKit();

    //查找所有
    @RequestMapping("/select")
    public void select( int page , int rows,String sidx,String sord,HttpServletResponse response,HttpSession httpSession) {
        String  gameId= httpSession.getAttribute("tournamestatus_gameId")+"";
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("gameId",gameId);

        PageCrt page1 = new PageCrt();
        page1.setPage(page);
        page1.setRows(rows);
        page1.setMap(map);
        page1.setSidx(sidx);
        page1.setSord(sord);

        Integer totalRecord=gameMemberService.selectCount(page1);
        int index = ((page - 1) * rows);
        page1.setIndex(index);
        page1.setRecords(totalRecord);
        page1.setTotal(totalRecord % rows == 0 ? totalRecord / rows : totalRecord / rows + 1);
        List<Map> list = gameMemberService.selectPaging(page1);
        page1.setList(list);
        PrintWriter out;
        out = json.getPrintWriter(response);
        json.objToJson(page1, out);
    }


    //修改
    @ResponseBody
    @RequestMapping("/upStatus")
    public Map<String,Object>  upStatus(HttpServletResponse response, GameMember gameMember,HttpSession httpSession) {
        String  gameId= httpSession.getAttribute("tournamestatus_gameId")+"";
        gameMember.setGameId(gameId);
        Map<String, Object> map = gameMemberService.updateStatus(gameMember);
        return map;
    }

}
