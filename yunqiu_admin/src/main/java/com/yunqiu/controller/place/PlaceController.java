package com.yunqiu.controller.place;

import com.yunqiu.controller.common.BaseController;
import com.yunqiu.model.PageCrt;
import com.yunqiu.model.Place;
import com.yunqiu.service.PlaceService;
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
@RequestMapping("/place")
@EnableAutoConfiguration
public class PlaceController extends BaseController {
    @Autowired
    private PlaceService placeService;

    JSONKit json= new JSONKit();

    //查找所有场地
    @RequestMapping("/select")
    public void select( int page , int rows,String sidx,String sord,String placeName,HttpServletResponse response) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("placeName",placeName);

        PageCrt page1 = new PageCrt();
        page1.setPage(page);
        page1.setRows(rows);
        page1.setMap(map);
        page1.setSidx(sidx);
        page1.setSord(sord);

        Integer totalRecord=placeService.selectCount(page1);
        int index = ((page - 1) * rows);
        page1.setIndex(index);
        page1.setRecords(totalRecord);
        page1.setTotal(totalRecord % rows == 0 ? totalRecord / rows : totalRecord / rows + 1);
        List<Map> list = placeService.selectPaging(page1);
        page1.setList(list);
        PrintWriter out;
        out = json.getPrintWriter(response);
        json.objToJson(page1, out);
    }


    //场地添加
    @ResponseBody
    @RequestMapping("/addPlace")
    public Map<String,Object>  addPlace(HttpServletResponse response, Place place) {
        Map<String, Object> map = placeService.addPlace(place);
        return map;
    }

    //场地修改
    @ResponseBody
    @RequestMapping("/upPlace")
    public Map<String,Object>  upPlace(HttpServletResponse response, Place place) {
        Map<String, Object> map = placeService.updatePlace(place);
        return map;
    }

    //场地删除
    @ResponseBody
    @RequestMapping("/delPlace")
    public Map<String,Object>  delPlace(HttpServletResponse response, @RequestParam("placeId") String placeId) {
        Map<String, Object> map = placeService.deletePlace(placeId);
        return map;
    }

    //查询所有场地
    @ResponseBody
    @RequestMapping("/getPlaceList")
    public Map<String,Object>  getProvinceList(HttpServletResponse response) {
        Map<String, Object> map = placeService.getPlaceList();
        return map;
    }


}
