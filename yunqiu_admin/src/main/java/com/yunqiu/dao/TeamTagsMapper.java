package com.yunqiu.dao;


import com.yunqiu.model.AdminUser;
import com.yunqiu.model.PageCrt;
import com.yunqiu.model.TeamTags;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface TeamTagsMapper {

    /**
     * 添加球队标签
     * @param teamTags
     */
    @Insert({
            "insert into aa_teamtags(teamtags_id,tags_name,createTime) values(",
            "#{teamtagsId},#{tagsName},#{createTime})"
    })
    int insertTeamTags(TeamTags teamTags);

    /**
     * 修改球队标签信息
     * @param teamTags
     */
    @Update("update aa_teamtags set tags_name=#{tagsName} where teamtags_id=#{teamtagsId}")
    int updateTeamTags(TeamTags teamTags);

    /**
     * 根据id删除球队标签
     * @param teamtagsId
     */
    @Delete("DELETE FROM aa_teamtags WHERE teamtags_id = #{teamtagsId}")
    int deletePlayerTagsById(@Param("teamtagsId") String teamtagsId);

    /**
     * 根据id查询球队标签
     * @param teamtagsId
     * @return
     */
    @Select("select * from aa_teamtags where 1=1 AND teamtags_id=#{teamtagsId}")
    @Results({
            @Result(id = true, property = "teamtagsId", column = "teamtags_id"),
            @Result(property = "tagsName", column = "tags_name"),
            @Result(property = "createTime", column = "createTime")
    })
    AdminUser selectPlayerTagsById(@Param("teamtagsId") String teamtagsId);


    /** 查询总数*/
    @Select({"<script>",
            "select count(*) as count from aa_teamtags a where 1=1 " +
             "<when test='map.tagsName!=null'>",
            "AND tags_name like '%${map.tagsName}%'",
            "</when>",
            "</script> "})
    @Results({
            @Result(id = true, property = "count", column = "count")
    })
    int selectTotalPage(PageCrt page);


    /** 分页查询*/
    @Select({"<script>",
            "select * from aa_teamtags where 1=1 " +
             "<when test='map.tagsName!=null'>",
            "AND tags_name like '%${map.tagsName}%'",
            "</when>",
             "<when test='sidx!=null'>",
            "ORDER BY ${sidx} ${sord}",
            "</when> LIMIT #{index},#{rows}",
            "</script> "})
    @Results({
            @Result(id = true, property = "teamtagsId", column = "teamtags_id"),
            @Result(property = "tagsName", column = "tags_name"),
            @Result(property = "createTime", column = "createTime")
    })
    List<Map> selectePaging(PageCrt page);

    /** 查询所有球队标签  */
    @Select("select teamtags_id as teamtagsId,tags_name as tagsName from aa_teamtags")
    @Results({
            @Result(id = true, property = "teamtagsId", column = "teamtags_id"),
            @Result(property = "tagsName", column = "tagsName")
    })
    List<TeamTags> getTeamtagsList();

}
