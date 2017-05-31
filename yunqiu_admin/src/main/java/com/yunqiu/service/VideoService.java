package com.yunqiu.service;

import com.yunqiu.model.PageCrt;
import com.yunqiu.model.Video;

import java.util.List;
import java.util.Map;

/**
 * Created by 11366 on 2017/1/14.
 */
public interface VideoService {
    Integer selectCount(PageCrt page);

    List<Map> selectPaging(PageCrt page);

    Map<String, Object> addVideo(Video video);

    Map<String, Object> updateVideo(Video video);

    Map<String, Object> deleteVideo(String videoId,String username,String password,String userId);


}
