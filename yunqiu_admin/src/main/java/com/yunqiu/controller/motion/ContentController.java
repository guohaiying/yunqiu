package com.yunqiu.controller.motion;

import com.yunqiu.controller.common.BaseController;
import com.yunqiu.model.Content;
import com.yunqiu.model.Global;
import com.yunqiu.model.PageCrt;
import com.yunqiu.model.TeamColour;
import com.yunqiu.service.ContentService;
import com.yunqiu.service.TeamColourService;
import com.yunqiu.util.JSONKit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.web.ResourceProperties;
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
@RequestMapping("/content")
@EnableAutoConfiguration
public class ContentController extends BaseController {
    @Autowired
    private ContentService contentService;

    JSONKit json= new JSONKit();

    //查找所有
    @RequestMapping("/select")
    public void select( int page , int rows,String sidx,String sord,HttpServletResponse response) {
        Map<String, Object> map = new HashMap<String, Object>();

        PageCrt page1 = new PageCrt();
        page1.setPage(page);
        page1.setRows(rows);
        page1.setMap(map);
        page1.setSidx(sidx);
        page1.setSord(sord);

        Integer totalRecord=contentService.selectCount(page1);
        int index = ((page - 1) * rows);
        page1.setIndex(index);
        page1.setRecords(totalRecord);
        page1.setTotal(totalRecord % rows == 0 ? totalRecord / rows : totalRecord / rows + 1);
        List<Map> list = contentService.selectPaging(page1);
        page1.setList(list);
        PrintWriter out;
        out = json.getPrintWriter(response);
        json.objToJson(page1, out);
    }


    @ResponseBody
    @RequestMapping("/addContent")
    public Map<String,Object>  addContent(HttpServletResponse response, Content content,HttpSession httpSession) {

        Map<String,Object> map = (Map<String,Object>)httpSession.getAttribute(Global.USER_SESSION_KEY);
        Map<String,Object> data = (Map<String,Object>)map.get("data");
        String userId = data.get("userId")+"";

        content.setCreate_user(userId);

        Map<String, Object> datamap = contentService.addContent(content);
        return datamap;
    }

    @ResponseBody
    @RequestMapping("/upContent")
    public Map<String,Object>  upTeamContent(HttpServletResponse response, Content content) {
        Map<String, Object> map = contentService.updatContent(content);
        return map;
    }

    @ResponseBody
    @RequestMapping("/delContent")
    public Map<String,Object>  delContent(HttpServletResponse response, @RequestParam("content_id") String content_id) {
        Map<String, Object> map = contentService.deleteContent(content_id);
        return map;
    }


}
