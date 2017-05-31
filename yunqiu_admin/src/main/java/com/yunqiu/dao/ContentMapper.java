package com.yunqiu.dao;


import com.yunqiu.model.Banner;
import com.yunqiu.model.Content;
import com.yunqiu.model.PageCrt;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface ContentMapper {

    @Insert({
            "insert into pw_content(content_id,content_title,img_url,content,browse_number,create_time,sort,create_user,is_show) values(",
            "#{content_id},#{content_title},#{img_url},#{content},#{browse_number},#{create_time},#{sort},#{create_user},#{is_show})"
    })
    int insertContent(Content content);

    @Update("update pw_content set content_title=#{content_title},img_url=#{img_url},content=#{content},browse_number=#{browse_number},sort=#{sort},is_show=#{is_show} where content_id=#{content_id}")
    int updateContent(Content content);

    @Delete("DELETE FROM pw_content WHERE content_id = #{content_id}")
    int deleteContentById(@Param("content_id") String content_id);

    /** 查询总数*/
    @Select({"<script>",
            "select count(*) as count from pw_content a where 1=1 " +
             "<when test='map.colour!=null'>",
            "AND colour like '%${map.colour}%'",
            "</when>",
            "</script> "})
    @Results({
            @Result(id = true, property = "count", column = "count")
    })
    int selectTotalPage(PageCrt page);


    /** 分页查询*/
    @Select({"<script>",
            "select *,(select user_name from aa_user where admin_id = create_user) as userName from pw_content where 1=1 " +
             "<when test='map.colour!=null'>",
            "AND colour like '%${map.colour}%'",
            "</when>",
             "<when test='sidx!=null'>",
            "ORDER BY ${sidx} ${sord}",
            "</when> LIMIT #{index},#{rows}",
            "</script> "})
    @Results({
            @Result(id = true, property = "teamcolorId", column = "teamcolor_id"),
            @Result(property = "colour", column = "colour"),
            @Result(property = "createTime", column = "createTime")
    })
    List<Map> selectePaging(PageCrt page);


}
