package com.yunqiu.dao.sqlprovider;

import com.yunqiu.util.Utils;

import java.util.Map;

/**
 * 球队sql拼接
 */
public class TeamSqlProvider {


    /**
     * 获取所有球队、搜索球队
     * @param params
     * @return
     */
    //"where team.team_id !=#{teamId}"
    public String selectTeamAllList(Map<String,Object> params){
        StringBuffer sql = new StringBuffer();
        sql.append("select team.team_id,team.team_name,team.badge,team.province,team.city,team.area,");
        sql.append("(SELECT COUNT(m.member_id) FROM pw_team_member AS m WHERE m.team_id=team.team_id) AS number ");
        sql.append("from pw_team as team ");
        //球队名称不为null，执行模糊搜索球队
        if (!Utils.isNull((String) params.get("teamName"))){
            sql.append("where team.team_name LIKE '%"+params.get("teamName")+"%'");
        }else {
            //球队名称为null，执行查询分页查询所有球队
            if ((int)params.get("pageNum") == 0){
                sql.append("where team.team_id !="+params.get("team_id"));
            }else {
                sql.append("where team.team_id !="+params.get("team_id")+" LIMIT "+params.get("start_now")+","+params.get("pageSize"));
            }
        }

        return sql.toString();
    }
}
