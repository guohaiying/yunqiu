package com.yunqiu.dao;

import com.yunqiu.model.ScheduleRanking;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 通用sql
 */

@Mapper
public interface ScheduleRankingMapper {

    @Insert({
            "insert into pw_schedule_ranking(ranking_id,schedule_id,team_id,current_ranking,lifting,game_number,victory,flat,negation,goal,lose,integral) values(",
            "#{ranking_id},#{schedule_id},#{team_id},#{current_ranking},#{lifting},#{game_number},#{victory},#{flat},#{negation},#{goal},#{lose},#{integral})"
    })
    int insertScheduleRanking(ScheduleRanking scheduleRanking);


    /** 查询比赛总条数*/
    @Select({"<script>",
            "select count(*) as count from (select * from pw_schedule_ranking where team_id=#{teamId} and schedule_id=#{schedule_id}) a where 1=1" +
            "</script> "})
    @Results({
            @Result(id = true, property = "count", column = "count")
    })
    int selectCountScheduleRanking(@Param("teamId") String teamId,@Param("schedule_id") String schedule_id);

    @Update({
            "update pw_schedule_ranking set victory = victory + 1 where schedule_id=#{schedule_id} and team_id=#{team_id}\n"
    })
    int updatescheduleRankingVictory(ScheduleRanking scheduleRanking);

    @Update({
            "update pw_schedule_ranking set negation = negation + 1 where schedule_id=#{schedule_id} and team_id=#{team_id}\n"
    })
    int updatescheduleRankingnNegation(ScheduleRanking scheduleRanking);

    @Update({
            "update pw_schedule_ranking set flat = flat + 1 where schedule_id=#{schedule_id} and team_id=#{team_id}\n"
    })
    int updatescheduleRankingnFlat(ScheduleRanking scheduleRanking);

    @Update({
            "update pw_schedule_ranking set victory = victory - 1 where schedule_id=#{schedule_id} and team_id=#{team_id} and victory!=0\n"
    })
    int updatescheduleRankingVictoryJian(ScheduleRanking scheduleRanking);

    @Update({
            "update pw_schedule_ranking set negation = negation - 1 where schedule_id=#{schedule_id} and team_id=#{team_id} and negation!=0\n"
    })
    int updatescheduleRankingnNegationJian(ScheduleRanking scheduleRanking);

    @Update({
            "update pw_schedule_ranking set flat = flat - 1 where schedule_id=#{schedule_id} and team_id=#{team_id} and flat!=0\n"
    })
    int updatescheduleRankingnFlatJian(ScheduleRanking scheduleRanking);


    @Select("SELECT t1.* FROM (\n" +
            "select ranking_id,integral from pw_schedule_ranking where schedule_id in \n" +
            "(select schedule_id from pw_schedule_relateGame sr where game_id = #{gameId}) \n" +
            ") t1 ORDER BY t1.integral desc")
    @Results({
            @Result(id = true, property = "ranking_id", column = "ranking_id"),
            @Result(property = "integral", column = "integral")
    })
    List<ScheduleRanking> findRanking(@Param("gameId") String gameId);

    @Update({
            "update pw_schedule_ranking set current_ranking = #{current_ranking} where ranking_id=#{ranking_id} \n"
    })
    int updatescheduleCurrentRanking(ScheduleRanking scheduleRanking);

}
