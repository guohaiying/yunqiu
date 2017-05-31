package com.yunqiu.dao;

import com.yunqiu.model.Dynamic;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

/**
 * Created by 11366 on 2017/1/24.
 */
@Mapper
public interface IndexMapper {

    /**
     * 根据用户id，获取该用户所在的球队列表
     * @param userId
     * @return
     */
    @Select({
            "SELECT team.team_id,team.team_name,team.badge,team.background FROM pw_team_member AS member ",
            "JOIN pw_team AS team ON team.team_id=member.team_id AND team.`status`=1 WHERE member.user_id=#{userId} AND member.`status`=1"
    })
    List<Map<String,Object>> indexTeamList(@Param("userId") String userId);

    /**
     * 获取推荐球队
     * @param number
     * @return
     */
    @Select({
            "select team.team_id,team.team_name,team.badge,team.province,team.city,team.area,team.home,",
            "(SELECT cloud.mean_power FROM pw_team_clouddata AS cloud WHERE cloud.team_id=team.team_id ORDER BY cloud.count_time DESC LIMIT 1 ) AS ",
            "power from pw_team AS team where status=1 ORDER BY RAND() LIMIT #{number}"
    })
    List<Map<String,Object>> recommendTeamList(@Param("number") int number);

    /**
     * 获取推荐的球员
     * @param number
     * @return
     */
    @Select({
            "SELECT user_id,nickname,portrait,position,province,city,area FROM pw_users ORDER BY RAND() LIMIT #{number}"
    })
    List<Map<String,Object>> recommendationUser(@Param("number") int number);

    /**
     * 根据状态获取球队比赛信息 DESC：降序
     * @param teamId
     * @param game_status
     * @return
     */
    @Select("SELECT * FROM pw_tournament WHERE (entry_teamA = #{teamId} OR entry_teamB=#{teamId}) AND game_status=#{game_status} ORDER BY game_time DESC LIMIT 1")
    Map<String,Object> gameInfo_desc(@Param("teamId") String teamId,@Param("game_status") int game_status);

    @Select("SELECT t.*,IF((SELECT video.game_id FROM pw_video AS video WHERE video.game_id=t.game_id AND video.classify=2 AND video.if_show=1 LIMIT 1),1,2) AS isVideo FROM pw_tournament AS t WHERE (t.entry_teamA = #{teamId} OR t.entry_teamB=#{teamId}) AND t.game_status=6 ORDER BY t.game_time DESC LIMIT 1")
    Map<String,Object> gameInfo_desc_index(@Param("teamId") String teamId);


    /**
     * 根据状态获取球队比赛信息 ASC：升序
     * @param teamId
     * @param game_status
     * @return
     */
    @Select("SELECT * FROM pw_tournament WHERE (entry_teamA = #{teamId} OR entry_teamB=#{teamId}) AND game_status=#{game_status} ORDER BY game_time ASC LIMIT 1")
    Map<String,Object> gameInfo_asc(@Param("teamId") String teamId,@Param("game_status") int game_status);

    @Select("SELECT * FROM pw_tournament WHERE (entry_teamA = #{teamId} OR entry_teamB=#{teamId}) AND (game_status=3 OR game_status=4) ORDER BY game_time ASC LIMIT 1")
    Map<String,Object> gameInfo_asc_index(@Param("teamId") String teamId);

    /**
     * 根据球队id获取球队详情
     * @param teamId
     * @return
     */
    @Select("SELECT * FROM pw_team where team_id=#{teamId}")
    Map<String,Object> selectTeamInfo(@Param("teamId") String teamId);

    /**
     * 查询赛事成员
     * @param game_id
     * @param team_id
     * @return
     */
    @Select("SELECT u.user_id,u.portrait FROM pw_game_member AS m JOIN pw_users AS u ON u.user_id = m.user_id WHERE m.game_id = #{game_id} AND m.team_id=#{team_id} AND m.`status`=1")
    List<Map<String,String>> gameMatch(@Param("game_id") String game_id,@Param("team_id") String team_id);

