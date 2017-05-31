package com.yunqiu.controller.team;

import com.yunqiu.controller.common.BaseController;
import com.yunqiu.model.PageCrt;
import com.yunqiu.model.PlayerTags;
import com.yunqiu.service.PlayertagsService;
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
@RequestMapping("/playertags")
@EnableAutoConfiguration
public class PlayerTagsController extends BaseController {
    @Autowired
    private PlayertagsService playertagsService;

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

        Integer totalRecord=playertagsService.selectCount(page1);
        int index = ((page - 1) * rows);
        page1.setIndex(index);
        page1.setRecords(totalRecord);
        page1.setTotal(totalRecord % rows == 0 ? totalRecord / rows : totalRecord / rows + 1);
        List<Map> list = playertagsService.selectPaging(page1);
        page1.setList(list);
        PrintWriter out;
        out = json.getPrintWriter(response);
        json.objToJson(page1, out);
    }


    //球员标签添加
    @ResponseBody
    @RequestMapping("/addPlayerTags")
    public Map<String,Object>  addPlayerTags(HttpServletResponse response, PlayerTags playerTags) {
        Map<String, Object> map = playertagsService.addPlayertags(playerTags);
        return map;
    }

    //球员标签修改
    @ResponseBody
    @RequestMapping("/upPlayerTags")
    public Map<String,Object>  upPlayerTags(HttpServletResponse response, PlayerTags playerTags) {
        Map<String, Object> map = playertagsService.updatePlayertags(playerTags);
        return map;
    }

    //标签删除
    @ResponseBody
    @RequestMapping("/delPlayerTags")
    public Map<String,Object>  delPlayerTags(HttpServletResponse response, @RequestParam("playertagsId") String playertagsId) {
        Map<String, Object> map = playertagsService.deletePlayertags(playertagsId);
        return map;
    }

    //查询所有球员标签
    @ResponseBody
    @RequestMapping("/getPlayerTagsList")
    public Map<String,Object>  getProvinceList(HttpServletResponse response) {
        Map<String, Object> map = playertagsService.getPlayertagsList();
        return map;
    }


}
