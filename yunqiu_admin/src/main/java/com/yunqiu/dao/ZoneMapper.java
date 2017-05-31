package com.yunqiu.dao;


import com.yunqiu.model.Area;
import com.yunqiu.model.City;
import com.yunqiu.model.Province;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface ZoneMapper {

    /** 查询所有省*/
    @Select("select id,province_id as provinceId,province from sy_province")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "provinceId", column = "province_id"),
            @Result(property = "province", column = "province")
    })
    List<Province> selecteAllProvince();

    /** 查询市*/
    @Select("select id,city_id as cityId,city,province_id as provinceId from sy_city where province_id =#{provinceId}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "cityId", column = "city_id"),
            @Result(property = "city", column = "city"),
            @Result(property = "provinceId", column = "provinceId")
    })
    List<City> getCityList(@Param("provinceId") String provinceId);

    /** 查询区/县*/
    @Select("select id,area_id as areaId,area,city_id as cityId from sy_area where city_id = #{cityId}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "areaId", column = "area_id"),
            @Result(property = "cityId", column = "city_id"),
            @Result(property = "area", column = "area")
    })
    List<Area> getAreaList(@Param("cityId") String cityId);



}
