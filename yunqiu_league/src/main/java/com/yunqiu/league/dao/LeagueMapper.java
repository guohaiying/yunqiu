package com.yunqiu.league.dao;

import com.yunqiu.league.dao.sqlProvider.LeagueSqlProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

/**
 * 数据库查询赛事
 */

@Mapper
public interface LeagueMapper {
    /**
     * 获取我所在球队的比赛总条数
     * @param user_id
     * @return
     */
    @SelectProvider(type = LeagueSqlProvider.class,method = "meTeamLeagueListTotal")
    int meTeamLeagueListTotal(@Param("user_id") String user_id,@Param("type") int type);

    /**
     * 查询我所在球队的赛事
     * @param params
     * @return
     */
    @SelectProvider(type = LeagueSqlProvider.class,method = "meTeamLeagueList")
    List<Map<String,Object>> meTeamLeagueList(Map<String,Object> params);

    /**
     * 获取赛事详情
     * @param league_id
     * @return
     */
    @Select("SELECT * FROM pw_league WHERE league_id = #{league_id}")
    Map<String,Object> leagueInfo(@Param("league_id") String league_id);
}
