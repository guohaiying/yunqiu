package com.yunqiu.dao;


import com.yunqiu.model.JoinTeam;
import org.apache.ibatis.annotations.*;

@Mapper
public interface JoinTeamMapper {

    /**
     * 添加球队申请
     * @param jointeam
     */
    @Insert({
            "insert into pw_join_team(join_id,user_id,team_id,remark,apply_time,audit_time,status,audit_msg) values(",
            "#{joinId},#{userId},#{teamId},#{remark},#{applyTime},#{auditTime},#{status},#{auditMsg})"
    })
    int insertJoinTeam(JoinTeam jointeam);

    @Update({
            "update pw_join_team set status=#{status} where user_id=#{userId} and team_id=#{teamId}\n"
    })
    int updateJoinTeamStatus(JoinTeam jointeam);



    /** 查询是否有队长 */
    @Select({"select count(*) as count from pw_team_member where team_id=#{teamId} and `status`=1 and identity=1\n"})
    @Results({
            @Result(id = true, property = "count", column = "count")
    })
    int selectIdentityCount(@Param("teamId") String teamId);

    /** 查询是否有超管 */
    @Select({"select count(*) as count from pw_team_member where team_id=#{teamId} and `status`=1 and jurisdiction=1"})
    @Results({
            @Result(id = true, property = "count", column = "count")
    })
    int selectJurisdictionCount(@Param("teamId") String teamId);



}
