package com.yunqiu.dao;


import com.yunqiu.model.GameMember;
import com.yunqiu.model.PageCrt;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface GameMemberMapper {

    /**
     * 修改状态
     * @param gameMember
     */
    @Update("update pw_game_member set status=#{status} where user_id=#{userId} and team_id=#{teamId} and game_id=#{gameId}")
    int updateStatus(GameMember gameMember);

    /**
     * 添加参赛成员
     * @param gameMember
     */
    @Insert({
            "insert into pw_game_member(match_id,user_id,team_id,game_id,status) values(",
            "#{matchId},#{userId},#{teamId},#{gameId},#{status})"
    })
    int insertGameMember(GameMember gameMember);


    /**
     * 根据id查询
     * @param gameMember
     * @return
     */
    @Select("select * from pw_game_member where 1=1 AND user_id=#{userId} and team_id=#{teamId} and game_id=#{gameId}")
    @Results({
            @Result(id = true, property = "menuId", column = "menu_id")
    })
    GameMember findIdGameMember(GameMember gameMember);


    /** 查询总数*/
    @Select({"<script>",
            "select count(*) as count from (select tm.member_id as memberId,(select team.team_name from pw_team team where team.team_id=tm.team_id) as teamName, u.nickname,u.portrait,tm.user_id as userId,tm.team_id as teamId,\n" +
                    "(select team.team_name from pw_team team where team.team_id=(select tour.entry_teamA from pw_tournament tour where game_id=#{map.gameId})) as entryTeamA,\n" +
                    "(select team.team_name from pw_team team where team.team_id=(select tour.entry_teamB from pw_tournament tour where game_id=#{map.gameId})) as entryTeamB,\n" +
                    "IFNULL((select status from pw_game_member where user_id=tm.user_id and team_id=tm.team_id and game_id=#{map.gameId}),2) as status\n" +
                    "from pw_team_member tm,pw_users u where tm.user_id=u.user_id and tm.`status`=1 and tm.team_id in\n" +
                    "((select t.entry_teamA from pw_tournament t where t.game_id=#{map.gameId}),\n" +
                    "((select t.entry_teamB from pw_tournament t where t.game_id=#{map.gameId})))) a where 1=1 " +
             "<when test='map.tagsName!=null'>",
            "AND tags_name like '%${map.tagsName}%'",
            "</when>",
            "</script> "})
    @Results({
            @Result(id = true, property = "count", column = "count")
    })
    int selectTotalPage(PageCrt page);


    /** 分页查询*/
    @Select({"<script>",
            "select tm.member_id as memberId,(select team.team_name from pw_team team where team.team_id=tm.team_id) as teamName, u.nickname,u.portrait,tm.user_id as userId,tm.team_id as teamId,\n" +
                    "(select team.team_name from pw_team team where team.team_id=(select tour.entry_teamA from pw_tournament tour where game_id=#{map.gameId})) as entryTeamA,\n" +
                    "(select team.team_name from pw_team team where team.team_id=(select tour.entry_teamB from pw_tournament tour where game_id=#{map.gameId})) as entryTeamB,\n" +
                    "IFNULL((select status from pw_game_member where user_id=tm.user_id and team_id=tm.team_id and game_id=#{map.gameId}),2) as status\n" +
                    "from pw_team_member tm,pw_users u where tm.user_id=u.user_id and tm.`status`=1 and tm.team_id in \n" +
                    "((select t.entry_teamA from pw_tournament t where t.game_id=#{map.gameId}),\n" +
                    "((select t.entry_teamB from pw_tournament t where t.game_id=#{map.gameId})))\n " +
             "<when test='sidx!=null'>",
            "ORDER BY ${sidx} ${sord}",
            "</when> LIMIT #{index},#{rows}",
            "</script> "})
    @Results({
            @Result(id = true, property = "playertagsId", column = "playertags_id"),
            @Result(property = "tagsName", column = "tags_name"),
            @Result(property = "createTime", column = "createTime")
    })
    List<Map> selectePaging(PageCrt page);


    //获取用户参与的比赛场数
    @Select({"select count(*) as count from pw_game_member where user_id=#{userId} and status in (1,3)\n"})
    @Results({
            @Result(id = true, property = "count", column = "count")
    })
    int getUserGameCount(@Param("userId") String userId);

}
