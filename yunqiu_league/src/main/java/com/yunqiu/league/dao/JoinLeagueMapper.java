package com.yunqiu.league.dao;

import com.yunqiu.league.model.JoinLeague;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * 赛事报名
 */

@Mapper
public interface JoinLeagueMapper {

    /**
     * 查询我所在的球队是否有赛事报名信息
     * @param user_id
     * @return
     */
    @Select("SELECT jl.* FROM pw_team_member AS m JOIN pw_join_league AS jl ON jl.team_id = m.team_id WHERE m.user_id = #{user_id}")
    List<Map<String,Object>> selectJoinStatus(@Param("user_id") String user_id);

    /**
     * 报名赛事
     * @param joinLeague
     */
    @Insert({
            "insert into pw_join_league(join_id,league_id,team_id,join_time,audit_status,audit_time,audit_opinion) values(",
            "#{join_id},#{league_id},#{team_id},#{join_time},#{audit_status},#{audit_time},#{audit_opinion})"
    })
    void insertJoinLeague(JoinLeague joinLeague);

    /**
     * 获取已报名球队的总数
     * @param league_id
     * @return
     */
    @Select("SELECT COUNT(j.join_id) AS number FROM pw_join_league AS j WHERE j.league_id=#{league_id}")
    int selectJoinLeagueTotal(@Param("league_id") String league_id);

    /**
     * 获取已报名的球队
     * @param start_now
     * @param pageSize
     * @param league_id
     * @return
     */
    @Select({
            "SELECT t.team_id,t.badge,t.team_name,t.province,t.city,t.area,(SELECT COUNT(m.member_id) FROM pw_team_member AS m WHERE m.team_id=j.team_id) AS number ",
            "FROM pw_join_league AS j JOIN pw_team AS t ON t.team_id=j.team_id WHERE j.league_id=#{league_id} LIMIT #{start_now},#{pageSize}"
    })
    List<Map<String,Object>> selectJoinLeague(@Param("start_now") int start_now,@Param("pageSize") int pageSize,@Param("league_id") String league_id);
}
