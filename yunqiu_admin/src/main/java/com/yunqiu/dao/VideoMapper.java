package com.yunqiu.dao;


import com.yunqiu.model.PageCrt;
import com.yunqiu.model.Video;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface VideoMapper {

    /**
     * 添加视频
     * @param video
     */
    @Insert({
            "insert into pw_video(video_id,video_type,video_tag,video_name,video_address_high,video_address_ordinary,video_address_smooth,league_id,game_id,if_show,user_id,createTime,classify,vid,screenshots)\n values(",
            "#{videoId},#{videoType},#{videoTag},#{videoName},#{videoAddressHigh},#{videoAddressOrdinary},#{videoAddressSmooth},#{leagueId},#{gameId},#{ifShow},#{userId},#{createTime},#{classify},#{vid},#{screenshots})"
    })
    int insertVideo(Video video);

    /**
     * 修改视频信息
     * @param video
     */
    @Update("update pw_video set video_type=#{videoType},video_tag=#{videoTag},video_name=#{videoName},video_address_high=#{videoAddressHigh},classify=#{classify},screenshots=#{screenshots},brief=#{brief},vid=#{vid},\n" +
            "video_address_ordinary=#{videoAddressOrdinary},video_address_smooth=#{videoAddressSmooth},league_id=#{leagueId},game_id=#{gameId},if_show=#{ifShow},duration=#{duration} \n" +
            " where video_id=#{videoId}")
    int updateVideo(Video video);

    /**
     * 根据id删除视频
     * @param videoId
     */
    @Delete("DELETE FROM pw_video WHERE video_id = #{videoId}")
    int deleteVideoById(@Param("videoId") String videoId);


    /** 查询总数*/
    @Select({"<script>",
            "select count(*) as count from (select pv.video_id as videoId,pv.video_type as videoType,pv.video_tag as videoTag,pv.video_name as videoName,pv.screenshots,pv.classify,pv.brief,\n" +
                    "pv.video_address_high as videoAddressHigh,pv.video_address_ordinary as videoAddressOrdinary,pv.video_address_smooth as videoAddressSmooth,\n" +
                    "pv.league_id as leagueId,pv.game_id as gameId,pv.if_show as ifShow,pv.createTime,(select user_name from aa_user where admin_id=pv.user_id) as userName,\n" +
                    "(select league_name from pw_league where league_id=pv.league_id) as leagueName\n" +
                    "from pw_video pv ) a where 1=1" +
                    "<when test='map.userName!=null'>",
            "AND a.userName like '%${map.userName}%'",
            "</when>",
            "<when test='map.classify!=null and map.classify!=0'>",
            "AND a.classify = #{map.classify}",
            "</when>",
            "<when test='map.videoType!=null and map.videoType!=0'>",
            "AND a.videoType = #{map.videoType}",
            "</when>",
            "<when test='map.videoTag!=null'>",
            "AND a.videoTag in(${map.videoTag})",
            "</when>",
            "<when test='map.ifShow!=null and map.ifShow!=0'>",
            "AND a.ifShow = #{map.ifShow}",
            "</when>",
            "</script> "})
    @Results({
            @Result(id = true, property = "count", column = "count")
    })
    int selectTotalPage(PageCrt page);


    /** 分页查询*/
    @Select({"<script>",
            "select * from (select pv.video_id as videoId,pv.video_type as videoType,pv.video_tag as videoTag,pv.video_name as videoName,pv.screenshots,pv.classify,pv.brief,pv.vid,\n" +
                    "pv.video_address_high as videoAddressHigh,pv.video_address_ordinary as videoAddressOrdinary,pv.video_address_smooth as videoAddressSmooth,\n" +
                    "pv.league_id as leagueId,pv.game_id as gameId,pv.if_show as ifShow,pv.createTime,(select user_name from aa_user where admin_id=pv.user_id) as userName,\n" +
                    "(select league_name from pw_league where league_id=pv.league_id) as leagueName,(select game_name from pw_tournament where game_id=pv.game_id) as gameName,pv.duration \n" +
                    " from pw_video pv) a where 1=1" +
             "<when test='map.userName!=null'>",
            "AND a.userName like '%${map.userName}%'",
            "</when>",
            "<when test='map.classify!=null and map.classify!=0'>",
            "AND a.classify = #{map.classify}",
            "</when>",
            "<when test='map.videoType!=null and map.videoType!=0'>",
            "AND a.videoType = #{map.videoType}",
            "</when>",
            "<when test='map.videoTag!=null'>",
            "AND a.videoTag in(${map.videoTag})",
            "</when>",
            "<when test='map.ifShow!=null and map.ifShow!=0'>",
            "AND a.ifShow = #{map.ifShow}",
            "</when>",
             "<when test='sidx!=null'>",
            "ORDER BY ${sidx} ${sord}",
            "</when> LIMIT #{index},#{rows}",
            "</script> "})
    @Results({
            @Result(id = true, property = "teamId", column = "team_id")
    })
    List<Map> selectePaging(PageCrt page);


}
