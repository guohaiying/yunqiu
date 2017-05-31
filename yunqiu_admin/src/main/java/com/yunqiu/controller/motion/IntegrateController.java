package com.yunqiu.controller.motion;

import com.yunqiu.controller.common.BaseController;
import com.yunqiu.model.Banner;
import com.yunqiu.model.Global;
import com.yunqiu.model.Integrate;
import com.yunqiu.model.PageCrt;
import com.yunqiu.service.BannerService;
import com.yunqiu.service.IntegrateService;
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
@RequestMapping("/integrate")
@EnableAutoConfiguration
public class IntegrateController extends BaseController {
    @Autowired
    private IntegrateService integrateService;

    JSONKit json= new JSONKit();


    @ResponseBody
    @RequestMapping("/addIntegrate")
    public Map<String,Object>  addIntegrate(HttpServletResponse response, Integrate integrate,HttpSession httpSession) {
        Map<String, Object> datamap = integrateService.addIntegrate(integrate);
        return datamap;
    }

    @ResponseBody
    @RequestMapping("/upIntegrate")
    public Map<String,Object>  upIntegrate(HttpServletResponse response, Integrate integrate) {
        Map<String, Object> map = integrateService.updatIntegrate(integrate);
        return map;
    }

    @ResponseBody
    @RequestMapping("/getIntegrate")
    public Integrate getIntegrate(HttpServletResponse response,String id ) {
        Integrate integrate = integrateService.findIdIntegrate(id);
        return integrate;
    }



}
