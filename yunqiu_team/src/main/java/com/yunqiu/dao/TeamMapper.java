package com.yunqiu.dao;

import com.yunqiu.dao.sqlprovider.TeamSqlProvider;
import com.yunqiu.model.Team;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * 球队信息持久化
 * @author 武尊
 * @time 2017-01-16
 * @version 1.0
 */

@Mapper
public interface TeamMapper {
    /**
     * 添加球队信息
     * @param team
     */
    @Insert({
            "insert into pw_team(team_id,team_name,badge,team_type,background,establish_time,fans_number,invite,province,",
            "city,area,home,enter_time,label,status) values(#{team_id},#{team_name},#{badge},#{team_type},#{background},",
            "#{establish_time},#{fans_number},#{invite},#{province},#{city},#{area},#{home},#{enter_time},#{label},#{status})"
    })
    void insertTeam(Team team);

    /**
     * 根据球队ID删除球队信息
     * @param teamId
     */
    @Delete("delete from pw_team where team_id = #{teamId}")
    void deleteTeam(@Param("teamId") String teamId);

    /**
     * 根据ID修改球队状态
     * @param teamId 1：正常 2：解散
     */
    @Update("update pw_team set status= #{status} where team_id = #{teamId}")
    void updateTeamStatusById(@Param("teamId") String teamId,@Param("status") int status);

    /**
     * 修改球队基础信息
     * @param team
     */
    @Update({
            "update pw_team set team_name=#{team_name},badge=#{badge},team_type=#{team_type},background=#{background},",
            "establish_time=#{establish_time},province=#{province},city=#{city},area=#{area},home=#{home},label=#{label}",
            " where team_id=#{team_id}"
    })
    void updateTeamInfo(Team team);
    /**
     * 根据id查询球队基本信息
     * @param teamId
     * @return
     */
    @Select("select * from pw_team where 1=1 and team_id = #{teamId}")
    Team selectTeamInfo(@Param("teamId") String teamId);

    /**
     * 修改球队邀请码
     * @param teamId
     * @param invite
     */
    @Update("update pw_team set invite=#{invite} where team_id=#{teamId}")
    void updateTeamInvite(@Param("teamId") String teamId,@Param("invite") String invite);

    /**
     * 查询球队列表
     * @param userId
     * @return
     */
    @Select("SELECT team.team_id,team.team_name,team.badge,team.province,team.city,team.area,(SELECT COUNT(m.member_id) FROM pw_team_member AS m WHERE m.team_id=team.team_id) AS number FROM pw_team_member AS member JOIN pw_team AS team ON team.team_id=member.team_id AND team.`status`=1 WHERE member.user_id=#{userId} AND member.`status`=1")
    List<Map<String,Object>> selectTeamList(@Param("userId") String userId);

    /**
     * 查询所有球队列表（除了指定球队id得以外）
     * @return
     */
    @SelectProvider(type = TeamSqlProvider.class,method = "selectTeamAllList")
    List<Map<String,Object>> selectTeamAllList(Map<String,Object> params);

    /**
     * 查询可加入的球队
     * @param user_id
     * @return
     */
    @Select({
            "SELECT t.team_id,t.team_name,t.badge,t.province,t.city,t.area,(SELECT COUNT(m.member_id) FROM pw_team_member AS m WHERE m.team_id=t.team_id) AS number ",
            "FROM pw_team t WHERE NOT EXISTS(SELECT * FROM pw_team_member m WHERE m.user_id =#{user_id} AND m.`status`=1 AND m.team_id = t.team_id) AND t.`status`=1"
    })
    List<Map<String,Object>> selectJoinTeam(@Param("user_id") String user_id);

    /**
     * 查询球队战绩
     * @param start_now
     * @param pageSize
     * @return
     */
    @Select({
            "SELECT e.game_id,steam.team_id AS sponsor_id,steam.team_name AS sponsor_name,steam.badge AS sponsor_badge,",
            "rteam.team_id AS rival_id,rteam.team_name AS rival_name,rteam.badge AS rival_badge,e.score_teamA,e.score_teamB,",
            "e.game_time,e.game_status FROM pw_tournament AS e JOIN pw_team AS steam ON steam.team_id=e.entry_teamA JOIN pw_team AS rteam",
            " ON rteam.team_id=e.entry_teamB WHERE e.game_status = 6 AND (e.entry_teamA=#{team_id} OR e.entry_teamB=#{team_id}) ",
            "ORDER BY e.game_time ASC LIMIT #{start_now},#{pageSize}"
    })
    List<Map<String,Object>> teamRecord(@Param("team_id") String team_id,@Param("start_now") int start_now, @Param("pageSize") int pageSize);

    /**
     * 获取球队已结束的比赛总数
     * @param team_id
     * @return
     */
    @Select({
            "SELECT COUNT(game_id) AS number FROM pw_tournament WHERE game_status=6 AND (entry_teamA=#{team_id} OR entry_teamB=#{team_id})"
    })
    int teamRecordTotal(@Param("team_id") String team_id);

    /**
     * 获取拥有管理权限的球队
     * @param user_id
     * @return
     */
    @Select({
            "SELECT t.team_id,t.badge,t.team_name,(SELECT COUNT(c.member_id) FROM pw_team_member AS c WHERE c.team_id=t.team_id) AS number ",
            "FROM pw_team_member AS m JOIN pw_team AS t ON t.team_id = m.team_id WHERE m.user_id=#{user_id} AND m.jurisdiction <> 0"
    })
    List<Map<String,Object>> selectManagementTeam(@Param("user_id") String user_id);

