package com.yunqiu.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

/**
 * Created by 11366 on 2017/3/11.
 */
@Mapper
public interface AppVersionMapper {

    @Select("SELECT * FROM aa_version WHERE update_status=1 ORDER BY upload_time DESC LIMIT 1")
    Map<String,Object> selectAppVersion();
}
