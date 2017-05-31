package com.yunqiu.tournament.dao;

import com.yunqiu.tournament.model.GameMember;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * Created by 11366 on 2017/2/25.
 */

@Mapper
public interface GameMemberMapper {
    /**
     * 查询用户是否参加了比赛
     * @param game_id
     * @param user_id
     * @return
     */
    @Select("select * from pw_game_member where user_id=#{user_id} AND game_id=#{game_id}")
    GameMember selectGameMember(@Param("game_id") String game_id,@Param("user_id") String user_id);

    /**
     * 根据比赛id跟球队id，查询参与比赛的球员
     * @param game_id
     * @param team_id
     * @return
     */
    @Select({
            "SELECT u.user_id,u.nickname,u.portrait,tm.jersey_no,tm.place,m.`status` FROM pw_game_member AS m JOIN pw_users AS u ON u.user_id=m.user_id ",
            "JOIN pw_team_member AS tm ON tm.team_id=m.team_id AND tm.user_id=m.user_id WHERE m.game_id=#{game_id} AND m.team_id=#{team_id}"
    })
    List<Map<String,Object>> selectGameMemberList(@Param("game_id") String game_id,@Param("team_id") String team_id);

    /**
     * 添加比赛报名信息
     * @param gameMember
     */
    @Insert({
            "insert into pw_game_member(match_id,user_id,team_id,game_id,status) values",
            "(#{match_id},#{user_id},#{team_id},#{game_id},#{status})"
    })
    void insertGameMember(GameMember gameMember);

    /**
     * 修改参与状态
     * @param match_id
     * @param status
     */
    @Update({
            "update pw_game_member set status=#{status} where match_id=#{match_id}"
    })
    void updateGameMember(@Param("match_id") String match_id,@Param("status") int status);
}