    /**
     * 查询球队平均年龄
     * @param team_id
     * @return
     */
    @Select({
            "SELECT ROUND(AVG(u.age),2) AS age FROM pw_team_member AS tm JOIN pw_users AS u ON u.user_id=tm.user_id WHERE tm.team_id=#{team_id}"
    })
    double selectTeamAvgAge(@Param("team_id") String team_id);

    /**
     * 搜索球队
     * @param team_name
     * @param start_now
     * @param pageSize
     * @return
     */
    @Select({
            "SELECT team.team_id,team.team_name,team.badge,team.province,team.city,team.area,(SELECT COUNT(member.member_id) ",
            "FROM pw_team_member AS member WHERE member.team_id=team.team_id) AS total_member FROM pw_team AS team WHERE ",
            "team.team_name LIKE #{team_name} LIMIT #{start_now},#{pageSize}"
    })
    List<Map<String,Object>> searchTeam(@Param("team_name") String team_name,@Param("start_now") int start_now, @Param("pageSize") int pageSize);

    @Select({
            "SELECT COUNT(team.team_id) AS number FROM pw_team AS team WHERE team.team_name LIKE #{team_name}"
    })
    int searchTeamTotal(@Param("team_name") String team_name);

    /**
     * 搜索球员用户
     * @param nickname
     * @param start_now
     * @param pageSize
     * @return
     */
    @Select({
            "SELECT users.user_id,users.nickname,users.portrait,users.position,users.province,users.city,users.area,users.standby ",
            "FROM pw_users AS users WHERE users.nickname LIKE #{nickname} LIMIT #{start_now},#{pageSize}"
    })
    List<Map<String,Object>> searchUser(@Param("nickname") String nickname,@Param("start_now") int start_now, @Param("pageSize") int pageSize);

    @Select("SELECT COUNT(users.user_id) AS number FROM pw_users AS users WHERE users.nickname LIKE #{nickname}")
    int searchUserTotal(@Param("nickname") String nickname);

    /**
     * 搜索赛事
     * @param game_name
     * @param start_now
     * @param pageSize
     * @return
     */
    @Select({
            "SELECT league.league_id,league.league_name,league.league_icon,league.start_time,league.end_time,league.game_system,league.`status`,",
            "league.province,league.city,league.area,league.league_site FROM pw_league AS league WHERE league.league_name LIKE #{game_name} LIMIT #{start_now},#{pageSize}"
    })
    List<Map<String,Object>> searchGame(@Param("game_name") String game_name,@Param("start_now") int start_now, @Param("pageSize") int pageSize);

    @Select("SELECT COUNT(league.league_id) AS number FROM pw_league AS league WHERE league.league_name LIKE #{game_name}")
    int searchGameTotal(@Param("game_name") String game_name);

    /**
     * 获取用户的排行榜（前100名）
     * @return
     */
    @Select({
            "SELECT users.user_id,users.nickname,users.portrait,clouddate.power FROM pw_user_clouddata AS clouddate JOIN pw_users AS users ",
            "ON users.user_id=clouddate.user_id ORDER BY clouddate.power DESC LIMIT 100"
    })
    List<Map<String,Object>> getBillboardByUser();

    /**
     * 获取球队的排行榜（前100名）
     * @return
     */
    @Select({
            "SELECT team.team_id,team.team_name,team.badge,clouddate.mean_power FROM pw_team_clouddata AS clouddate JOIN ",
            "pw_team AS team ON team.team_id=clouddate.team_id ORDER BY clouddate.mean_power DESC LIMIT 100"
    })
    List<Map<String,Object>> getBillboardByTeam();

    /**
     * 获取球队荣誉
     * @param team_id
     * @return
     */
    /*@Select({
            "SELECT team_honor.team_id,league.league_icon,league_honor.league_id,league_honor.img_url,league_honor.honor_name FROM ",
            "pw_team_honor AS team_honor JOIN pw_league_honor AS league_honor ON league_honor.honor_id=team_honor.honor_id JOIN ",
            "pw_league AS league ON league.league_id=league_honor.league_id WHERE team_honor.team_id=#{team_id} ORDER BY team_honor.gain_time DESC"
    })*/
    @Select({
            "SELECT team_honor.team_id,league_honor.league_id,league_honor.img_url,league_honor.honor_name FROM ",
            "pw_team_honor AS team_honor JOIN pw_league_honor AS league_honor ON league_honor.honor_id=team_honor.honor_id ",
            "WHERE team_honor.team_id=#{team_id} ORDER BY team_honor.gain_time DESC"
    })
    List<Map<String,Object>> getTeamHonor(@Param("team_id") String team_id);

    @Select({
            "SELECT team_honor.team_id,league_honor.league_id,league_honor.img_url,league_honor.honor_name FROM ",
            "pw_team_honor AS team_honor JOIN pw_league_honor AS league_honor ON league_honor.honor_id=team_honor.honor_id ",
            "WHERE team_honor.team_id=#{team_id} ORDER BY team_honor.gain_time DESC LIMIT 1"
    })
    Map<String,Object> getTeamHonorOne(@Param("team_id") String team_id);


}
