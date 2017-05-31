package com.yunqiu.dao;

import com.yunqiu.model.Match;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * Created by 11366 on 2017/1/25.
 */

@Mapper
public interface MatchMapper {
    /**
     * 添加报名信息
     * @param match
     */
    @Insert({
            "insert into pw_match(match_id,user_id,team_id,event_id,status) values(",
            "#{match_id},#{user_id},#{team_id},#{event_id},#{status})"
    })
    void insertMatch(Match match);

    /**
     * 修改参与状态
     * @param status
     * @param user_id
     * @param event_id
     * @param team_id
     */
    @Update("update pw_match set status=#{status} where user_id=#{user_id} AND event_id=#{event_id} AND team_id=#{team_id}")
    void updateMatchStatusByUserIdAndEventIdAndTeamId(@Param("status") int status,@Param("user_id") String user_id,
                                             @Param("event_id") String event_id,@Param("team_id") String team_id);

    /**
     * 查询参与信息
     * @param user_id
     * @param event_id
     * @return
     */
    @Select("select * from pw_match where user_id=#{user_id} AND event_id=#{event_id}")
    Match selectMatchByUserIdAndEventIdAndTeamId(@Param("user_id") String user_id,@Param("event_id") String event_id);

    /**
     * 查询所有报名比赛的成员
     * @param event_id
     * @param team_id
     * @return
     */
    @Select("SELECT u.user_id,u.nickname,u.portrait,m.`status` FROM pw_match AS m JOIN pw_users AS u ON u.user_id=m.user_id WHERE m.event_id=#{event_id} AND m.team_id=#{team_id}")
    List<Map<String,Object>> selectAllMatch(@Param("event_id") String event_id,@Param("team_id") String team_id);

    /**
     * 获取比赛已参与人数
     * @param event_id
     * @return
     */
    @Select("SELECT COUNT(match_id) AS event_match FROM pw_match WHERE event_id=#{event_id} AND team_id=#{team_id}")
    int selectMatchNumber(@Param("event_id") String event_id,@Param("team_id") String team_id);

    /**
     * 获取球队总数
     * @param team_id
     * @return
     */
    @Select("SELECT COUNT(team_id) AS team_match FROM pw_team_member WHERE team_id=#{team_id}")
    int selectTeamMatchNumber(@Param("team_id") String team_id);
}
