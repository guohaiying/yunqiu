package com.yunqiu.tournament.dao.sqlprovider;

import com.yunqiu.tournament.model.Tournament;
import com.yunqiu.util.Utils;

import java.util.Map;

/**
 * 比赛管理sql拼接
 */
public class TournamentSqlProvider {

    /**
     * 修改比赛sql拼接
     * @param tournament
     * @return
     */
    public String updateGame(Tournament tournament){
        StringBuffer sql = new StringBuffer();
        sql.append("update pw_tournament set game_id=#{game_id}");
        if (!Utils.isNull(tournament.getUser_id())){
            sql.append(",user_id=#{user_id}");
        }
        if (!Utils.isNull(tournament.getGame_name())){
            sql.append(",game_name=#{game_name}");
        }
        if (!Utils.isNull(tournament.getEntry_teamA())){
            sql.append(",entry_teamA=#{entry_teamA}");
        }
        if (!Utils.isNull(tournament.getEntry_teamB())){
            sql.append(",entry_teamB=#{entry_teamB}");
        }
        if (tournament.getUniform_teamA() != 0){
            sql.append(",uniform_teamA=#{uniform_teamA}");
        }
        if (tournament.getUniform_teamB() != 0){
            sql.append(",uniform_teamB=#{uniform_teamB}");
        }
        if (tournament.getScore_teamA() != 0){
            sql.append(",score_teamA=#{score_teamA}");
        }
        if (tournament.getScore_teamB() != 0){
            sql.append(",score_teamB=#{score_teamB}");
        }
        if (tournament.getSpot_teamA() != 0){
            sql.append(",spot_teamA=#{spot_teamA}");
        }
        if (tournament.getSpot_teamB() != 0){
            sql.append(",spot_teamB=#{spot_teamB}");
        }
        if (tournament.getGame_time() != null){
            sql.append(",game_time=#{game_time}");
        }
        if (tournament.getContinue_time() != 0){
            sql.append(",continue_time=#{continue_time}");
        }
        if (tournament.getApply_end_time() != null){
            sql.append(",apply_end_time=#{apply_end_time}");
        }
        if (tournament.getGame_system() != 0){
            sql.append(",game_system=#{game_system}");
        }
        if (!Utils.isNull(tournament.getGame_site())){
            sql.append(",game_site=#{game_site}");
        }
        if (tournament.getGame_cost() != 0){
            sql.append(",game_cost=#{game_cost}");
        }
        if (tournament.getValue_added() != 0){
            sql.append(",value_added=#{value_added}");
        }
        if (tournament.getGame_status() != 0){
            sql.append(",game_status=#{game_status}");
        }
        if (tournament.getScore_status() != 0){
            sql.append(",score_status=#{score_status}");
        }
        if (!Utils.isNull(tournament.getAudit_reason())){
            sql.append(",audit_reason=#{audit_reason}");
        }
        if (!Utils.isNull(tournament.getRefuse_reason())){
            sql.append(",refuse_reason=#{refuse_reason}");
        }
        if (!Utils.isNull(tournament.getCancel_reason())){
            sql.append(",cancel_reason=#{cancel_reason}");
        }
        if (tournament.getClassify() != 0){
            sql.append(",classify=#{classify}");
        }
        sql.append(" WHERE game_id=#{game_id}");
        return sql.toString();
    }

    //获取球队的比赛列表（包括训练）
    public String selectGameList(Map<String,Object> params){
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT distinct t.game_id, t.*,teamA.team_name AS teamA_name,teamA.badge AS teamA_badge,teamB.team_name AS teamB_name,teamB.badge AS teamB_badge,");
        sql.append("IF((SELECT video.game_id FROM pw_video AS video WHERE video.game_id=t.game_id AND video.classify=2 AND video.if_show=1 LIMIT 1)=t.game_id,2,1) AS isVideo ");
        sql.append("FROM pw_team_member AS m JOIN pw_tournament AS t ON (t.entry_teamA=m.team_id OR t.entry_teamB=m.team_id) ");
        if ((int)params.get("game_status") == 7 || (int)params.get("game_status") == 8 || (int)params.get("game_status") == 9){
            sql.append("AND (t.game_status=7 OR t.game_status=8 OR t.game_status=9) ");
        }else if ((int)params.get("game_status") != 0){
            sql.append("AND t.game_status="+params.get("game_status")+" ");
        }
        if (!Utils.isNull((String) params.get("game_time"))){
            sql.append("AND t.game_time LIKE '%"+params.get("game_time")+"%' ");
        }
        sql.append("JOIN pw_team AS teamA ON teamA.team_id=t.entry_teamA LEFT JOIN pw_team AS teamB ON teamB.team_id=t.entry_teamB ");
        sql.append("WHERE m.user_id='"+params.get("user_id")+"' ");
        if (!Utils.isNull((String) params.get("team_id"))){
            sql.append("AND m.team_id ='"+params.get("team_id")+"' ");
        }
        sql.append("ORDER BY t.game_time ASC ");
        if ((int)params.get("pageSize") != 0){
            sql.append("LIMIT "+params.get("start_now")+","+params.get("pageSize"));
        }
        return sql.toString();
    }

