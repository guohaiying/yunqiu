package com.yunqiu.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * 通用sql
 */

@Mapper
public interface GeneralMapper {

    /**
     * 查询球队参与的赛事总数
     * @return
     */
    @Select({
            "SELECT COUNT(DISTINCT league_id,team_id) FROM pw_join_league WHERE team_id=#{team_id} AND audit_status!=3"
    })
    int selectLeagueNumber(@Param("team_id") String team_id);

    /**
     * 获取球队参与的所有比赛
     * @param team_id
     * @return
     */
    @Select({
            "SELECT * FROM pw_tournament WHERE (entry_teamA=#{team_id} OR entry_teamB=#{team_id}) AND game_status=6"
    })
    List<Map<String,Object>> selectGameAll(@Param("team_id") String team_id);

    /**
     * 获取球队赛事
     * @param team_id
     * @return
     */
    @Select({
            "SELECT DISTINCT le.league_id,le.league_icon,le.league_name AS league_abbreviation,le.game_system,le.apply_start_time,",
            "le.apply_end_time,le.start_time,le.end_time,le.league_site,le.province,le.city,le.area,le.`status` FROM pw_join_league ",
            "AS jl JOIN pw_league AS le ON le.league_id=jl.league_id WHERE jl.team_id=#{team_id} AND jl.audit_status=2"
    })
    List<Map<String,Object>> selectLeague(@Param("team_id") String team_id);

    /**
     * 获取全项数据
     * @param game_id
     * @return
     */
    @Select({
            "SELECT COUNT(g.grand_id) AS total_number,g.type,g.team_id,IF((SELECT t.entry_teamA FROM pw_tournament AS t WHERE t.entry_teamA=g.team_id ORDER BY game_time desc limit 1)",
            "=g.team_id,1,2) AS attribution FROM pw_game_grand AS g WHERE g.game_id = #{game_id} GROUP BY g.team_id,g.type"
    })
    List<Map<String,Object>> selectEntireGrand(@Param("game_id") String game_id);

    /**
     * 根据比赛id查询比赛
     * @param game_id
     * @return
     */
    @Select("SELECT * FROM pw_tournament WHERE game_id=#{game_id}")
    Map<String,Object> selectGameByGameId(@Param("game_id") String game_id);

    /**
     * 获取球队最近30天的比赛信息
     * @param team_id 球队id
     * @param current_date 当前日期
     * @param anciently_date 30天前的日期
     * @return
     */
    @Select({
            "SELECT * FROM pw_tournament WHERE (entry_teamA=#{team_id} OR entry_teamB = #{team_id})  ",
            "AND game_time <= #{current_date} AND game_time >= #{anciently_date} AND game_status = 6 ORDER BY game_time ASC"
    })
    List<Map<String,Object>> selectGameByTeamIdAndTime(@Param("team_id") String team_id, @Param("current_date") String current_date, @Param("anciently_date") String anciently_date);

    /**
     * 根据用户ID查询资料
     * @param userId
     * @return
     */
    @Select("select * from pw_users where 1=1 AND user_id=#{userId}")
    Map<String,Object> selectUsersByUserId(@Param("userId") String userId);
}
