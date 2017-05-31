package com.yunqiu.dao;


import com.yunqiu.model.Schedule;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ScheduleMapper {

    @Insert({
            "insert into pw_schedule(schedule_id,league_id,start_time,end_time,league_type,schedule_name,total_rounds,schedule_status) values(",
            "#{scheduleId},#{leagueId},#{startTime},#{endTime},#{leagueType},#{scheduleName},#{totalRounds},#{scheduleStatus})"
    })
    int insertSchedule(Schedule schedule);


    @Delete("DELETE FROM pw_schedule WHERE schedule_id = #{scheduleId}")
    int deleteScheduleById(@Param("scheduleId") String scheduleId);

    @Select({"<script>",
            "select schedule_id as scheduleId,schedule_name as scheduleName,league_type as leagueType,league_id,total_rounds as totalRounds  from pw_schedule where 1=1 " +
            "<when test='leagueId!=null'>",
                "AND league_id=#{leagueId}",
            "</when>  order by schedule_name",
            "</script> "})
    @Results({
            @Result(id = true, property = "scheduleId", column = "schedule_id"),
            @Result(property = "scheduleName", column = "schedule_name")
    })
    List<Schedule> getScheduleList(@Param("leagueId") String leagueId);



}
