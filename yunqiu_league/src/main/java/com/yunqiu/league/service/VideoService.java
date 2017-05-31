package com.yunqiu.league.service;

import java.util.Map;

/**
 * Created by 11366 on 2017/3/2.
 */
public interface VideoService {
    Map<String,Object> selectVideo(String id,int classify,int pageNum,int pageSize);
}
