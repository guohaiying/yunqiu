package com.yunqiu.dao;

import com.yunqiu.model.Invitation;
import com.yunqiu.model.TeamMember;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * 球队成员持久化
 * @author 武尊
 * @time 2017-01-16
 * @version 1.0
 */

@Mapper
public interface TeamMemberMapper {

    /**
     * 添加球队成员
     * @param teamMember
     */
    @Insert({
            "insert into pw_team_member(member_id,user_id,team_id,identity,jurisdiction,enqueue_time,jersey_no,place,status) values(",
            "#{member_id},#{user_id},#{team_id},#{identity},#{jurisdiction},#{enqueue_time},#{jersey_no},#{place},#{status})"
    })
    void insertTeamMember(TeamMember teamMember);

    /**
     * 根据球队ID跟用户ID查询球员
     * @param teamId
     * @param userId
     * @return
     */
    @Select("select * from pw_team_member where user_id=#{userId} AND team_id=#{teamId}")
    TeamMember selectTeamMemberByTeamIdAndUserId(@Param("teamId") String teamId,@Param("userId") String userId);

    /**
     * 查询球队成员
     * @param teamId
     * @return
     */
    @Select({
            "SELECT m.user_id,m.jersey_no,m.identity,m.status,u.portrait,u.nickname,IFNULL(m.place,u.position) AS place FROM pw_team_member AS m ",
            "JOIN pw_users AS u ON u.user_id = m.user_id WHERE m.team_id = #{teamId} AND m.status = 1"
    })
    List<Map<String,Object>> selectTeamMemberByTeamId(@Param("teamId") String teamId);

    /**
     * 修改权限
     * @param teamId
     * @param userId
     * @param jurisdiction
     */
    @Update("update pw_team_member set jurisdiction=#{jurisdiction} where team_id=#{teamId} AND user_id=#{userId}")
    void updateJurisdiction(@Param("teamId") String teamId,@Param("userId") String userId,@Param("jurisdiction") int jurisdiction);

    /**
     * 查询球队成员管理列表（申请加入球队的成员、球队成员）
     * @param teamId
     * @return
     */
    @Select({
            "SELECT m.user_id,m.jersey_no,m.identity,m.status,null AS join_id,u.portrait,u.nickname,IFNULL(m.place,u.position) AS place FROM pw_team_member AS m ",
            "JOIN pw_users AS u ON (u.user_id = m.user_id) WHERE m.team_id = #{teamId} AND m.status = 1 UNION ",
            "SELECT j.user_id,0,0,0,j.join_id,ju.portrait,ju.nickname,ju.position FROM pw_join_team AS j JOIN pw_users AS ju ON (ju.user_id = j.user_id) WHERE ",
            "j.team_id = #{teamId} AND j.status=1"
    })
    List<Map<String,Object>> selectTeamMemberManageList(@Param("teamId") String teamId);

    /**
     * 修改球员球衣号码
     * @param jersey_no
     * @param teamId
     * @param userId
     */
    @Update("update pw_team_member set jersey_no=#{jersey_no} where team_id=#{teamId} and user_id=#{userId}")
    void updateJerseyNo(@Param("jersey_no") int jersey_no,@Param("teamId") String teamId,@Param("userId") String userId);

    /**
     * 修改球员身份
     * @param teamId
     * @param userId
     * @param identity
     */
    @Update("update pw_team_member set identity=#{identity} where team_id=#{teamId} AND user_id=#{userId}")
    void updateIdentity(@Param("teamId") String teamId,@Param("userId") String userId,@Param("identity") int identity);

    /**
     * 修改球员位置
     * @param teamId
     * @param userId
     * @param place
     */
    @Update("update pw_team_member set place=#{place} where team_id=#{teamId} AND user_id=#{userId}")
    void updatePlace(@Param("teamId") String teamId,@Param("userId") String userId,@Param("place") String place);

    /**
     * 修改球员状态
     * @param teamId
     * @param userId
     * @param status
     */
    @Update("update pw_team_member set status=#{status} where team_id=#{teamId} AND user_id=#{userId}")
    void updateStatus(@Param("teamId") String teamId,@Param("userId") String userId,@Param("status") int status);

    /**
     * 获取球队总数
     * @param team_id
     * @return
     */
    @Select("SELECT COUNT(team_id) AS team_match FROM pw_team_member WHERE team_id=#{team_id}")
    Map<String,Integer> selectTeamMatchNumber(@Param("team_id") String team_id);

    /**
     * 获取最佳球员
     * @param team_id
     * @return
     */
    @Select({
            "SELECT gg.user_id,users.nickname,users.portrait,member.jersey_no,gg.type,MAX(total.total_number) AS number FROM pw_game_grand AS gg ",
            "JOIN pw_users AS users ON users.user_id=gg.user_id JOIN pw_team_member AS member ON member.team_id=gg.team_id JOIN ",
            "(SELECT grand.grand_id,SUM(grand.result) AS total_number FROM pw_game_grand AS grand WHERE grand.team_id=#{team_id} ",
            "AND (grand.type=1 OR grand.type=6 OR grand.type=3 OR grand.type=4) GROUP BY grand.user_id,grand.type ORDER BY SUM(grand.result) DESC) ",
            "AS total WHERE gg.grand_id=total.grand_id GROUP BY gg.type"
    })
    List<Map<String,Object>> getBestTeamMember(@Param("team_id") String team_id);

    /**
     * 获取球队管理员
     * @param team_id
     * @return
     */
    @Select({
            "SELECT * FROM pw_team_member WHERE `status`=1 AND (jurisdiction = 1 OR jurisdiction = 2) AND team_id = #{team_id}"
    })
    List<TeamMember> getTeamAdmin(@Param("team_id") String team_id);

    /**
     * 添加邀请球员信息
     * @param invitation
     */
    @Insert({
            "insert into pw_invitation(invitation_id,user_id,team_id,audit_status,invitation_time) values",
            "(#{invitation_id},#{user_id},#{team_id},#{audit_status},#{invitation_time})"
    })
    void insertInvitation(Invitation invitation);
}
