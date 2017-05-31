package com.yunqiu.dao;


import com.yunqiu.model.AdminUser;
import com.yunqiu.model.PageCrt;
import com.yunqiu.model.Place;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface PlaceMapper {

    /**
     * 添加场地
     * @param place
     */
    @Insert({
            "insert into aa_place(place_id,place_name,createTime) values(",
            "#{placeId},#{placeName},#{createTime})"
    })
    int insertPlace(Place place);

    /**
     * 修改场地信息
     * @param place
     */
    @Update("update aa_place set place_name=#{placeName} where place_id=#{placeId}")
    int updatePlace(Place place);

    /**
     * 根据id删除场地
     * @param placeId
     */
    @Delete("DELETE FROM aa_place WHERE place_id = #{placeId}")
    int deletePlaceById(@Param("placeId") String placeId);

    /**
     * 根据id查询场地
     * @param placeId
     * @return
     */
    @Select("select * from aa_place where 1=1 AND place_id=#{placeId}")
    @Results({
            @Result(id = true, property = "placeId", column = "place_id"),
            @Result(property = "placeName", column = "place_name"),
            @Result(property = "createTime", column = "createTime")
    })
    AdminUser selectPlaceById(@Param("placeId") String placeId);


    /** 查询总数*/
    @Select({"<script>",
            "select count(*) as count from aa_place a where 1=1 " +
             "<when test='map.placeName!=null'>",
            "AND place_name like '%${map.placeName}%'",
            "</when>",
            "</script> "})
    @Results({
            @Result(id = true, property = "count", column = "count")
    })
    int selectTotalPage(PageCrt page);


    /** 分页查询*/
    @Select({"<script>",
            "select * from aa_place where 1=1 " +
             "<when test='map.placeName!=null'>",
            "AND place_name like '%${map.placeName}%'",
            "</when>",
             "<when test='sidx!=null'>",
            "ORDER BY ${sidx} ${sord}",
            "</when> LIMIT #{index},#{rows}",
            "</script> "})
    @Results({
            @Result(id = true, property = "placeId", column = "place_id"),
            @Result(property = "placeName", column = "place_name"),
            @Result(property = "createTime", column = "createTime")
    })
    List<Map> selectePaging(PageCrt page);

    /** 查询所有场地  */
    @Select("select place_id as placeId,place_name as placeName from aa_place")
    @Results({
            @Result(id = true, property = "placeId", column = "placeId"),
            @Result(property = "placeName", column = "placeName")
    })
    List<Place> getPlaceList();

}
