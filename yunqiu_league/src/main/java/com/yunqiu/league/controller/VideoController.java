package com.yunqiu.league.controller;

import com.yunqiu.league.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 视频获取
 */

@RestController
@RequestMapping("/video")
@EnableAutoConfiguration
public class VideoController {
    @Autowired
    private VideoService videoService;

    /**
     * 获取视频
     * @param classify 分类：1：赛事视频 2：比赛视频 3：发现（精彩）视频
     * @return
     */
    @RequestMapping(value = "/selectVideo",method = RequestMethod.GET)
    public Map<String,Object> selectVideo(@RequestParam(value = "id",required = false,defaultValue = "") String id,@RequestParam("classify") int classify,
                                          @RequestParam(value = "pageNum",required = false,defaultValue = "1") int pageNum,
                                          @RequestParam(value = "pageSize",required = false,defaultValue = "20") int pageSize){
        return videoService.selectVideo(id,classify,pageNum,pageSize);
    }
}
