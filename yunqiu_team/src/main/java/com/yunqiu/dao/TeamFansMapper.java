package com.yunqiu.dao;

import com.yunqiu.model.TeamFans;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * Created by 11366 on 2017/2/11.
 */

@Mapper
public interface TeamFansMapper {

    /**
     * 添加球队关注
     * @param teamFans
     */
    @Insert({
            "insert into pw_team_fans(fans_id,user_id,team_id) values",
            "(#{fans_id},#{user_id},#{team_id})"
    })
    void insertTeamFans(TeamFans teamFans);

    /**
     * 根据用户id跟被关注的球队id查询关注信息
     * @param user_id
     * @param team_id
     * @return
     */
    @Select("select * from pw_team_fans where user_id=#{user_id} and team_id=#{team_id}")
    TeamFans selectTeamFans(@Param("user_id") String user_id,@Param("team_id") String team_id);

    /**
     * 删除球队关注
     * @param fans_id
     */
    @Delete("delete from pw_team_fans where fans_id=#{fans_id}")
    void deleteFans(@Param("fans_id") String fans_id);

    /**
     * 获取关注的球队的总数
     * @param user_id
     * @return
     */
    @Select("SELECT COUNT(fans_id) AS number FROM pw_team_fans WHERE user_id=#{user_id}")
    int selectFocusTotal(@Param("user_id") String user_id);

    /**
     * 获取球队的粉丝总数
     * @param team_id
     * @return
     */
    @Select("SELECT COUNT(fans_id) AS number FROM pw_team_fans WHERE team_id=#{team_id}")
    int selectTeamFansTotal(@Param("team_id") String team_id);

    /**
     *
     * 获取粉丝列表
     * @param team_id
     * @param start_now
     * @param pageSize
     * @return
     */
    @Select({
            "SELECT u.user_id,u.nickname,u.portrait,u.position,u.province,u.city,u.area FROM pw_team_fans AS f ",
            "JOIN pw_users AS u ON u.user_id=f.user_id WHERE f.team_id=#{team_id} LIMIT #{start_now},#{pageSize}"
    })
    List<Map<String,Object>> selectTeamFansList(@Param("team_id") String team_id,@Param("start_now") int start_now,@Param("pageSize") int pageSize);

    /**
     * 分页查询关注的球队列表
     * @param user_id
     * @param start_now
     * @param pageSize
     * @return
     */
    @Select({
            "SELECT t.team_id,t.team_name,t.badge,t.province,t.city,t.area,(SELECT COUNT(m.member_id) FROM ",
            "pw_team_member AS m WHERE m.team_id=f.team_id) AS number FROM pw_team_fans AS f JOIN ",
            "pw_team AS t ON t.team_id=f.team_id WHERE f.user_id=#{user_id} LIMIT #{start_now},#{pageSize}"
    })
    List<Map<String,Object>> selectPagingFocus(@Param("user_id") String user_id, @Param("start_now") int start_now, @Param("pageSize") int pageSize);
}
