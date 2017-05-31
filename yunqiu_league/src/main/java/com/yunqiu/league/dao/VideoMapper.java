package com.yunqiu.league.dao;

import com.yunqiu.league.dao.sqlProvider.VideoSqlProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

/**
 * Created by 11366 on 2017/3/2.
 */
@Mapper
public interface VideoMapper {
    @SelectProvider(type = VideoSqlProvider.class,method = "selectVide")
    List<Map<String,Object>> selectVideo(Map<String,Object> params);

    @SelectProvider(type = VideoSqlProvider.class,method = "selectVideTotal")
    int selectVideoTotal(Map<String,Object> params);
}
