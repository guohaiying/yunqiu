package com.yunqiu.dao;


import com.yunqiu.model.League;
import com.yunqiu.model.PageCrt;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface LeagueMapper {

    /**
     * 添加赛事
     * @param league
     */
    @Insert({
            "insert into pw_league(league_id,league_abbreviation,league_name,introduce,league_icon,background,direct,sponsor,undertake,assisting,start_time,\n" +
                    "end_time,apply_start_time,apply_end_time,game_system,league_type,league_site,province,city,area,status,if_notice,if_open) values(",
            "#{leagueId},#{leagueAbbreviation},#{leagueName},#{introduce},#{leagueIcon},#{background},#{direct},#{sponsor},#{undertake},#{assisting},#{startTime}," +
                    "#{endTime},#{applyStartTime},#{applyEndTime},#{gameSystem},#{leagueType},#{leagueSite},(select province from sy_province where province_id=#{province}),(select city from sy_city where city_id=#{city}),(select area from sy_area where area_id=#{area}),#{status},#{ifNotice},#{ifOpen})"
    })
    int insertLeague(League league);

    /**
     * 修改赛事信息
     * @param league
     */
    @Update("update pw_league set league_abbreviation=#{leagueAbbreviation},league_name=#{leagueName},introduce=#{introduce},news=#{news},league_icon=#{leagueIcon},background=#{background},\n" +
            "start_time=#{startTime},end_time=#{endTime},apply_start_time=#{applyStartTime},apply_end_time=#{applyEndTime},game_system=#{gameSystem},league_type=#{leagueType},province=(select province from sy_province where province_id=#{province}),\n" +
            "city=(select city from sy_city where city_id=#{city}),area=(select area from sy_area where area_id=#{area}),status=#{status},if_notice=#{ifNotice},if_open=#{ifOpen} where league_id=#{leagueId}")
    int updateLeague(League league);

    /**
     * 修改赛事举办单位
     * @param league
     */
    @Update("update pw_league set direct =#{direct},sponsor=#{sponsor},undertake=#{undertake},assisting=#{assisting} where league_id=#{leagueId}")
    int updateHostUnit(League league);

    /**
     * 根据id修改赛事状态
     * @param leagueId
     */
    @Delete("update pw_league set if_open = 2 where league_id=#{leagueId}")
    int closeLeague(@Param("leagueId") String leagueId);


    /** 查询总数*/
    @Select({"<script>",
            "select count(*) as count from (select pw.league_id as leagueId,pw.league_abbreviation as leagueAbbreviation,pw.league_name as leagueName,pw.introduce,pw.news,pw.league_icon as leagueIcon,\n" +
                    "pw.background,pw.direct,pw.sponsor,pw.undertake,pw.assisting,pw.start_time as startTime,pw.end_time as endTime,pw.apply_start_time as applyStartTime,\n" +
                    "pw.apply_end_time as applyEndTime,pw.game_system as gameSystem,pw.league_type as leagueType,pw.league_site as leagueSite,pw.province,pw.city,pw.area,pw.`status`,\n" +
                    "pw.if_notice as ifNotice,pw.if_open as ifOpen\n" +
                    "from pw_league pw ) a where 1=1" +
             "<when test='map.leagueName!=null'>",
            "AND a.leagueName like '%${map.leagueName}%'",
            "</when>",
            "<when test='map.leagueAbbreviation!=null'>",
            "AND a.leagueAbbreviation like '%${map.leagueAbbreviation}%'",
            "</when>",
            "<when test='map.leagueType!=null'>",
            "AND a.leagueType in(${map.leagueType})",
            "</when>",
            "<when test='map.gameSystem!=null'>",
            "AND a.gameSystem in(${map.gameSystem})",
            "</when>",
            "<when test='map.status!=null'>",
            "AND a.status in(${map.status})",
            "</when>",
            "<when test='map.ifOpen!=null and map.ifOpen!=0'>",
            "AND a.ifOpen = #{map.ifOpen}",
            "</when>",
            "</script> "})
    @Results({
            @Result(id = true, property = "count", column = "count")
    })
    int selectTotalPage(PageCrt page);


    /** 分页查询*/
    @Select({"<script>",
            "select pw.league_id as leagueId,pw.league_abbreviation as leagueAbbreviation,pw.league_name as leagueName,pw.introduce,pw.news,pw.league_icon as leagueIcon,\n" +
                    "pw.background,pw.direct,pw.sponsor,pw.undertake,pw.assisting,pw.start_time as startTime,pw.end_time as endTime,pw.apply_start_time as applyStartTime,\n" +
                    "pw.apply_end_time as applyEndTime,pw.game_system as gameSystem,pw.league_type as leagueType,pw.league_site as leagueSite," +
                    "pw.province,\n" +
                    "pw.city,pw.area,pw.`status`,\n" +
                    "pw.if_notice as ifNotice,pw.if_open as ifOpen\n" +
                    "from pw_league pw where 1=1 " +
             "<when test='map.leagueName!=null'>",
            "AND pw.league_name like '%${map.leagueName}%'",
            "</when>",
            "<when test='map.leagueAbbreviation!=null'>",
            "AND pw.league_abbreviation like '%${map.leagueAbbreviation}%'",
            "</when>",
           "<when test='map.leagueType!=null'>",
            "AND pw.league_type in(${map.leagueType})",
            "</when>",
            "<when test='map.gameSystem!=null'>",
            "AND pw.game_system in(${map.gameSystem})",
            "</when>",
            "<when test='map.status!=null'>",
            "AND pw.status in(${map.status})",
            "</when>",
            "<when test='map.ifOpen!=null and map.ifOpen!=0'>",
            "AND pw.if_open = #{map.ifOpen}",
            "</when>",
             "<when test='sidx!=null'>",
            "ORDER BY ${sidx} ${sord}",
            "</when> LIMIT #{index},#{rows}",
            "</script> "})
    @Results({
            @Result(id = true, property = "leagueId", column = "leagueId"),
            @Result(property = "leagueAbbreviation", column = "leagueAbbreviation"),
            @Result(property = "leagueName", column = "leagueName"),
            @Result(property = "introduce", column = "introduce"),
            @Result(property = "leagueIcon", column = "leagueIcon"),
            @Result(property = "background", column = "background"),
            @Result(property = "direct", column = "direct"),
            @Result(property = "sponsor", column = "sponsor"),
            @Result(property = "undertake", column = "undertake"),
            @Result(property = "assisting", column = "assisting"),
            @Result(property = "startTime", column = "startTime"),
            @Result(property = "endTime", column = "endTime"),
            @Result(property = "applyStartTime", column = "applyStartTime"),
            @Result(property = "applyEndTime", column = "applyEndTime"),
            @Result(property = "gameSystem", column = "gameSystem"),
            @Result(property = "leagueType", column = "leagueType"),
            @Result(property = "leagueSite", column = "leagueSite"),
            @Result(property = "province", column = "province"),
            @Result(property = "city", column = "city"),
            @Result(property = "area", column = "area"),
            @Result(property = "status", column = "status"),
            @Result(property = "ifNotice", column = "ifNotice"),
            @Result(property = "ifOpen", column = "ifOpen"),
    })
    List<Map> selectePaging(PageCrt page);


    /** 查询赛程信息*/
    @Select({"select pw.direct,pw.sponsor,pw.undertake,pw.assisting,pw.league_site as leagueSite\n" +
                    "from pw_league pw where 1=1 and league_id=#{leagueId} "
              })
    @Results({
            @Result(property = "direct", column = "direct"),
            @Result(property = "sponsor", column = "sponsor"),
            @Result(property = "undertake", column = "undertake"),
            @Result(property = "assisting", column = "assisting"),
            @Result(property = "leagueSite", column = "leagueSite")
    })
    League getHostUnit(@Param("leagueId") String leagueId);


    /**
     * 修改赛事场地
     * @param league
     */
    @Update("update pw_league set league_site =#{leagueSite} where league_id=#{leagueId}")
    int updateLeagueSite(League league);

    /** 查询所有赛事*/
    @Select("select league_id as leagueId,league_name as leagueName from pw_league")
    @Results({
            @Result(id = true, property = "leagueId", column = "leagueId"),
            @Result(property = "leagueName", column = "leagueName")
    })
    List<League> getLeagueList();



    /** 查询总数*/
    @Select({"<script>",
            "select count(*) as count from (select @n:=@n+1 as n,(select team_name from pw_team where team_id = gg.team_id) as teamName,sum(result) as res  \n" +
                    "from pw_game_grand gg,(select @n:=0) table2 where gg.type=1 \n" +
                    "and game_id in (select game_id from pw_schedule_relateGame sr where sr.schedule_id in\n" +
                    "(select s.schedule_id from pw_schedule s where s.league_id=#{map.leagueId}))\n" +
                    " AND gg.type=#{map.type} GROUP BY team_id) a where 1=1" +
            "</script> "})
    @Results({
            @Result(id = true, property = "count", column = "count")
    })
    int selectTotalPageTeam(PageCrt page);


    /** 分页查询*/
    @Select({"<script>",
            "select @n:=@n+1 as n,(select team_name from pw_team where team_id = gg.team_id) as teamName,sum(result) as res  \n" +
            "from pw_game_grand gg,(select @n:=0) table2 where 1=1 \n" +
            "and game_id in (select game_id from pw_schedule_relateGame sr where sr.schedule_id in\n" +
            "(select s.schedule_id from pw_schedule s where s.league_id=#{map.leagueId}))\n" +
            "<when test='map.type!=null'>",
            "AND gg.type=#{map.type}",
            "</when>",
            "GROUP BY team_id \n" +
            "<when test='sidx!=null'>",
            "ORDER BY ${sidx} ${sord}",
            "</when> LIMIT #{index},#{rows}",
            "</script> "})
    @Results({
            @Result(id = true, property = "leagueId", column = "leagueId")
    })
    List<Map> selectePagingTeam(PageCrt page);

    /** 查询总数*/
    @Select({"<script>",
            "select count(*) as count from (select @n:=@n+1 as n,(select nickname from pw_users where user_id = gg.user_id) as nickname,sum(result) as res,\n" +
                    "(select t.team_name from pw_team_member tm,pw_team t where tm.user_id = gg.user_id and tm.status=1 and tm.team_id=gg.team_id and tm.team_id=t.team_id) as teamName \n" +
                    "from pw_game_grand gg,(select @n:=0) table2 where gg.type=1 \n" +
                    "and game_id in (select game_id from pw_schedule_relateGame sr where sr.schedule_id in\n" +
                    "(select s.schedule_id from pw_schedule s where s.league_id=#{map.leagueId}))\n" +
                    " AND gg.type=#{map.type} GROUP BY user_id) a where 1=1" +
                    "</script> "})
    @Results({
            @Result(id = true, property = "count", column = "count")
    })
    int selectTotalPageUser(PageCrt page);


    /** 分页查询*/
    @Select({"<script>",
            "select @n:=@n+1 as n,(select nickname from pw_users where user_id = gg.user_id) as nickname,sum(result) as res,\n" +
                    "(select t.team_name from pw_team_member tm,pw_team t where tm.user_id = gg.user_id and tm.status=1 and tm.team_id=gg.team_id and tm.team_id=t.team_id) as teamName \n" +
                    "from pw_game_grand gg,(select @n:=0) table2 where 1=1 \n" +
                    "and game_id in (select game_id from pw_schedule_relateGame sr where sr.schedule_id in\n" +
                    "(select s.schedule_id from pw_schedule s where s.league_id=#{map.leagueId}))\n" +
             "<when test='map.type!=null'>",
            "AND gg.type=#{map.type}",
            "</when>",
            "GROUP BY user_id \n" +
             "<when test='sidx!=null'>",
            "ORDER BY ${sidx} ${sord}",
            "</when> LIMIT #{index},#{rows}",
            "</script> "})
    @Results({
            @Result(id = true, property = "leagueId", column = "leagueId")
    })
    List<Map> selectePagingUser(PageCrt page);

    /** 查询总数*/
    @Select({"<script>",
            "select count(*) as count from (select (select team_name from pw_team where team_id = sr.team_id) as teamName,\n" +
                    "(select schedule_name from pw_schedule where schedule_id = sr.schedule_id) as scheduleName,sr.game_number as gameNumber\n" +
                    ",sr.victory,sr.negation ,sr.flat,sr.goal,sr.lose,sr.integral,sr.current_ranking as currentRanking \n" +
                    "from pw_schedule_ranking sr where sr.schedule_id in (\n" +
                    "select s.schedule_id from pw_schedule s where s.league_id = #{map.leagueId} \n" +
                    ") GROUP BY team_id) a where 1=1" +
                    "</script> "})
    @Results({
            @Result(id = true, property = "count", column = "count")
    })
    int selectTotalPagePoints(PageCrt page);


    /** 分页查询*/
    @Select({"<script>",
            "select sr.ranking_id as rankingId,(select team_name from pw_team where team_id = sr.team_id) as teamName,\n" +
                    "(select schedule_name from pw_schedule where schedule_id = sr.schedule_id) as scheduleName,sr.game_number as gameNumber\n" +
                    ",sr.victory,sr.negation ,sr.flat,sr.goal,sr.lose,sr.integral,sr.current_ranking as currentRanking \n" +
                    "from pw_schedule_ranking sr where sr.schedule_id in (\n" +
                    "select s.schedule_id from pw_schedule s where s.league_id = #{map.leagueId} \n" +
                    ")",
            "GROUP BY team_id \n" +
            "<when test='sidx!=null'>",
            "ORDER BY ${sidx} ${sord}",
            "</when> LIMIT #{index},#{rows}",
            "</script> "})
    @Results({
            @Result(id = true, property = "leagueId", column = "leagueId")
    })
    List<Map> selectePagingPoints(PageCrt page);


    /**
     * 修改积分
     */
    @Update("update pw_schedule_ranking set integral =#{integral} where ranking_id=#{rankingId}")
    int updateScheduleRanking(@Param("rankingId") String rankingId,@Param("integral") String integral);


}
