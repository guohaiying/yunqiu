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
     * 获取收藏的比赛
     * @param user_id
     * @return
     */
    @Select({
            "SELECT COUNT(collect_id) AS number FROM pw_game_collection WHERE user_id=#{user_id}"
    })
    int selectGameCollection(@Param("user_id") String user_id);

    /**
     * 根据用户id查询比赛（进球/红黄牌）数据
     * @param user_id
     * @return
     */
    @Select({
            "SELECT COUNT(grand_id) AS number,type FROM pw_game_grand WHERE user_id = #{user_id} GROUP BY type"
    })
    List<Map<String,Object>> selectGameGrandByUserId(@Param("user_id") String user_id);

    /**
     * 获取用户参与的所有比赛
     * @param user_id
     * @return
     */
    @Select({
            "SELECT t.* FROM pw_team_member AS m JOIN pw_tournament AS t ON (t.entry_teamA=m.team_id OR t.entry_teamB=m.team_id) ",
            "AND t.game_status=6 WHERE m.user_id=#{user_id}"
    })
    List<Map<String,Object>> selectGameAll(@Param("user_id") String user_id);

    /**
     * 根据用户id跟比赛id，查询参与信息
     * @param user_id
     * @param game_id
     * @return
     */
    @Select({
            "SELECT * FROM pw_game_member WHERE user_id = #{user_id} AND game_id = #{game_id}"
    })
    Map<String,Object> selectGameMember(@Param("user_id") String user_id,@Param("game_id") String game_id);

    /**
     * 查询用户参与的赛事总数
     * @return
     */
    @Select({
            "SELECT COUNT(distinct le.league_id) AS number FROM pw_team_member AS m JOIN pw_join_league AS jl ON jl.team_id = m.team_id JOIN ",
            "pw_league AS le ON le.league_id = jl.league_id AND le.`status`!=4 WHERE m.user_id = #{user_id} ORDER BY le.apply_end_time ASC"
    })
    int selectLeagueNumber(@Param("user_id") String user_id);
}
