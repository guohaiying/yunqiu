package com.yunqiu.dao;


import com.yunqiu.model.AdminUser;
import com.yunqiu.model.PageCrt;
import com.yunqiu.model.PlayerTags;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface PlayerTagsMapper {

    /**
     * 添加球员标签
     * @param playerTags
     */
    @Insert({
            "insert into aa_playertags(playertags_id,tags_name,createTime) values(",
            "#{playertagsId},#{tagsName},#{createTime})"
    })
    int insertPlayerTags(PlayerTags playerTags);

    /**
     * 修改球员标签信息
     * @param playerTags
     */
    @Update("update aa_playertags set tags_name=#{tagsName} where playertags_id=#{playertagsId}")
    int updatePlayerTags(PlayerTags playerTags);

    /**
     * 根据id删除球员标签
     * @param playertagsId
     */
    @Delete("DELETE FROM aa_playertags WHERE playertags_id = #{playertagsId}")
    int deletePlayerTagsById(@Param("playertagsId") String playertagsId);

    /**
     * 根据id查询球员标签
     * @param playertagsId
     * @return
     */
    @Select("select * from aa_playertags where 1=1 AND playertags_id=#{playertagsId}")
    @Results({
            @Result(id = true, property = "playertagsId", column = "playertags_id"),
            @Result(property = "tagsName", column = "tags_name"),
            @Result(property = "createTime", column = "createTime")
    })
    AdminUser selectPlayerTagsById(@Param("playertagsId") String playertagsId);


    /** 查询总数*/
    @Select({"<script>",
            "select count(*) as count from aa_playertags a where 1=1 " +
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
            "select * from aa_playertags where 1=1 " +
             "<when test='map.tagsName!=null'>",
            "AND tags_name like '%${map.tagsName}%'",
            "</when>",
             "<when test='sidx!=null'>",
            "ORDER BY ${sidx} ${sord}",
            "</when> LIMIT #{index},#{rows}",
            "</script> "})
    @Results({
            @Result(id = true, property = "playertagsId", column = "playertags_id"),
            @Result(property = "tagsName", column = "tags_name"),
            @Result(property = "createTime", column = "createTime")
    })
    List<Map> selectePaging(PageCrt page);

    /** 查询所有球员标签  */
    @Select("select playertags_id as playertagsId,tags_name as tagsName from aa_playertags")
    @Results({
            @Result(id = true, property = "playertagsId", column = "playertagsId"),
            @Result(property = "tagsName", column = "tagsName")
    })
    List<PlayerTags> getPlayerTagsList();

}
