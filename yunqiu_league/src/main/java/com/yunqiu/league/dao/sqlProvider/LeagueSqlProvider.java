package com.yunqiu.league.dao.sqlProvider;

import com.yunqiu.util.Utils;

import java.util.Map;

/**
 * 赛事管理的动态sql拼写
 */
public class LeagueSqlProvider {

    public String meTeamLeagueListTotal(String user_id,int type){
        StringBuffer sql = new StringBuffer();
        if (type == 1){
            sql.append("SELECT COUNT(le.league_id) AS number FROM pw_team_member AS m ");
            sql.append("JOIN pw_join_league AS jl ON jl.team_id = m.team_id ");
            sql.append("JOIN pw_league AS le ON le.league_id = jl.league_id  WHERE m.user_id = #{user_id}");
        }else {
            sql.append("SELECT COUNT(league_id) AS number FROM pw_league");
        }
        return sql.toString();
    }
    /**
     * 查询我所在球队的赛事
     * @param params
     * @return
     */
    public String meTeamLeagueList(Map<String,Object> params){
        StringBuffer sql = new StringBuffer();
        if ((int)params.get("type") == 1){
            sql.append("SELECT distinct le.league_id,le.league_id,le.league_abbreviation,le.league_icon,le.game_system,");
            sql.append("le.start_time,le.end_time,le.apply_start_time,le.apply_end_time,le.league_site,");
            sql.append("le.province,le.city,le.area,le.status");
            sql.append(" FROM pw_team_member AS m ");
            sql.append("JOIN pw_join_league AS jl ON jl.team_id = m.team_id ");
            sql.append("JOIN pw_league AS le ON le.league_id = jl.league_id ");
            if ((int)params.get("status") != 0){
                sql.append("AND le.`status`="+params.get("status")+" ");
            }
            sql.append("WHERE m.user_id = '"+params.get("user_id")+"' ");
            if (!Utils.isNull((String) params.get("team_id"))){
                sql.append("AND m.team_id='"+params.get("team_id")+"' ");
            }
        }else {
            sql.append("SELECT le.league_id,le.league_id,le.league_abbreviation,le.league_icon,le.game_system,");
            sql.append("le.start_time,le.end_time,le.apply_start_time,le.apply_end_time,le.league_site,le.province,le.city,");
            sql.append("le.area,le.status FROM pw_league AS le WHERE 1=1 ");
            if ((int)params.get("status") != 0){
                sql.append("AND le.`status`="+params.get("status")+" ");
            }
            if ((int)params.get("game_system") != 0){
                sql.append("AND le.game_system="+params.get("game_system")+" ");
            }
        }
        sql.append("ORDER BY le.apply_end_time ASC LIMIT "+params.get("start_now")+","+params.get("pageSize"));
        return sql.toString();
    }
}
