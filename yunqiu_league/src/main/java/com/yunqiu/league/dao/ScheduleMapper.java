package com.yunqiu.league.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * 赛程数据库操作
 */
@Mapper
public interface ScheduleMapper {

    /**
     * 根据赛事id获取该赛程的所有赛程
     * @param league_id
     * @return
     */
    @Select("SELECT * FROM pw_schedule AS s WHERE s.league_id = #{league_id} ORDER BY start_time DESC")
    List<Map<String,Object>> selectSchedule(@Param("league_id") String league_id);
    /**
     * 获取当前时间的赛程
     * @param league_id
     * @param time
     * @return
     */
    @Select("SELECT * FROM pw_schedule AS s WHERE s.league_id = #{league_id} AND s.start_time <= #{time} AND s.end_time >= #{time} LIMIT 1")
    Map<String,Object> selectCurveSchedule(@Param("league_id") String league_id, @Param("time") String time);

    /**
     * 获取离当前时间最近的赛程（往后）
     * @param league_id
     * @param time
     * @return
     */
    @Select("SELECT * FROM pw_schedule AS s WHERE s.league_id = #{league_id} AND s.start_time >#{time} ORDER BY s.start_time ASC LIMIT 1")
    Map<String,Object> selectLatelySchedule(@Param("league_id") String league_id, @Param("time") String time);

    /**
     * 获取最后一场赛程
     * @param league_id
     * @return
     */
    @Select("SELECT * FROM pw_schedule AS s WHERE s.league_id = #{league_id} ORDER BY s.start_time DESC LIMIT 1")
    Map<String,Object> selectFinallySchedule(@Param("league_id") String league_id);

    /**
     * 获取当前轮次
     * @param schedule_id
     * @return
     */
    @Select({
            "SELECT r.rounds FROM pw_schedule_relategame AS r JOIN pw_tournament AS t ON t.game_id=r.game_id AND ",
            "t.game_time>=#{curveTime} WHERE r.schedule_id =#{schedule_id} ORDER BY t.game_time ASC LIMIT 1"
    })
    String selectCurveRounds(@Param("schedule_id") String schedule_id,@Param("curveTime") String curveTime);

    /**
     * 获取赛程的比赛
     * @param schedule_id
     * @param rounds
     * @return
     */
    @Select({
            "SELECT t.game_id, t.*,teamA.team_name AS teamA_name,teamA.badge AS teamA_badge,teamB.team_name AS teamB_name,teamB.badge AS teamB_badge,",
            "IF((SELECT video.game_id FROM pw_video AS video WHERE video.game_id=t.game_id AND video.classify=2 AND video.if_show=1 LIMIT 1)=t.game_id,2,1) AS isVideo ",
            "FROM pw_schedule_relategame AS r JOIN pw_tournament AS t ON t.game_id = r.game_id ",
            "JOIN pw_team AS teamA ON teamA.team_id=t.entry_teamA JOIN pw_team AS teamB ON teamB.team_id=t.entry_teamB WHERE ",
            "r.schedule_id=#{schedule_id} AND r.rounds=#{rounds} ORDER BY t.game_time ASC"
    })
    List<Map<String,Object>> selectGame(@Param("schedule_id") String schedule_id,@Param("rounds") int rounds);

    /**
     * 获取榜单
     * @param type
     * @param league_id
     * @return
     */
    @Select({
            "SELECT u.user_id,u.nickname,u.portrait,t.team_id,t.team_name,COUNT(g.grand_id) AS total_number FROM pw_schedule AS s JOIN ",
            "pw_schedule_relategame AS r ON r.schedule_id=s.schedule_id JOIN pw_game_grand AS g ON g.game_id=r.game_id AND g.type=#{type} JOIN ",
            "pw_team AS t ON t.team_id=g.team_id JOIN pw_users AS u ON u.user_id=g.user_id WHERE s.league_id = #{league_id} GROUP BY g.user_id ORDER BY total_number DESC"
    })
    List<Map<String,Object>> selectCrunchies(@Param("type") int type,@Param("league_id") String league_id);

    /**
     * 获取淘汰赛的积分榜
     * @param schedule_id
     * @return
     */
    @Select({
            "SELECT t.game_id,t.game_time,t.score_teamA,t.score_teamB,teamA.team_id AS teamA_id,teamA.team_name AS teamA_name,teamA.badge AS teamA_badge,",
            "teamB.team_id AS teamB_id,teamB.team_name AS teamB_name,teamB.badge AS teamB_badge FROM pw_schedule_relategame AS r JOIN pw_tournament AS t ON ",
            "t.game_id = r.game_id JOIN pw_team AS teamA ON teamA.team_id=t.entry_teamA JOIN pw_team AS teamB ON teamB.team_id=t.entry_teamB WHERE ",
            "r.schedule_id = #{schedule_id} ORDER BY t.game_time ASC"
    })
    List<Map<String,Object>> selectTabelleByWeed(@Param("schedule_id") String schedule_id);

    /**
     * 获取小组赛、联赛的积分榜
     * @param schedule_id
     * @return
     */
    @Select({
            "SELECT pm.*,t.team_name,t.badge FROM pw_schedule_ranking AS pm JOIN pw_team AS t ON t.team_id=pm.team_id WHERE ",
            "pm.schedule_id=#{schedule_id} ORDER BY pm.current_ranking ASC"
    })
    List<Map<String,Object>> selectTabelleByGroup(@Param("schedule_id") String schedule_id);

}
