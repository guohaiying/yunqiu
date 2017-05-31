package com.yunqiu.dao;


import com.yunqiu.model.JoinLeague;
import com.yunqiu.model.PageCrt;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface JoinLeagueMapper {

    /**
     * 添加球队
     * @param joinleague
     */
    @Insert({
            "insert into pw_join_league(join_id,league_id,team_id,join_time,audit_status)  values(",
            "#{joinId},#{leagueId},#{teamId},#{joinTime},#{auditStatus})"
    })
    int insertJoinLeague(JoinLeague joinleague);

    /**
     * 修改
     * @param joinuleague
     */
    @Update("update pw_join_league set audit_status=#{auditStatus},audit_time=#{auditTime} where join_id=#{joinId}")
    int updateJoinLeague(JoinLeague joinuleague);

    /**
     * 根据id删除赛事球队
     * @param joinId
     */
    @Delete("DELETE FROM pw_join_league WHERE join_id = #{joinId}")
    int deleteById(@Param("joinId") String joinId);


    /** 查询总数*/
    @Select({"select count(*) as count from (select pjl.join_id as joinId,pjl.league_id as leagueId,pjl.team_id as teamId,pjl.join_time as joinTime," +
                    "pjl.audit_status as auditStatus,pjl.audit_time as auditTime,pjl.audit_opinion as auditOpinion," +
                    "pt.team_name as teamName,pt.badge,(select league_name from pw_league where league_id=pjl.league_id) as leagueName " +
            " from pw_join_league pjl,pw_team pt where pjl.league_id = #{map.leagueId} and pjl.team_id = pt.team_id) a where 1=1" })
    @Results({
            @Result(id = true, property = "count", column = "count")
    })
    int selectTotalPage(PageCrt page);


    /** 分页查询*/
    @Select({"select pjl.join_id as joinId,pjl.league_id as leagueId,pjl.team_id as teamId,pjl.join_time as joinTime,\n" +
            "pjl.audit_status as auditStatus,pjl.audit_time as auditTime,pjl.audit_opinion as auditOpinion,\n" +
            "pt.team_name as teamName,pt.badge,(select league_name from pw_league where league_id=pjl.league_id) as leagueName\n" +
            " from pw_join_league pjl,pw_team pt where pjl.league_id = #{map.leagueId} and pjl.team_id = pt.team_id"})
    @Results({
            @Result(id = true, property = "joinId", column = "joinId"),
            @Result(property = "leagueId", column = "leagueId"),
            @Result(property = "teamId", column = "teamId"),
            @Result(property = "joinTime", column = "joinTime"),
            @Result(property = "auditStatus", column = "auditStatus"),
            @Result(property = "auditTime", column = "auditTime"),
            @Result(property = "auditOpinion", column = "auditOpinion"),
            @Result(property = "teamName", column = "teamName"),
            @Result(property = "badge", column = "badge"),
            @Result(property = "leagueName", column = "leagueName")
    })
    List<Map> selectePaging(PageCrt page);


    //获取赛事名称
    @Select({"select league_name as leagueName from pw_league where league_id=#{leagueId}"})
    @Results({
            @Result(property = "leagueName", column = "leagueName")
    })
    String getJoinLeagueName(@Param("leagueId") String leagueId);

}
