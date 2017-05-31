package com.yunqiu.controller.motion;

import com.yunqiu.controller.common.BaseController;
import com.yunqiu.model.Banner;
import com.yunqiu.model.Global;
import com.yunqiu.model.PageCrt;
import com.yunqiu.model.TeamColour;
import com.yunqiu.service.BannerService;
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
@RequestMapping("/banner")
@EnableAutoConfiguration
public class BannerController extends BaseController {
    @Autowired
    private BannerService bannerService;

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

        Integer totalRecord=bannerService.selectCount(page1);
        int index = ((page - 1) * rows);
        page1.setIndex(index);
        page1.setRecords(totalRecord);
        page1.setTotal(totalRecord % rows == 0 ? totalRecord / rows : totalRecord / rows + 1);
        List<Map> list = bannerService.selectPaging(page1);
        page1.setList(list);
        PrintWriter out;
        out = json.getPrintWriter(response);
        json.objToJson(page1, out);
    }


    @ResponseBody
    @RequestMapping("/addBanner")
    public Map<String,Object>  addBanner(HttpServletResponse response, Banner banner,HttpSession httpSession) {
        Map<String,Object> map = (Map<String,Object>)httpSession.getAttribute(Global.USER_SESSION_KEY);
        Map<String,Object> data = (Map<String,Object>)map.get("data");
        String userId = data.get("userId")+"";

        banner.setCreate_user(userId);
        Map<String, Object> datamap = bannerService.addBanner(banner);
        return datamap;
    }

    @ResponseBody
    @RequestMapping("/upBanner")
    public Map<String,Object>  upBanner(HttpServletResponse response, Banner banner) {
        Map<String, Object> map = bannerService.updatBanner(banner);
        return map;
    }

    @ResponseBody
    @RequestMapping("/delBanner")
    public Map<String,Object>  delBanner(HttpServletResponse response, @RequestParam("banner_id") String banner_id) {
        Map<String, Object> map = bannerService.deleteBanner(banner_id);
        return map;
    }



}
