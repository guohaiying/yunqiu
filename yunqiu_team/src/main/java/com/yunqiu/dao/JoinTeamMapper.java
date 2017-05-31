package com.yunqiu.dao;

import com.yunqiu.model.JoinTeam;
import org.apache.ibatis.annotations.*;

import java.util.Date;

/**
 * Created by 11366 on 2017/1/21.
 */

@Mapper
public interface JoinTeamMapper {

    /**
     * 申请加入球队（添加球队申请）
     * @param joinTeam
     */
    @Insert({
            "insert into pw_join_team(join_id,user_id,team_id,remark,apply_time,audit_time,status,audit_msg)",
            " values(#{join_id},#{user_id},#{team_id},#{remark},#{apply_time},#{audit_time},#{status},#{audit_msg})"
    })
    void insertJoinTeam(JoinTeam joinTeam);


    /**
     * 根据用户id、球队ID跟申请状态查询用户申请信息
     * @param teamId
     * @param userId
     * @param status
     * @return
     */
    @Select("select * from pw_join_team where team_id=#{teamId} and user_id=#{userId} and status=#{status}")
    JoinTeam selectJoinTeamByTeamIdAndUserIdAndStatus(@Param("teamId") String teamId,@Param("userId") String userId,@Param("status") int status);

    /**
     * 根据主键id查询
     * @param join_id
     * @return
     */
    @Select("select * from pw_join_team where join_id=#{join_id}")
    JoinTeam selectJoinTeamById(@Param("join_id") String join_id);

    /**
     * 修改状态
     * @param join_id
     * @param status
     */
    @Update("update pw_join_team set status=#{status},audit_msg=#{audit_msg},audit_time=#{audit_time} where join_id=#{join_id}")
    void updateStatus(@Param("join_id") String join_id, @Param("audit_msg") String audit_msg, @Param("status") int status, @Param("audit_time")Date audit_time);
}
