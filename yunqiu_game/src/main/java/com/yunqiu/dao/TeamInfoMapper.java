package com.yunqiu.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * 球队成员持久化
 * @author 武尊
 * @time 2017-01-16
 * @version 1.0
 */

@Mapper
public interface TeamInfoMapper {

    /**
     * 根据球队ID跟用户ID查询球员
     * @param teamId
     * @param userId
     * @return
     */
    @Select("select * from pw_team_member where user_id=#{userId} AND team_id=#{teamId}")
    Map<String,Object> selectTeamMemberByTeamIdAndUserId(@Param("teamId") String teamId, @Param("userId") String userId);

    /**
     * 根据id查询球队基本信息
     * @param teamId
     * @return
     */
    @Select("select * from pw_team where 1=1 and team_id = #{teamId}")
    Map<String,Object> selectTeamInfo(@Param("teamId") String teamId);

    /**
     * 根据用户id，查询球队列表
     * @param userId
     * @return
     */
    @Select("SELECT team.team_id,team.team_name FROM pw_team_member AS member JOIN pw_team AS team ON team.team_id=member.team_id AND team.`status`=1 WHERE member.user_id=#{userId} AND member.`status`=1")
    List<Map<String,Object>> selectTeamList(@Param("userId") String userId);
}
