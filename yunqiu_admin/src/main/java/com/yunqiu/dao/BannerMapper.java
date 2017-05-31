package com.yunqiu.dao;


import com.yunqiu.model.Banner;
import com.yunqiu.model.PageCrt;
import com.yunqiu.model.TeamColour;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface BannerMapper {

    @Insert({
            "insert into aa_banner(banner_id,img_url,article_url,article_content,is_show,create_time,sort,create_user) values(",
            "#{banner_id},#{img_url},#{article_url},#{article_content},#{is_show},#{create_time},#{sort},#{create_user})"
    })
    int insertBanner(Banner banner);

    @Update("update aa_banner set img_url=#{img_url},article_url=#{article_url},article_content=#{article_content},is_show=#{is_show},sort=#{sort} where banner_id=#{banner_id}")
    int updateBanner(Banner banner);

    @Delete("DELETE FROM aa_banner WHERE banner_id = #{banner_id}")
    int deleteBannerById(@Param("banner_id") String banner_id);

    /** 查询总数*/
    @Select({"<script>",
            "select count(*) as count from aa_banner a where 1=1 " +
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
            "select *,(select user_name from aa_user where admin_id = create_user) as userName from aa_banner where 1=1 " +
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
