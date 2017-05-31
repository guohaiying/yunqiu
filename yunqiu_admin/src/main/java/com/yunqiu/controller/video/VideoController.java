package com.yunqiu.controller.video;

import com.yunqiu.controller.common.BaseController;
import com.yunqiu.model.Global;
import com.yunqiu.model.PageCrt;
import com.yunqiu.model.Video;
import com.yunqiu.service.VideoService;
import com.yunqiu.util.JSONKit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/video")
@EnableAutoConfiguration
public class VideoController extends BaseController {
    @Autowired
    private VideoService videoService;

    JSONKit json= new JSONKit();

    //查找所有
    @RequestMapping("/select")
    public void select( int page , int rows,String sidx,String sord,String userName,String videoType,String videoTag,String ifShow,HttpServletResponse response,HttpSession httpSession) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userName",userName);
        map.put("videoType",videoType);
        if(videoTag==""){
            map.put("videoTag",null);
        }else{
            if(videoTag!=null){
                videoTag = videoTag.substring(0,videoTag.length()-1);
            }
            map.put("videoTag",videoTag);
        }

        map.put("ifShow",ifShow);
        String  classify= httpSession.getAttribute("video_classify")+"";
        if (classify.equals("null")) {
            map.put("classify",0);
        }else{
            map.put("classify",Integer.parseInt(classify));
        }


        PageCrt page1 = new PageCrt();
        page1.setPage(page);
        page1.setRows(rows);
        page1.setMap(map);
        page1.setSidx(sidx);
        page1.setSord(sord);

        Integer totalRecord=videoService.selectCount(page1);
        int index = ((page - 1) * rows);
        page1.setIndex(index);
        page1.setRecords(totalRecord);
        page1.setTotal(totalRecord % rows == 0 ? totalRecord / rows : totalRecord / rows + 1);
        List<Map> list = videoService.selectPaging(page1);
        page1.setList(list);
        PrintWriter out;
        out = json.getPrintWriter(response);
        json.objToJson(page1, out);
    }


    //视频添加
    @ResponseBody
    @RequestMapping("/addVideo")
    public Map<String,Object>  addVideo(HttpServletResponse response, Video video, HttpSession httpSession) {
        Map<String,Object> map = (Map<String,Object>)httpSession.getAttribute(Global.USER_SESSION_KEY);
        Map<String,Object> data = (Map<String,Object>)map.get("data");
        String userId = data.get("userId")+"";
        video.setUserId(userId);

        Map<String, Object> datamap = videoService.addVideo(video);
        return datamap;
    }

    //视频修改
    @ResponseBody
    @RequestMapping("/upVideo")
    public Map<String,Object>  upVideo(HttpServletResponse response, Video video) {
        Map<String, Object> map = videoService.updateVideo(video);
        return map;
    }

    //视频删除
    @ResponseBody
    @RequestMapping("/delVideo")
    public Map<String,Object>  delVideo(HttpSession httpSession,HttpServletResponse response, @RequestParam("videoId") String videoId,@RequestParam("username") String username,@RequestParam("password") String password) {
        Map<String,Object> map = (Map<String,Object>)httpSession.getAttribute(Global.USER_SESSION_KEY);
        Map<String,Object> data = (Map<String,Object>)map.get("data");
        String userId = data.get("userId")+"";
        Map<String, Object> datamap = videoService.deleteVideo(videoId,username,password,userId);
        return datamap;
    }



}