    /**
     * 查询比赛参与信息
     * @param user_id
     * @param game_id
     * @return
     */
    @Select("select * from pw_game_member where user_id=#{user_id} AND game_id=#{game_id} AND team_id=#{team_id}")
    Map<String,Object> selectMatchByUserIdAndEventIdAndTeamId(@Param("user_id") String user_id,@Param("game_id") String game_id,@Param("team_id") String team_id);

    /**
     * 获取比赛已参与人数
     * @param game_id
     * @return
     */
    @Select("SELECT COUNT(match_id) AS event_match FROM pw_game_member WHERE game_id=#{game_id} AND team_id=#{team_id}")
    Map<String,Integer> selectMatchNumber(@Param("game_id") String game_id,@Param("team_id") String team_id);

    /**
     * 获取球队总数
     * @param team_id
     * @return
     */
    @Select("SELECT COUNT(team_id) AS team_match FROM pw_team_member WHERE team_id=#{team_id}")
    Map<String,Integer> selectTeamMatchNumber(@Param("team_id") String team_id);

    /**
     * 获取最新消息
     * @param user_id
     * @param message_type
     * @param type_id
     * @return
     */
    @Select({
            "SELECT * FROM pw_message WHERE user_id=#{user_id} AND message_type=#{message_type} AND type_id=#{type_id} ORDER BY push_time DESC LIMIT 1"
    })
    Map<String,Object> getMessageNew(@Param("user_id") String user_id,@Param("message_type") int message_type,@Param("type_id") String type_id);

    /**
     * 获取消息总数
     * @param user_id
     * @param message_type
     * @param type_id
     * @return
     */
    @Select({
            "SELECT COUNT(message_id) AS number FROM pw_message WHERE user_id=#{user_id} AND message_type=#{message_type} AND type_id=#{type_id}"
    })
    int getMessageTotal(@Param("user_id") String user_id,@Param("message_type") int message_type,@Param("type_id") String type_id);

    /**
     * 获取消息列表
     * @param user_id
     * @param message_type
     * @param type_id
     * @param start_now
     * @param pageSize
     * @return
     */
    @Select({
            "SELECT * FROM pw_message WHERE user_id=#{user_id} AND message_type=#{message_type} AND ",
            "type_id=#{type_id} ORDER BY push_time DESC LIMIT #{start_now},#{pageSize}"
    })
    List<Map<String,Object>> getMessage(@Param("user_id") String user_id,@Param("message_type") int message_type,
                                     @Param("type_id") String type_id,@Param("start_now") int start_now,@Param("pageSize") int pageSize);

    /**
     * 修改消息阅读状态（根据message_id）
     * @param message_id
     */
    @Select({
            "update pw_message set is_look=2 where message_id=#{message_id}"
    })
    void updateMessageById(@Param("message_id") String message_id);

    /**
     * 修改消息阅读状态(根据类型)
     * @param user_id
     */
    @Select({
            "update pw_message set is_look=2 where user_id=#{user_id} AND message_type=#{message_type} AND type_id=#{type_id}"
    })
    void updateMessageByType(@Param("user_id") String user_id,@Param("message_type") int message_type,@Param("type_id") String type_id);

    /**
     * 查询动态
     * @param user_id
     * @return
     */
    @Select({
            "SELECT * FROM pw_dynamic WHERE user_id=#{user_id} AND is_look=1 ORDER BY create_time DESC LIMIT 5"
    })
    List<Dynamic> selectDynamic(@Param("user_id") String user_id);

    /**
     * 修改动态查看状态
     * @param dynamic_id
     */
    @Update({
            "update pw_dynamic set is_look=2 WHERE dynamic_id=#{dynamic_id}"
    })
    void updateDynamic(@Param("dynamic_id") String dynamic_id);
}
