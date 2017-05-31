package com.yunqiu.tournament.dao;

import com.yunqiu.tournament.dao.sqlprovider.TournamentSqlProvider;
import com.yunqiu.tournament.model.Tournament;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * 比赛持久化
 * @author 武尊
 * @time 2017-02-23
 */

@Mapper
public interface TournamentMapper {

    /**
     * 添加比赛
     * @param tournament
     */
    @Insert({
            "insert into pw_tournament(game_id,user_id,game_name,entry_teamA,entry_teamB,",
            "uniform_teamA,uniform_teamB,score_teamA,score_teamB,spot_teamA,spot_teamB,game_time,continue_time,apply_end_time,",
            "game_system,game_site,game_cost,value_added,game_status,score_status,",
            "audit_reason,refuse_reason,cancel_reason,classify) values(#{game_id},#{user_id},",
            "#{game_name},#{entry_teamA},#{entry_teamB},#{uniform_teamA},#{uniform_teamB},",
            "#{score_teamA},#{score_teamB},#{spot_teamA},#{spot_teamB},#{game_time},#{continue_time},#{apply_end_time},",
            "#{game_system},#{game_site},#{game_cost},#{value_added},#{game_status},",
            "#{score_status},#{audit_reason},#{refuse_reason},#{cancel_reason},#{classify})"
    })
    void insertGame(Tournament tournament);

    /**
     * 修改比赛信息
     * @param tournament
     */
    @SelectProvider(type = TournamentSqlProvider.class,method = "updateGame")
    void updateGame(Tournament tournament);

    /**
     * 根据比赛id查询比赛
     * @param game_id
     * @return
     */
    @Select("SELECT * FROM pw_tournament WHERE game_id=#{game_id}")
    Tournament selectGameByGameId(@Param("game_id") String game_id);

    @Select({
            "SELECT t.*,ta.team_name AS teamA_name,ta.badge AS teamA_badge,tb.team_name AS teamB_name,tb.badge AS teamB_badge ",
            "FROM pw_tournament AS t JOIN pw_team AS ta ON ta.team_id=t.entry_teamA LEFT JOIN pw_team AS tb ON tb.team_id=t.entry_teamB ",
            "WHERE t.game_id=#{game_id}"
    })
    Map<String,Object> selectGameByGameIdByMap(@Param("game_id") String game_id);

   /**
     * 获取比赛列表（用户所在的所有球队的比赛）+训练 的总条数
     * @param user_id
     * @return
     */
    @Select({
            "SELECT COUNT(t.game_id) FROM pw_team_member AS m JOIN pw_tournament AS t ON ",
            "t.entry_teamA=m.team_id OR t.entry_teamB=m.team_id WHERE m.user_id=#{user_id}"
    })
    int selectTeamGameListTotal(@Param("user_id") String user_id);

    /**
     * 获取用户所在的球队的所有比赛、训练
     * @param params
     * @return
     */
    @SelectProvider(type = TournamentSqlProvider.class,method = "selectGameList")
    List<Map<String,Object>> selectGameList(Map<String,Object> params);

    /**
     * 获取约战列表
     * @param params
     * @return
     */
    @SelectProvider(type = TournamentSqlProvider.class,method = "getAboutGameList")
    List<Map<String,Object>> getAboutGameList(Map<String,Object> params);

    /**
     * 获取约战总数
     * @return
     */
    @SelectProvider(type = TournamentSqlProvider.class,method = "getAboutGameListTotal")
    List<Map<String,Object>> getAboutGameTotal(Map<String,Object> params);

    /**
     * 获取收藏的比赛列表总条数
     * @param user_id
     * @return
     */
    @Select({
            "SELECT COUNT(collect_id) FROM pw_game_collection WHERE user_id=#{user_id}"
    })
    int selectCollectionGameListTotal(@Param("user_id") String user_id);

    /**
     * 获取用户收藏的比赛列表
     * @param params
     * @return
     */
    @SelectProvider(type = TournamentSqlProvider.class,method = "selectCollectionGameList")
    List<Map<String,Object>> selectCollectionGameList(Map<String,Object> params);

    /**
     * 获取所有比赛信息
     * @return
     */
    @Select({
            "select * from pw_tournament where game_status=0 or game_status=1 or game_status=2 or game_status=3 or game_status=4 or game_status=5"
    })
    List<Tournament> selectTournamentByAll();
}
