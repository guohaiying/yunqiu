package com.yunqiu.league.dao.sqlProvider;

import java.util.Map;

/**
 * Created by 11366 on 2017/3/2.
 */

public class VideoSqlProvider {
    public String selectVide(Map<String,Object> params){
        StringBuffer sql = new StringBuffer();
        sql.append("select * from pw_video where 1=1 ");
        if ((int)params.get("classify") == 1){
            sql.append("and classify=1 ");
            sql.append("and league_id='"+params.get("id")+"'");
        }else if ((int)params.get("classify") == 2){
            sql.append("and classify=2 ");
            sql.append("and game_id='"+params.get("id")+"'");
        }else {
            sql.append("and classify=3");
        }
        sql.append(" ORDER BY createTime DESC LIMIT "+params.get("start_now")+","+params.get("pageSize"));
        return sql.toString();
    }

    public String selectVideTotal(Map<String,Object> params){
        StringBuffer sql = new StringBuffer();
        sql.append("select COUNT(video_id) AS number from pw_video where 1=1 ");
        if ((int)params.get("classify") == 1){
            sql.append("and classify= 1 ");
            sql.append("and league_id='"+params.get("id")+"'");
        }else if ((int)params.get("classify") == 2){
            sql.append("and classify=2 ");
            sql.append("and game_id='"+params.get("id")+"'");
        }else {
            sql.append("and classify= 3");
        }
        return sql.toString();
    }
}
