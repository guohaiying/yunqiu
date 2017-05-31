package com.yunqiu.dao;

import com.yunqiu.model.Event;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 比赛持久化
 */

@Mapper
public interface EventMapper {

    /**
     * 创建比赛
     * @param event
     */
    @Insert({
            "insert into pw_event(event_id,user_id,sponsor_team,rival_team,sponsor_score,rival_score,game_time,continue_time,",
            "game_system,site,sponsor_uniform,rival_uniform,apply_end_time,audit_status,event_status,score_status,audit_reason,refuse_reason,cancel_reason)",
            " values(#{event_id},#{user_id},#{sponsor_team},#{rival_team},#{sponsor_score},#{rival_score},#{game_time},#{continue_time},",
            "#{game_system},#{site},#{sponsor_uniform},#{rival_uniform},#{apply_end_time},#{audit_status},#{event_status},#{score_status},#{audit_reason},",
            "#{refuse_reason},#{cancel_reason})"
    })
    void insertEvent(Event event);

    /**
     * 根据用户id，查询到用户所在所有球队得比赛信息
     * @param user_id
     * @return
     */
    @Select("SELECT distinct e.* FROM pw_team_member AS m JOIN pw_event AS e ON (e.sponsor_team = m.team_id OR e.rival_team = m.team_id) AND e.event_status != 8 AND e.event_status != 9 WHERE m.user_id = #{user_id} Order By e.game_time ASC")
    List<Event> selectEventByUser(@Param("user_id") String user_id);

    /**
     * 根据比赛id，查询比赛信息
     * @param event_id
     * @return
     */
    @Select("select * from pw_event where event_id=#{event_id}")
    Event selectEventById(@Param("event_id") String event_id);

    /**
     *修改比赛状态
     * @param event_id
     * @param event_status
     * @param audit_reason
     */
    @Update({
            "update pw_event set event_status=#{event_status},audit_reason=#{audit_reason},refuse_reason=#{refuse_reason},",
            "cancel_reason=#{cancel_reason},audit_status=#{audit_status} where event_id=#{event_id}"
    })
    void updateEventStatusById(@Param("event_id") String event_id,@Param("audit_status") int audit_status,@Param("event_status") int event_status,
                               @Param("audit_reason") String audit_reason,@Param("refuse_reason") String refuse_reason,
                               @Param("cancel_reason") String cancel_reason);
}
