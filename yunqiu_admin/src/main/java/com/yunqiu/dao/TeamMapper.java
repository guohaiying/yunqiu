package com.yunqiu.dao;


import com.yunqiu.model.PageCrt;
import com.yunqiu.model.Team;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface TeamMapper {

    /**
     * 添加球队
     * @param team
     */
    @Insert({
            "insert into pw_team(team_id,team_name,badge,team_type,enter_time,province,city,area,home,status) values(",
            "#{teamId},#{teamName},#{badge},#{teamType},#{enterTime},(select province from sy_province where province_id=#{province}),(select city from sy_city where city_id=#{city}),(select area from sy_area where area_id=#{area}),#{home},#{status})"
    })
    int insertTeam(Team team);

    /**
     * 修改球队信息
     * @param team
     */
    @Update("update pw_team set team_name=#{teamName},badge=#{badge},team_type=#{teamType},background=#{background},\n" +
            "establish_time=#{establishTime},invite=#{invite},province=(select province from sy_province where province_id=#{province}),city=(select city from sy_city where city_id=#{city}),area=(select area from sy_area where area_id=#{area}),home=#{home},label=#{label}\n" +
            "where team_id=#{teamId}")
    int updateTeam(Team team);

    /**
     * 根据id删除球队
     * @param teamId
     */
    @Delete("DELETE FROM pw_team WHERE team_id = #{teamId}")
    int deleteTeamById(@Param("teamId") String teamId);

    /**
     * 根据解散球队
     * @param teamId
     */
    @Update("update pw_team set status=2 \n"+
            "where team_id=#{teamId}")
    int disbandTeam(@Param("teamId") String teamId);



    /** 查询总数*/
    @Select({"<script>",
            "select count(*) as count from (select team_id as teamId,team_name as teamName,badge,team_type as teamType,background,establish_time as establishTime," +
                    "invite,home,enter_time as enterTime,label,status,province,area,city from pw_team ) a where 1=1" +
            "<when test='map.teamName!=null'>",
            "AND teamName like '%${map.teamName}%'",
            "</when>",
            "<when test='map.teamType!=null and map.teamType!=0'>",
            "AND teamType = #{map.teamType}",
            "</when>",
            "<when test='map.status!=null and map.status!=0'>",
            "AND status = #{map.status}",
            "</when>",
            "<when test='map.province!=null'>",
            "AND province = #{map.province}",
            "</when>",
            "<when test='map.city!=null'>",
            "AND city = #{map.city}",
            "</when>",
            "<when test='map.area!=null'>",
            "AND area = #{map.area}",
            "</when>",
            "</script> "})
    @Results({
            @Result(id = true, property = "count", column = "count")
    })
    int selectTotalPage(PageCrt page);


    /** 分页查询*/
    @Select({"<script>",
            "select pt.team_id as teamId,pt.team_name as teamName,pt.badge,pt.team_type as teamType,pt.background,pt.establish_time as establishTime," +
                    "pt.invite,pt.home,pt.enter_time as enterTime,pt.label,pt.status,pt.province,pt.area,pt.city," +
                    "(select count(*) from pw_team_member where `status`=1 and team_id=pt.team_id)  as tpcount," +
                    "(select nickname from pw_users where user_id =(select user_id from pw_team_member where team_id=pt.team_id and status=1 and jurisdiction=1)) as userName" +
                    " from pw_team pt where 1=1 " +
             "<when test='map.teamName!=null'>",
            "AND pt.team_name like '%${map.teamName}%'",
            "</when>",
            "<when test='map.teamType!=null and map.teamType!=0'>",
            "AND pt.team_type = #{map.teamType}",
            "</when>",
            "<when test='map.status!=null and map.status!=0'>",
            "AND pt.status = #{map.status}",
            "</when>",
            "<when test='map.province!=null'>",
            "AND pt.province = #{map.province}",
            "</when>",
            "<when test='map.city!=null'>",
            "AND pt.city = #{map.city}",
            "</when>",
            "<when test='map.area!=null'>",
            "AND pt.area = #{map.area}",
            "</when>",
             "<when test='sidx!=null'>",
            "ORDER BY ${sidx} ${sord}",
            "</when> LIMIT #{index},#{rows}",
            "</script> "})
    @Results({
            @Result(id = true, property = "teamId", column = "team_id"),
            @Result(property = "teamName", column = "team_name"),
            @Result(property = "badge", column = "badge"),
            @Result(property = "teamType", column = "team_type"),
            @Result(property = "background", column = "background"),
            @Result(property = "establishTime", column = "establish_time"),
            @Result(property = "fansNumber", column = "fans_number"),
            @Result(property = "invite", column = "invite"),
            @Result(property = "province", column = "province"),
            @Result(property = "city", column = "city"),
            @Result(property = "area", column = "area"),
            @Result(property = "home", column = "home"),
            @Result(property = "enterTime", column = "enter_time"),
            @Result(property = "label", column = "label"),
            @Result(property = "status", column = "status")
    })
    List<Map> selectePaging(PageCrt page);

    /** 查询所有未解散球队*/
    @Select("select team_id as teamId,team_name as teamName,province from pw_team where status=1")
    @Results({
            @Result(id = true, property = "teamId", column = "teamId"),
            @Result(property = "teamName", column = "teamName")
    })
    List<Team> getTeamList();


}
