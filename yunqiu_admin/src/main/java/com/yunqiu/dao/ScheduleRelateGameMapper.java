package com.yunqiu.dao;


import com.yunqiu.model.LeagueHonor;
import com.yunqiu.model.PageCrt;
import com.yunqiu.model.ScheduleRelateGame;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface ScheduleRelateGameMapper {

    @Insert({
            "insert into pw_schedule_relateGame(relate_id,schedule_id,game_id,rounds) values(",
            "#{relateId},#{scheduleId},#{gameId},#{rounds})"
    })
    int insertScheduleRelateGame(ScheduleRelateGame scheduleRelateGame);


    @Delete("DELETE FROM pw_schedule_relateGame WHERE relate_id = #{relateId}")
    int deleteScheduleRelateGameById(@Param("relateId") String relateId);

    /** 查询总数球队*/
    @Select({"<script>",
            "select count(*) as count from (select sr.relate_id as relateId,sr.schedule_id as scheduleId,sr.game_id as gameId,s.league_type leagueType,(select team_name from pw_team where team_id=t.entry_teamA) as entryTeamA,\n" +
                    "(select team_name from pw_team where team_id=t.entry_teamB) as entryTeamB,t.game_time as gameTime,s.schedule_name as scheduleName,\n" +
                    "t.game_status as gameStatus,t.game_site as gameSite\n" +
                    "from pw_schedule_relategame sr,pw_schedule s,pw_tournament t\n" +
                    "where sr.schedule_id=s.schedule_id and sr.game_id=t.game_id and s.league_id=#{map.leagueId} ) a where 1=1" +
                    "</script> "})
    @Results({
            @Result(id = true, property = "count", column = "count")
    })
    int selectTotalPage(PageCrt page);


    /** 分页查询球队 */
    @Select({"<script>",
            "select sr.relate_id as relateId,sr.schedule_id as scheduleId,sr.game_id as gameId,s.league_type leagueType,(select team_name from pw_team where team_id=t.entry_teamA) as entryTeamA,\n" +
                    "(select team_name from pw_team where team_id=t.entry_teamB) as entryTeamB,t.game_time as gameTime,sr.rounds,s.schedule_name as scheduleName,\n" +
                    "t.game_status as gameStatus,t.game_site as gameSite\n" +
                    "from pw_schedule_relategame sr,pw_schedule s,pw_tournament t\n" +
                    "where sr.schedule_id=s.schedule_id and sr.game_id=t.game_id and s.league_id=#{map.leagueId}" +
            "<when test='sidx!=null'>",
            "ORDER BY ${sidx} ${sord}",
            "</when> LIMIT #{index},#{rows}",
            "</script> "})
    @Results({
            @Result(id = true, property = "teamId", column = "team_id")
    })
    List<Map> selectePaging(PageCrt page);


    @Update({
            "update pw_schedule_relateGame set schedule_id=#{scheduleId},game_id=#{gameId},rounds=#{rounds} where relate_id=#{relateId}\n"
    })
    int updateScheduleRelateGame(ScheduleRelateGame scheduleRelateGame);

    @Select("select * from pw_schedule_relateGame where game_id=#{gameId} limit 1 ")
    @Results({
            @Result(id = true, property = "honorId", column = "honor_id"),
            @Result(property = "honorName", column = "honor_name")
    })
    Map<String,Object> getScheduleRelateGame(@Param("gameId") String gameId);

}
