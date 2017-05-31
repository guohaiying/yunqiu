package com.yunqiu.dao;


import com.yunqiu.dao.sqlprovider.TournamentSqlProvider;
import com.yunqiu.model.PageCrt;
import com.yunqiu.model.Tournament;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface TournamentMapper {

    /**
     * 添加赛程
     * @param rounds
     */
    @Insert({
            "INSERT INTO pw_tournament(game_id,rounds,user_id,league_id,game_name,entry_teamA,entry_teamB,uniform_teamA,uniform_teamB,score_teamA,score_teamB,game_time,continue_time,apply_end_time,game_system,game_site,game_cost,\n" +
                    "value_added,audit_status,fight_status,game_status,score_status,audit_reason,refuse_reason,cancel_reason,classify) \n" +
                    " SELECT #{newGameId} as game_id,#{rounds} as rounds,#{userId} as user_id,#{leagueId} as league_id,game_name,entry_teamA,entry_teamB,uniform_teamA,uniform_teamB,score_teamA,score_teamB,game_time,continue_time,apply_end_time,game_system,game_site,game_cost,\n" +
                    "value_added,audit_status,fight_status,game_status,score_status,audit_reason,refuse_reason,cancel_reason,2 as classify FROM pw_tournament WHERE game_id=#{gameId};"
    })
    int insertTournament(@Param("rounds") int rounds,@Param("newGameId") String newGameId,@Param("gameId") String gameId,@Param("userId") String userId,@Param("leagueId") String leagueId );


    /**
     * 添加比赛
     * @param tournament
     */
    @Insert({
            "INSERT INTO pw_tournament(game_id,user_id,game_name,entry_teamA,entry_teamB,uniform_teamA,uniform_teamB,score_teamA,score_teamB,spot_teamA,spot_teamB,game_time,continue_time,apply_end_time,game_system,game_site,game_cost,\n" +
                    "value_added,game_status,score_status,audit_reason,refuse_reason,cancel_reason,classify,inform_video,show_status,entering_status) \n" +
                    "value(#{gameId},#{userId},#{gameName},#{entryTeamA},#{entryTeamB},#{uniformTeamA},#{uniformTeamB},#{scoreTeamA},#{scoreTeamB},#{spotTeamA},#{spotTeamB},#{gameTime},#{continueTime},#{applyEndTime},#{gameSystem},#{gameSite},#{gameCost}" +
                    ",#{valueAdded},#{gameStatus},#{scoreStatus},#{auditReason},#{refuseReason},#{cancelReason},#{classify},#{informVideo},#{showStatus},#{enteringStatus})"
    })
    int insertTournamentList(Tournament tournament);


    /**
     * 修改比赛信息
     * @param tournament
     */
    @Update("update pw_tournament set game_name=#{gameName},entry_teamA=#{entryTeamA},entry_teamB=#{entryTeamB},uniform_teamA=#{uniformTeamA},uniform_teamB=#{uniformTeamB},\n" +
            "score_teamA=#{scoreTeamA},score_teamB=#{scoreTeamB},score_teamB=#{scoreTeamB},spot_teamA=#{spotTeamA},spot_teamB=#{spotTeamB},game_time=#{gameTime},continue_time=#{continueTime},\n" +
            "apply_end_time=#{applyEndTime},game_system=#{gameSystem},game_site=#{gameSite},game_cost=#{gameCost},value_added=#{valueAdded},game_status=#{gameStatus},score_status=#{scoreStatus}\n" +
            ",audit_reason=#{auditReason},refuse_reason=#{refuseReason},cancel_reason=#{cancelReason},classify=#{classify},inform_video=#{informVideo},show_status=#{showStatus},entering_status=#{enteringStatus}\n" +
            "where game_id=#{gameId}")
    int updateTournamentList(Tournament tournament);

    /**
     * 修改场地信息
     * @param tournament
     */
    @Update("update pw_tournament set rounds=#{rounds} where game_id=#{gameId}")
    int updateTournament(Tournament tournament);

    /**
     * 根据id删除赛程
     * @param gameId
     */
    @Delete("DELETE FROM pw_tournament WHERE game_id = #{gameId}")
    int deleteTournamentById(@Param("gameId") String gameId);

    /**
     * 根据id查询场地
     * @param gameId
     * @return
     */
    @Select("select * from aa_place where 1=1 AND place_id=#{placeId}")
    @Results({
            @Result(id = true, property = "placeId", column = "place_id"),
            @Result(property = "placeName", column = "place_name"),
            @Result(property = "createTime", column = "createTime")
    })
    Tournament selectTournamentById(@Param("gameId") String gameId);


    /** 查询总数*/
    @Select({"<script>",
            "select count(*) as count from (select pt.game_id as gameId,pt.user_id as userId,(select team_name from pw_team where team_id=pt.entry_teamA) as teamAName,\n" +
                    "(select team_name from pw_team where team_id=pt.entry_teamB) as teamBName,pt.game_time as gameTime,pt.classify,pt.rounds,\n" +
                    "pt.game_site as gameSite,pt.game_status as gameStatus\n" +
                    "from pw_tournament pt where pt.league_id=1 and pt.classify=2) a where 1=1" +
             "<when test='map.rounds!=null'>",
            "AND rounds like '%${map.rounds}%'",
            "</when>",
            "</script> "})
    @Results({
            @Result(id = true, property = "count", column = "count")
    })
    int selectTotalPage(PageCrt page);


    /** 分页查询*/
    @Select({"<script>",
            "select pt.game_id as gameId,pt.user_id as userId,(select team_name from pw_team where team_id=pt.entry_teamA) as teamAName,\n" +
                    "(select team_name from pw_team where team_id=pt.entry_teamB) as teamBName,pt.game_time as gameTime,pt.classify,pt.rounds,\n" +
                    "pt.game_site as gameSite,pt.game_status as gameStatus\n" +
                    "from pw_tournament pt where pt.league_id=#{map.leagueId} and pt.classify=2 " +
             "<when test='map.rounds!=null'>",
            "AND rounds like '%${map.rounds}%'",
            "</when>",
             "<when test='sidx!=null'>",
            "ORDER BY ${sidx} ${sord}",
            "</when> LIMIT #{index},#{rows}",
            "</script> "})
    @Results({
            @Result(id = true, property = "placeId", column = "place_id"),
            @Result(property = "placeName", column = "place_name"),
            @Result(property = "createTime", column = "createTime")
    })
    List<Map> selectePaging(PageCrt page);


    /** 查询比赛总条数*/
    @Select({"<script>",
            "select count(*) as count from (select pt.game_id as gameId,pt.user_id as userId,pt.game_name as gameName,(select team_name from pw_team where team_id=pt.entry_teamA) as teamAName,\n" +
                    "(select team_name from pw_team where team_id=pt.entry_teamB) as teamBName,pt.uniform_teamA as uniformTeamA,pt.uniform_teamB as uniformTeamB,\n" +
                    "pt.score_teamA as scoreTeamA,pt.score_teamB as scoreTeamB,pt.spot_teamA as spotTeamA,pt.spot_teamB as spotTeamB,pt.game_time as gameTime,\n" +
                    "pt.continue_time as continueTime,pt.apply_end_time as applyEndTime,pt.game_system as gameSystem,pt.game_site as gameSite,pt.game_cost as gameCost,\n" +
                    "pt.value_added as valueAdded,pt.game_status as gameStatus,pt.score_status as scoreStatus,pt.audit_reason as auditReason,pt.refuse_reason as refuseReason,\n" +
                    "pt.cancel_reason as cancelReason,pt.classify,pt.inform_video as informVideo,pt.show_status as showStatus,pt.entering_status as enteringStatus\n" +
                    " from pw_tournament pt where 1=1 ) a where 1=1" +
                    "<when test='map.rounds!=null'>",
            "AND rounds like '%${map.rounds}%'",
            "</when>",
            "</script> "})
    @Results({
            @Result(id = true, property = "count", column = "count")
    })
    int selectCountTournament(PageCrt page);


    /** 分页查询比赛 */
    @Select({"<script>",
            "select pt.game_id as gameId,pt.user_id as userId,pt.game_name as gameName,(select team_name from pw_team where team_id=pt.entry_teamA) as teamAName,\n" +
                    "(select team_name from pw_team where team_id=pt.entry_teamB) as teamBName,pt.uniform_teamA as uniformTeamA,pt.uniform_teamB as uniformTeamB,\n" +
                    "pt.score_teamA as scoreTeamA,pt.score_teamB as scoreTeamB,pt.spot_teamA as spotTeamA,pt.spot_teamB as spotTeamB,pt.game_time as gameTime,\n" +
                    "pt.continue_time as continueTime,pt.apply_end_time as applyEndTime,pt.game_system as gameSystem,pt.game_site as gameSite,pt.game_cost as gameCost,\n" +
                    "pt.value_added as valueAdded,pt.game_status as gameStatus,pt.score_status as scoreStatus,pt.audit_reason as auditReason,pt.refuse_reason as refuseReason,\n" +
                    "pt.cancel_reason as cancelReason,pt.classify,pt.inform_video as informVideo,pt.show_status as showStatus,pt.entering_status as enteringStatus\n" +
                    " from pw_tournament pt where 1=1 " +
            "<when test='map.rounds!=null'>",
            "AND rounds like '%${map.rounds}%'",
            "</when>",
            "<when test='sidx!=null'>",
            "ORDER BY ${sidx} ${sord}",
            "</when> LIMIT #{index},#{rows}",
            "</script> "})
    @Results({
            @Result(id = true, property = "placeId", column = "place_id"),
            @Result(property = "placeName", column = "place_name"),
            @Result(property = "createTime", column = "createTime")
    })
    List<Map> selectPagingTournament(PageCrt page);


    /** 查询所有比赛 */
    @Select("select pt.game_id as gameId,pt.game_name as gameName from pw_tournament pt group by game_name")
    @Results({
            @Result(id = true, property = "gameId", column = "gameId"),
            @Result(property = "gameName", column = "gameName")
    })
    List<Tournament> getTournamentList();


    @Select("select pt.user_id as userId,pt.team_id as teamId,\n" +
            "(select team_name from pw_team where team_id = pt.team_id) as teamName,\n" +
            "pt.jersey_no as jerseyNo,\n" +
            "(select nickname from pw_users where user_id=pt.user_id) as nickname\n" +
            "from pw_team_member pt\n" +
            "where pt.team_id = (select entry_teamA from pw_tournament where game_id=#{gameId}) \n")
    @Results({
            @Result(id = true, property = "gameId", column = "gameId"),
            @Result(property = "gameName", column = "gameName")
    })
    List<Map<String,Object>> getEntryTeamAList(@Param("gameId")  String gameId);

    @Select("select pt.user_id as userId,pt.team_id as teamId,\n" +
            "(select team_name from pw_team where team_id = pt.team_id) as teamName,\n" +
            "pt.jersey_no as jerseyNo,\n" +
            "(select nickname from pw_users where user_id=pt.user_id) as nickname\n" +
            "from pw_team_member pt\n" +
            "where pt.team_id = (select entry_teamB from pw_tournament where game_id=#{gameId}) ")
    @Results({
            @Result(id = true, property = "gameId", column = "gameId"),
            @Result(property = "gameName", column = "gameName")
    })
    List<Map<String,Object>> getEntryTeamBList(@Param("gameId") String gameId);


    @Select("select pt.user_id as userId,pt.team_id as teamId,\n" +
            "(select team_name from pw_team where team_id = pt.team_id) as teamName,\n" +
            "pt.jersey_no as jerseyNo,\n" +
            "(select nickname from pw_users where user_id=pt.user_id) as nickname\n" +
            "from pw_team_member pt\n" +
            "where  pt.team_id = #{teamId}")
    @Results({
            @Result(id = true, property = "gameId", column = "gameId"),
            @Result(property = "gameName", column = "gameName")
    })
    List<Map<String,Object>> getEntryTeamByTeamId(@Param("gameId") String gameId,@Param("teamId") String teamId);

    @Select("select pt.user_id as userId,pt.team_id as teamId,\n" +
            "(select team_name from pw_team where team_id = pt.team_id) as teamName,\n" +
            "pt.jersey_no as jerseyNo,\n" +
            "(select nickname from pw_users where user_id=pt.user_id) as nickname\n" +
            "from pw_team_member pt WHERE pt.team_id in ((SELECT entry_teamA from pw_tournament where game_id =#{gameId}),(SELECT entry_teamB from pw_tournament where game_id = #{gameId}))")
    @Results({
            @Result(id = true, property = "gameId", column = "gameId"),
            @Result(property = "gameName", column = "gameName")
    })
    List<Map<String,Object>> getEntryTeamAllUser(@Param("gameId") String gameId);

    @Select("select team.team_name as teamName from pw_tournament t,pw_team team where t.game_id = #{gameId} and t.entry_teamA = team.team_id")
    @Results({
            @Result(property = "teamName", column = "teamName")
    })
    String getEntryTeamAName(@Param("gameId")  String gameId);

    @Select("select team.team_name as teamName from pw_tournament t,pw_team team where t.game_id = #{gameId} and t.entry_teamB = team.team_id")
    @Results({
            @Result(property = "teamName", column = "teamName")
    })
    String getEntryTeamBName(@Param("gameId")  String gameId);


    @Select("select t.entry_teamA as entryTeamAId,t.entry_teamB as entryTeamBId,\n" +
            "(select team_name from pw_team where team_id = t.entry_teamA) as entryTeamAName,\n" +
            "(select team_name from pw_team where team_id = t.entry_teamB) as entryTeamBName,score_teamA as scoreTeamA,score_teamB as scoreTeamB \n" +
            "from pw_tournament t where t.game_id=#{gameId}\n" +
            "\n")
    @Results({
            @Result(property = "teamName", column = "teamName")
    })
    Map<String, Object> getEntryTeamNameAB(@Param("gameId")  String gameId);

    /**
     * 修改比赛信息
     * @param tournament
     */
    @SelectProvider(type = TournamentSqlProvider.class,method = "updateGame")
    void updateGame(Tournament tournament);
}
