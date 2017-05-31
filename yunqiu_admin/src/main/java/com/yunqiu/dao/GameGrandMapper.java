package com.yunqiu.dao;


import com.yunqiu.model.GameGrand;
import com.yunqiu.model.PageCrt;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface GameGrandMapper {

    /** 根据比赛id查询所有队员 */
    @Select("select *,IFNULL((a.ddd-a.eee),0) as teamNet from (select gg.grand_id as grandId,gg.game_id as gameId,gg.user_id as userId,gg.team_id as teamId, gg.type,u.age,u.weight,u.stature,\n" +
            "IFNULL((select result from pw_game_grand where game_id=gg.game_id and user_id=gg.user_id and type=1 GROUP BY gg.user_id),0) as playerGoals,#进球\n" +
            "(select IFNULL(sum(result),0) from pw_game_grand where type=1 and team_id=gg.team_id) as teamGoals,\n" +
            "IFNULL((select result from pw_game_grand where game_id=gg.game_id and user_id=gg.user_id and type=6 GROUP BY gg.user_id),0) as playerAssists,#助攻\n" +
            "(select IFNULL(sum(result),0) from pw_game_grand where type=6 and team_id=gg.team_id) as teamAssists,\n" +
            "IFNULL((select result from pw_game_grand where game_id=gg.game_id and user_id=gg.user_id and type=7 GROUP BY gg.user_id),0) as playerShots,#射门次数\n" +
            "(select IFNULL(sum(result),0) from pw_game_grand where type=7 and team_id=gg.team_id) as teamShots,\n" +
            "IFNULL((select result from pw_game_grand where game_id=gg.game_id and user_id=gg.user_id and type=8 GROUP BY gg.user_id),0) as playerShooting,#射正次数\n" +
            "(select IFNULL(sum(result),0) from pw_game_grand where type=8 and team_id=gg.team_id) as teamShooting,\n" +
            "IFNULL((select result from pw_game_grand where game_id=gg.game_id and user_id=gg.user_id and type=13 GROUP BY gg.user_id),0) as playerSteals,#抢断次数\n" +
            "(select IFNULL(sum(result),0) from pw_game_grand where type=13 and team_id=gg.team_id) as teamSteals,\n" +
            "IFNULL((select result from pw_game_grand where game_id=gg.game_id and user_id=gg.user_id and type=14 GROUP BY gg.user_id),0) as playerSiege,#解围次数\n" +
            "(select IFNULL(sum(result),0) from pw_game_grand where type=14 and team_id=gg.team_id) as teamSiege,\n" +
            "IFNULL((select result from pw_game_grand where game_id=gg.game_id and user_id=gg.user_id and type=15 GROUP BY gg.user_id),0) as playerSave,#扑救次数\n" +
            "(select IFNULL(sum(result),0) from pw_game_grand where type=15 and team_id=gg.team_id) as teamSave,\n" +
            "IFNULL((select result from pw_game_grand where game_id=gg.game_id and user_id=gg.user_id and type=11 GROUP BY gg.user_id),0) as playerFree,#任意球次数\n" +
            "(select IFNULL(sum(result),0) from pw_game_grand where type=11 and team_id=gg.team_id) as teamFree,\n" +
            "IFNULL((select result from pw_game_grand where game_id=gg.game_id and user_id=gg.user_id and type=12 GROUP BY gg.user_id),0) as playerCorner,#角球次数\n" +
            "(select IFNULL(sum(result),0) from pw_game_grand where type=12 and team_id=gg.team_id) as teamCorner,\n" +
            "IFNULL((select result from pw_game_grand where game_id=gg.game_id and user_id=gg.user_id and type=10 GROUP BY gg.user_id),0) as playerThreatpass,#威胁传球次数\n" +
            "(select IFNULL(sum(result),0) from pw_game_grand where type=10 and team_id=gg.team_id) as teamThreatpass,\n" +
            "IFNULL((select result from pw_game_grand where game_id=gg.game_id and user_id=gg.user_id and type=9 GROUP BY gg.user_id),0) as playerSuper,#过人次数\n" +
            "(select IFNULL(sum(result),0) from pw_game_grand where type=9 and team_id=gg.team_id) as teamSuper,\n" +
            "IFNULL((select result from pw_game_grand where game_id=gg.game_id and user_id=gg.user_id and type=16 GROUP BY gg.user_id),0) as playerFoul,#犯规次数\n" +
            "(select IFNULL(sum(result),0) from pw_game_grand where type=16 and team_id=gg.team_id) as teamFoul,\n" +
            "IFNULL((select result from pw_game_grand where game_id=gg.game_id and user_id=gg.user_id and type=4 GROUP BY gg.user_id),0) as playerYellow,#黄牌次数\n" +
            "(select IFNULL(sum(result),0) from pw_game_grand where type=4 and team_id=gg.team_id) as teamYellow,\n" +
            "IFNULL((select result from pw_game_grand where game_id=gg.game_id and user_id=gg.user_id and type=3 GROUP BY gg.user_id),0) as playerRed,#红牌次数\n" +
            "(select IFNULL(sum(result),0) from pw_game_grand where type=3 and team_id=gg.team_id) as teamRed,\n" +
            "(select entry_teamA from pw_tournament where game_id=#{gameId}) as entryTeamA,\n" +
            "(select entry_teamB from pw_tournament where game_id=#{gameId}) as entryTeamB,\n" +
            "(select sum(result) from pw_game_grand where game_id=#{gameId} and team_id=(select entry_teamA from pw_tournament where game_id='3h9lg7bp2q85VEScIdxQVM') and type=1) as ddd,\n" +
            "(select sum(result) from pw_game_grand where game_id=#{gameId} and team_id=(select entry_teamB from pw_tournament where game_id='3h9lg7bp2q85VEScIdxQVM') and type=1) as eee\n" +
            "\n" +
            "from pw_game_grand gg,pw_users u where gg.user_id = u.user_id\n" +
            "and gg.game_id=#{gameId} \n" +
            "GROUP BY gg.user_id) a \n")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "provinceId", column = "province_id"),
            @Result(property = "province", column = "province")
    })
    List<Map> getAllUser(@Param("gameId") String gameId);


    /**
     * 添加比赛数据
     * @param gameGrand
     */
    @Insert({
            "insert into pw_game_grand(grand_id,game_id,type,team_id,user_id,result,parent_id,extra,time) values(",
            "#{grandId},#{gameId},#{type},#{teamId},#{userId},#{result},#{parentId},#{extra},#{time})"
    })
    int insertGameGrand(GameGrand gameGrand);

    /**
     * 修改比赛数据
     * @param gameGrand
     */
    @Update("update pw_game_grand set type =#{type},team_id=#{teamId},user_id=#{userId},result=#{result},extra=#{extra},time=#{time} where grand_id=#{grandId}")
    int updateGameGrand(GameGrand gameGrand);

    /**
     * 修改比赛数据
     * @param gameGrand
     */
    @Update("update pw_game_grand set type =#{type},team_id=#{teamId},user_id=#{userId},result=#{result},extra=#{extra},time=#{time} where parent_id=#{parentId}")
    int updateGameGrand2(GameGrand gameGrand);


    /**
     * 根据id删除比赛数据
     * @param grandId
     */
    @Delete("DELETE FROM pw_game_grand WHERE grand_id = #{grandId}")
    int deleteGameGrandById(@Param("grandId") String grandId);

    /**
     * 根据父id删除比赛数据
     * @param grandId
     */
    @Delete("DELETE FROM pw_game_grand WHERE parent_id = #{grandId}")
    int deleteGameGrandById2(@Param("grandId") String grandId);


    /**
     * 根据父id删除比赛数据
     * @param gameId
     */
    @Delete("DELETE FROM pw_game_grand WHERE type=17 and game_id =  #{gameId}")
    int deleteGameGrandByGameId(@Param("gameId") String gameId);



    /** 查询总数*/
    @Select({"<script>",
            "select count(*) as count from (select gg.grand_id as grandId,gg.time,gg.user_id as userId,\n" +
                    "(select nickname from pw_users where user_id=gg.user_id) as nickname,gg.team_id as teamId,\n" +
                    "(select team_name from pw_team where team_id = gg.team_id) as teamName,gg.result,gg.type,\n" +
                    "(select type from pw_game_grand where parent_id = gg.grand_id)as guserType,\n" +
                    "(select u.nickname from pw_game_grand gard,pw_users u where gard.parent_id = gg.grand_id and gard.user_id=u.user_id)as guserName,\n" +
                    "(select user_id from pw_game_grand where parent_id = gg.grand_id)as guserId\n" +
                    "from pw_game_grand gg where gg.game_id=#{map.gameId} and gg.type!=17 and parent_id is NULL) a where 1=1  " +
            "</script> "})
    @Results({
            @Result(id = true, property = "count", column = "count")
    })
    int selectTotalPage(PageCrt page);


    /** 分页查询*/
    @Select({"<script>",
            "select gg.grand_id as grandId,gg.time,gg.user_id as userId,\n" +
                    "(select nickname from pw_users where user_id=gg.user_id) as nickname,gg.team_id as teamId,\n" +
                    "(select team_name from pw_team where team_id = gg.team_id) as teamName,gg.result,gg.type,\n" +
                    "(select type from pw_game_grand where parent_id = gg.grand_id)as guserType,\n" +
                    "(select u.nickname from pw_game_grand gard,pw_users u where gard.parent_id = gg.grand_id and gard.user_id=u.user_id)as guserName,\n" +
                    "(select user_id from pw_game_grand where parent_id = gg.grand_id)as guserId\n" +
                    "from pw_game_grand gg where gg.game_id=#{map.gameId} and gg.type!=17 and parent_id is NULL" +
            "<when test='map.colour!=null'>",
            "AND colour like '%${map.colour}%'",
            "</when>",
            "<when test='sidx!=null'>",
            "ORDER BY ${sidx} ${sord}",
            "</when> LIMIT #{index},#{rows}",
            "</script> "})
    @Results({
            @Result(id = true, property = "teamcolorId", column = "teamcolor_id"),
            @Result(property = "colour", column = "colour"),
            @Result(property = "createTime", column = "createTime")
    })
    List<Map> selectePaging(PageCrt page);


    /** 查询总数*/
    @Select({"<script>",
            "select count(*) as count from (select gg.grand_id as grandId,gg.game_id as gameId,gg.team_id as teamId,gg.type,gg.user_id as userId,gg.result,gg.extra,gg.time,(select team_name from pw_team where team_id = gg.team_id) as teamName,\n" +
                    "(select nickname from pw_users where user_id = gg.user_id) as nickname,\n" +
                    "(select pgg.user_id  from pw_game_grand pgg where pgg.parent_id = gg.grand_id) as gluserId,\n" +
                    "(select pgg.type  from pw_game_grand pgg where pgg.parent_id = gg.grand_id) as gltype,\n" +
                    "(select nickname from pw_users where user_id = (select pgg.user_id  from pw_game_grand pgg where pgg.parent_id = gg.grand_id)) as glnickname" +
                    ",(select pgg.result from pw_game_grand pgg where pgg.parent_id = gg.grand_id) as glresult \n" +
                    "from pw_game_grand gg where gg.parent_id is null and gg.game_id=#{map.gameId} and gg.type!=17 ) a where 1=1  " +
                    "</script> "})
    @Results({
            @Result(id = true, property = "count", column = "count")
    })
    int selectTotalPageLie(PageCrt page);


    /** 分页查询*/
    @Select({"<script>",
            "select gg.grand_id as grandId,gg.game_id as gameId,gg.team_id as teamId,gg.type,gg.user_id as userId,gg.result,gg.extra,gg.time,(select team_name from pw_team where team_id = gg.team_id) as teamName,\n" +
                    "(select nickname from pw_users where user_id = gg.user_id) as nickname,\n" +
                    "(select pgg.user_id  from pw_game_grand pgg where pgg.parent_id = gg.grand_id) as gluserId,\n" +
                    "(select pgg.type  from pw_game_grand pgg where pgg.parent_id = gg.grand_id) as gltype,\n" +
                    "(select nickname from pw_users where user_id = (select pgg.user_id  from pw_game_grand pgg where pgg.parent_id = gg.grand_id)) as glnickname\n" +
                    ",(select pgg.result from pw_game_grand pgg where pgg.parent_id = gg.grand_id) as glresult,\n" +
                    "(select result from pw_game_grand where team_id = gg.team_id and game_id = gg.game_id and type=17) as kongqiulv\n" +
                    "from pw_game_grand gg where gg.parent_id is null and gg.game_id=#{map.gameId} and gg.type!=17 " +
                    "<when test='map.colour!=null'>",
            "AND colour like '%${map.colour}%'",
            "</when>",
            "<when test='sidx!=null'>",
            "ORDER BY ${sidx} ${sord}",
            "</when> LIMIT #{index},#{rows}",
            "</script> "})
    @Results({
            @Result(id = true, property = "teamcolorId", column = "teamcolor_id"),
            @Result(property = "colour", column = "colour"),
            @Result(property = "createTime", column = "createTime")
    })
    List<Map> selectePagingLie(PageCrt page);





}
