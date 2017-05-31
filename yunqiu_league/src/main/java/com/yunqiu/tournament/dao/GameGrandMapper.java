package com.yunqiu.tournament.dao;

import com.yunqiu.tournament.model.GameGrand;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * 比赛战报数据持久化
 */
@Mapper
public interface GameGrandMapper {
    /**
     * 录入比赛战报
     * @param gameGrand
     */
    @Insert({
            "insert into pw_game_grand(grand_id,game_id,type,team_id,user_id,result,parent_id) values",
            "(#{grand_id},#{game_id},#{type},#{team_id},#{user_id},#{result},#{parent_id})"
    })
    void insert(GameGrand gameGrand);

    /**
     * 获取全项数据
     * @param game_id
     * @return
     */
    @Select({
            "SELECT COUNT(g.grand_id) AS total_number,g.type,g.team_id,IF((SELECT t.entry_teamA FROM pw_tournament AS t WHERE t.entry_teamA=g.team_id AND t.game_id=g.game_id)",
            "=g.team_id,1,2) AS attribution FROM pw_game_grand AS g WHERE g.game_id = #{game_id} GROUP BY g.team_id,g.type"
    })
    List<Map<String,Object>> selectEntireGrand(@Param("game_id") String game_id);

    /**
     * 根据比赛id跟进球状态查询战报
     * @param game_id
     * @param type
     * @return
     */
    @Select({
            "SELECT u.user_id,u.nickname,IF((SELECT t.entry_teamA FROM pw_tournament AS t WHERE t.entry_teamA=g.team_id AND t.game_id=g.game_id)=g.team_id,1,2) AS attribution ",
            "FROM pw_game_grand AS g JOIN pw_users AS u ON u.user_id=g.user_id WHERE g.game_id=#{game_id} AND g.type=#{type}"
    })
    List<Map<String,Object>> selectGrandByType(@Param("game_id") String game_id,@Param("type") int type);

    /**
     * 查询助攻
     * @param grand_id
     * @return
     */
    @Select({
            "SELECT u.user_id,u.nickname FROM pw_game_grand AS g JOIN pw_users AS u ON u.user_id=g.user_id WHERE g.parent_id=#{grand_id} AND g.type=6"
    })
    Map<String,Object> selectAssists(@Param("grand_id") String grand_id);
}