    //获取约战广场
    public String getAboutGameList(Map<String,Object> params){
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT team.team_id,team.team_name,team.badge,team.province,team.city,team.area,t.game_id,t.game_time,t.game_site,t.game_system ");
        sql.append("FROM pw_tournament AS t LEFT JOIN pw_team AS team ON team.team_id=t.entry_teamA ");
        if (!Utils.isNull((String) params.get("name"))){
            sql.append("LEFT JOIN pw_users AS u ON u.user_id=t.user_id ");
        }
        sql.append("WHERE t.classify = 4 AND t.game_status =2 ");
        if (!Utils.isNull((String) params.get("name"))){
            sql.append("AND (team.team_name LIKE '%"+params.get("name")+"%' OR u.nickname LIKE '%"+params.get("name")+"%') ");
        }
        if (!Utils.isNull((String) params.get("game_time"))){
            sql.append("AND t.game_time LIKE '%"+params.get("game_time")+"%' ");
        }
        if ((int)params.get("game_system") != 0){
            sql.append("AND t.game_system="+params.get("game_system")+" ");
        }
        sql.append("ORDER BY t.game_time ASC LIMIT "+params.get("start_now")+","+params.get("pageSize"));
        return sql.toString();
    }

    //获取约战广场总数
    public String getAboutGameListTotal(Map<String,Object> params){
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT team.team_id,team.team_name,team.badge,team.province,team.city,team.area,t.game_id,t.game_time,t.game_site,t.game_system ");
        sql.append("FROM pw_tournament AS t LEFT JOIN pw_team AS team ON team.team_id=t.entry_teamA ");
        if (!Utils.isNull((String) params.get("name"))){
            sql.append("LEFT JOIN pw_users AS u ON u.user_id=t.user_id ");
        }
        sql.append("WHERE t.classify = 4 AND t.game_status =2 ");
        if (!Utils.isNull((String) params.get("name"))){
            sql.append("AND (team.team_name LIKE '%"+params.get("name")+"%' OR u.nickname LIKE '%"+params.get("name")+"%') ");
        }
        if (!Utils.isNull((String) params.get("game_time"))){
            sql.append("AND t.game_time LIKE '%"+params.get("game_time")+"%' ");
        }
        if ((int)params.get("game_system") != 0){
            sql.append("AND t.game_system="+params.get("game_system")+" ");
        }
        sql.append("ORDER BY t.game_time ASC");
        return sql.toString();
    }

    //获取收藏的比赛列表
    public String selectCollectionGameList(Map<String,Object> params){
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT t.*,teamA.team_name AS teamA_name,teamA.badge AS teamA_badge,teamB.team_name AS teamB_name,teamB.badge AS teamB_badge,");
        sql.append("IF((SELECT video.game_id FROM pw_video AS video WHERE video.game_id=t.game_id AND video.classify=2 AND video.if_show=1 LIMIT 1)=t.game_id,2,1) AS isVideo ");
        sql.append("FROM pw_game_collection AS g JOIN pw_tournament AS t ON t.game_id = g.game_id ");
        sql.append("JOIN pw_team AS teamA ON teamA.team_id=t.entry_teamA LEFT JOIN pw_team AS teamB ON teamB.team_id=t.entry_teamB ");
        sql.append("WHERE g.user_id='"+params.get("user_id")+"' ");
        sql.append("ORDER BY t.game_time ASC ");
        if ((int)params.get("pageSize") != 0){
            sql.append("LIMIT "+params.get("start_now")+","+params.get("pageSize"));
        }
        return sql.toString();
    }
}