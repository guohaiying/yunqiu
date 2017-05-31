package com.yunqiu.dao;

import com.yunqiu.model.Area;
import com.yunqiu.model.City;
import com.yunqiu.model.Province;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 省、市、区/县数据业务
 */

@Mapper
public interface RegionMapper {
    /**
     * 查询省份
     * @return
     */
    @Select("select * from sy_province")
    List<Province> selectProvince();

    /**
     * 根据省份id查询市
     * @param province_id
     * @return
     */
    @Select("select * from sy_city where province_id=#{province_id}")
    List<City> selectCity(@Param("province_id") String province_id);

    /**
     * 根据市级id查询区/县
     * @param city_id
     * @return
     */
    @Select("select * from sy_area where city_id=#{city_id}")
    List<Area> selectArea(@Param("city_id") String city_id);

    /**
     * 查询场地
     * @return
     */
    @Select("select place_name from aa_place")
    List<String> selectPlace();
}
