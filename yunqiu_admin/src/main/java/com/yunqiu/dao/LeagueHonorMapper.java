package com.yunqiu.dao;


import com.yunqiu.model.*;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface LeagueHonorMapper {


    @Insert({
            "insert into pw_league_honor(honor_id,honor_name,img_url,creation_time,sorting,league_id,honor_type) values(",
            "#{honorId},#{honorName},#{imgUrl},#{creationTime},#{sorting},#{leagueId},#{honorType})"
    })
    int insertLeagueHonor(LeagueHonor leagueHonor);

    @Insert({
            "insert into pw_user_honor(uson_id,user_id,honor_id,gain_time) values(",
            "#{usonId},#{userId},#{honorId},#{gain_time})"
    })
    int insertUserHonor(UserHonor userHonor);

    @Insert({
            "insert into pw_team_honor(teon_id,team_id,honor_id,gain_time) values(",
            "#{teonId},#{teamId},#{honorId},#{gain_time})"
    })
    int insertTeamHonor(TeamHonor teamHonor);


    @Update("update pw_league_honor set honor_name=#{honorName},img_url=#{imgUrl},sorting=#{sorting},league_id=#{leagueId},honor_type=#{honorType} where honor_id=#{honorId}")
    int updateLeagueHonor(LeagueHonor leagueHonor);

    @Update("update pw_user_honor set user_id=#{glId},honor_id=#{honorId} where uson_id=#{zid}")
    int updateUserHonor(@Param("zid") String zid,@Param("glId") String glId,@Param("honorId") String honorId);

    @Update("update pw_team_honor set team_id=#{glId},honor_id=#{honorId} where teon_id=#{zid}")
    int updateTeamHonor(@Param("zid") String zid,@Param("glId") String glId,@Param("honorId") String honorId);


    @Delete("DELETE FROM pw_league_honor WHERE honor_id = #{honorId}")
    int deleteLeagueHonorById(@Param("honorId") String honorId);

    @Delete("DELETE FROM pw_user_honor WHERE uson_id = #{zid}")
    int deleteUserHonorById(@Param("zid") String zid);

    @Delete("DELETE FROM pw_league_honor WHERE honor_id = #{honorId}")
    int deletelegaueHonorById(@Param("honorId") String honorId);

    @Delete("DELETE FROM pw_team_honor WHERE teon_id = #{zid}")
    int deleteTeamHonorById(@Param("zid") String zid);


    /** 查询总数*/
    @Select({"<script>",
            "select count(*) as count from (select * from (\n" +
                    "select uh.uson_id as zid,uh.gain_time as gainTime,lh.honor_name as honorName,lh.img_url as imgUrl,lh.sorting,u.nickname,\n" +
                    "u.portrait,u.user_id as glId, \n" +
                    "(select team_name from pw_team where team_id=(select team_id from pw_team_member where user_id = u.user_id  limit 1)) as teamName,\n" +
                    "(select badge from pw_team where team_id=(select team_id from pw_team_member where user_id = u.user_id limit 1)) as badge\n" +
                    " from pw_user_honor uh,pw_league_honor lh,pw_users u where uh.honor_id in \n" +
                    "(select honor_id from pw_league_honor where league_id = #{map.leagueId}) \n" +
                    "and uh.honor_id = lh.honor_id and uh.user_id = u.user_id\n" +
                    "\n" +
                    "UNION ALL\n" +
                    "\n" +
                    "select uh.teon_id as zid,uh.gain_time as gainTime,lh.honor_name as honorName,lh.img_url as imgUrl,lh.sorting,'' as nickname,\n" +
                    "'' as portrait,uh.team_id as glId, \n" +
                    "(select team_name from pw_team where team_id=uh.team_id) as teamName,\n" +
                    "(select badge from pw_team where team_id=uh.team_id) as badge\n" +
                    " from pw_team_honor uh,pw_league_honor lh where uh.honor_id in \n" +
                    "(select honor_id from pw_league_honor where league_id = #{map.leagueId}) \n" +
                    "and uh.honor_id = lh.honor_id \n" +
                    "\n" +
                    ") a ) a where 1=1" +
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
            "select * from (\n" +
                    "select uh.uson_id as zid,uh.gain_time as gainTime,lh.honor_name as honorName,lh.img_url as imgUrl,lh.sorting,u.nickname,\n" +
                    "u.portrait,u.user_id as glId, uh.honor_id as honorId, \n" +
                    "(select team_name from pw_team where team_id=(select team_id from pw_team_member where user_id = u.user_id limit 1)) as teamName,\n" +
                    "(select badge from pw_team where team_id=(select team_id from pw_team_member where user_id = u.user_id limit 1)) as badge\n" +
                    " from pw_user_honor uh,pw_league_honor lh,pw_users u where uh.honor_id in \n" +
                    "(select honor_id from pw_league_honor where league_id = #{map.leagueId}) \n" +
                    "and uh.honor_id = lh.honor_id and uh.user_id = u.user_id\n" +
                    "\n" +
                    "UNION ALL\n" +
                    "\n" +
                    "select uh.teon_id as zid,uh.gain_time as gainTime,lh.honor_name as honorName,lh.img_url as imgUrl,lh.sorting,'' as nickname,\n" +
                    "'' as portrait,uh.team_id as glId, uh.honor_id as honorId, \n" +
                    "(select team_name from pw_team where team_id=uh.team_id) as teamName,\n" +
                    "(select badge from pw_team where team_id=uh.team_id) as badge\n" +
                    " from pw_team_honor uh,pw_league_honor lh where uh.honor_id in \n" +
                    "(select honor_id from pw_league_honor where league_id = #{map.leagueId}) \n" +
                    "and uh.honor_id = lh.honor_id \n" +
                    "\n" +
                    ") a " +
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

    @Select("select honor_id as honorId,honor_name as honorName,honor_type as honorType,sorting,img_url as imgUrl from pw_league_honor where league_id =#{leagueId} order by sorting asc")
    @Results({
            @Result(id = true, property = "honorId", column = "honor_id"),
            @Result(property = "honorName", column = "honor_name")
    })
    List<LeagueHonor> getLeagueHonorList(@Param("leagueId") String leagueId);



    @Select("select honor_id as honorId,honor_name as honorName,honor_type as honorType from pw_league_honor where honor_id =#{honorId} ")
    @Results({
            @Result(id = true, property = "honorId", column = "honor_id"),
            @Result(property = "honorName", column = "honor_name")
    })
    LeagueHonor getLeagueHonorById(@Param("honorId") String honorId);


    @Select("select t.team_id as id,t.team_name as name from pw_join_league jl,pw_team t where jl.league_id=#{leagueId} and audit_status=2 and jl.team_id = t.team_id ")
    @Results({
            @Result(id = true, property = "honorId", column = "honor_id"),
            @Result(property = "honorName", column = "honor_name")
    })
    List<Map<String,Object>> getLeagueTeamList(@Param("leagueId") String leagueId);

    @Select("SELECT tm.user_id as id,u.nickname as name from pw_team_member tm,pw_users u where tm.team_id in \n" +
            "(select t.team_id from pw_join_league jl,pw_team t where jl.league_id=#{leagueId} and audit_status=2 and jl.team_id = t.team_id) \n" +
            "and tm.`status`=1 and tm.user_id = u.user_id")
    @Results({
            @Result(id = true, property = "honorId", column = "honor_id"),
            @Result(property = "honorName", column = "honor_name")
    })
    List<Map<String,Object>> getLeagueUserList(@Param("leagueId") String leagueId);

}
