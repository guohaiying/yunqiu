package com.yunqiu.dao;


import com.yunqiu.model.PageCrt;
import com.yunqiu.model.TeamColour;
import com.yunqiu.model.TeamTags;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface TeamColourMapper {

    /**
     * 添加队服颜色
     * @param teamColour
     */
    @Insert({
            "insert into aa_teamcolour(teamcolor_id,colour,createTime) values(",
            "#{teamcolorId},#{colour},#{createTime})"
    })
    int insertTeamColour(TeamColour teamColour);

    /**
     * 修改队服颜色信息
     * @param teamColour
     */
    @Update("update aa_teamcolour set colour=#{colour} where teamcolor_id=#{teamcolorId}")
    int updateTeamColour(TeamColour teamColour);

    /**
     * 根据id删除队服颜色
     * @param teamcolorId
     */
    @Delete("DELETE FROM aa_teamcolour WHERE teamcolor_id = #{teamcolorId}")
    int deleteTeamColourById(@Param("teamcolorId") String teamcolorId);

    /** 查询总数*/
    @Select({"<script>",
            "select count(*) as count from aa_teamcolour a where 1=1 " +
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
            "select * from aa_teamcolour where 1=1 " +
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

    /** 查询所有队服颜色  */
    @Select("select teamcolor_id as teamcolorId,colour as colour from aa_teamcolour")
    @Results({
            @Result(id = true, property = "teamcolorId", column = "teamcolor_id"),
            @Result(property = "colour", column = "colour")
    })
    List<TeamColour> getTeamColourList();

}
