package com.yunqiu.dao;


import com.yunqiu.model.PageCrt;
import com.yunqiu.model.TeamMember;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface TeamMemberMapper {

    /**
     * 添加球队成员
     * @param teamMember
     */
    @Insert({
            "insert into pw_team_member(member_id,user_id,team_id,identity,jurisdiction,enqueue_time,jersey_no,place,status) values(",
            "#{memberId},#{userId},#{teamId},#{identity},#{jurisdiction},#{enqueueTime},#{jerseyNo},#{place},#{status})"
    })
    int insertTeamMeaMember(TeamMember teamMember);


    /**
     * 修改球队成员信息
     * @param teamMember
     */
    @Insert({
            "update pw_team_member set identity =#{identity},jurisdiction=#{jurisdiction},jersey_no=#{jerseyNo},place=#{place} where member_id=#{memberId}\n"
    })
    int updateTeamMember(TeamMember teamMember);

    /**
     * 修改队员状态
     * @param teamMember
     */
    @Update({
            "update pw_team_member set status=#{status} where member_id=#{memberId}\n"
    })
    int updateTeamMemberStatus(TeamMember teamMember);




    /** 查询总数球队*/
    @Select({"<script>",
            "select count(*) as count from (select pt.team_id as teamId,pt.team_name as teamName,pt.badge,pt.team_type as teamType,pt.background,pt.establish_time as establishTime,\n" +
                    "pt.fans_number as fansNumber,pt.invite,pt.home,pt.enter_time as enterTime,pt.label,pt.status,pt.province,pt.area,pt.city,\n" +
                    "(select count(*) from pw_team_member where `status`=1 and team_id=pt.team_id)  as tpcount,\n" +
                    "(select nickname from pw_users where user_id =(select user_id from pw_team_member where team_id=pt.team_id and status=1 and jurisdiction=1)) as userName\n" +
                    " from pw_team_member ptm,pw_team pt where ptm.team_id=pt.team_id and ptm.user_id=#{map.userId} ) a where 1=1" +
            "</script> "})
    @Results({
            @Result(id = true, property = "count", column = "count")
    })
    int selectTotalPage(PageCrt page);


    /** 分页查询球队 */
    @Select({"<script>",
            "select pt.team_id as teamId,pt.team_name as teamName,pt.badge,pt.team_type as teamType,pt.background,pt.establish_time as establishTime,\n" +
                    "pt.fans_number as fansNumber,pt.invite,pt.home,pt.enter_time as enterTime,pt.label,pt.status,pt.province,pt.area,pt.city,ptm.enqueue_time as enqueueTime,\n" +
                    "(select count(*) from pw_team_member where `status`=1 and team_id=pt.team_id)  as tpcount,\n" +
                    "(select nickname from pw_users where user_id =(select user_id from pw_team_member where team_id=pt.team_id and status=1 and jurisdiction=1)) as userName\n" +
                    " from pw_team_member ptm,pw_team pt where ptm.team_id=pt.team_id and ptm.user_id=#{map.userId} " +
            "<when test='sidx!=null'>",
            "ORDER BY ${sidx} ${sord}",
            "</when> LIMIT #{index},#{rows}",
            "</script> "})
    @Results({
            @Result(id = true, property = "teamId", column = "team_id"),
            @Result(property = "teamName", column = "team_name"),
            @Result(property = "badge", column = "badge"),
            @Result(property = "teamType", column = "team_type"),
            @Result(property = "background", column = "background"),
            @Result(property = "establishTime", column = "establish_time"),
            @Result(property = "fansNumber", column = "fans_number"),
            @Result(property = "invite", column = "invite"),
            @Result(property = "province", column = "province"),
            @Result(property = "city", column = "city"),
            @Result(property = "area", column = "area"),
            @Result(property = "home", column = "home"),
            @Result(property = "enterTime", column = "enter_time"),
            @Result(property = "label", column = "label"),
            @Result(property = "status", column = "status")
    })
    List<Map> selectePaging(PageCrt page);


    /** 查询总数球员*/
    @Select({"<script>",
            "select count(*) as count from (select tm.member_id as memberId,tm.user_id as userId,tm.team_id as teamId,tm.identity,tm.jurisdiction,tm.enqueue_time as enqueueTime,\n" +
                    "tm.jersey_no as jerseyNo,tm.place,tm.`status`,(select nickname from pw_users where user_id=tm.user_id) as nickname,0 as ustatus\n" +
                    "from pw_team_member tm where tm.team_id =#{map.teamId} \n" +
                    "UNION all \n" +
                    "select jt.join_id as memberId,jt.user_id as userId,jt.team_id as teamId,5 as identity,0 as jurisdiction,jt.apply_time as enqueueTime,\n" +
                    "0 as jerseyNo,0 asplace,0 as`status`,(select nickname from pw_users where user_id=jt.user_id) as nickname,jt.`status` as ustatus\n" +
                    "from pw_join_team jt where jt.team_id=#{map.teamId} and jt.status=1 ) a where 1=1" +
                    "</script> "})
    @Results({
            @Result(id = true, property = "count", column = "count")
    })
    int selectTotalPageUser(PageCrt page);


    /** 分页查询球员 */
    @Select({"<script>",
            "select * from (\n" +
                    "select tm.member_id as memberId,tm.user_id as userId,tm.team_id as teamId,tm.identity,tm.jurisdiction,tm.enqueue_time as enqueueTime,\n" +
                    "tm.jersey_no as jerseyNo,tm.place,tm.`status`,(select nickname from pw_users where user_id=tm.user_id) as nickname,0 as ustatus\n" +
                    "from pw_team_member tm where tm.team_id =#{map.teamId} \n" +
                    "UNION all \n" +
                    "select jt.join_id as memberId,jt.user_id as userId,jt.team_id as teamId,5 as identity,0 as jurisdiction,jt.apply_time as enqueueTime,\n" +
                    "0 as jerseyNo,0 asplace,0 as`status`,(select nickname from pw_users where user_id=jt.user_id) as nickname,jt.`status` as ustatus\n" +
                    "from pw_join_team jt where jt.team_id=#{map.teamId} and jt.status=1\n) a " +
            "<when test='sidx!=null'>",
            "ORDER BY ${sidx} ${sord}",
            "</when> LIMIT #{index},#{rows}",
            "</script> "})
    @Results({
            @Result(id = true, property = "teamId", column = "team_id"),
            @Result(property = "teamName", column = "team_name"),
            @Result(property = "badge", column = "badge"),
            @Result(property = "teamType", column = "team_type"),
            @Result(property = "background", column = "background"),
            @Result(property = "establishTime", column = "establish_time"),
            @Result(property = "fansNumber", column = "fans_number"),
            @Result(property = "invite", column = "invite"),
            @Result(property = "province", column = "province"),
            @Result(property = "city", column = "city"),
            @Result(property = "area", column = "area"),
            @Result(property = "home", column = "home"),
            @Result(property = "enterTime", column = "enter_time"),
            @Result(property = "label", column = "label"),
            @Result(property = "status", column = "status")
    })
    List<Map> selectePagingUser(PageCrt page);



    /**
     * 根据id查询球队名称
     * @param teamId
     * @return
     */
    @Select("select team_name as teamName from pw_team where 1=1 AND team_id=#{teamId}")
    @Results({
            @Result(id = true, property = "teamName", column = "teamName")
    })
    String getTeamName(@Param("teamId") String teamId);

    /**
     * 根据id查询用户名称
     * @param userId
     * @return
     */
    @Select("select nickname from pw_users where 1=1 AND user_id=#{userId}")
    @Results({
            @Result(id = true, property = "nickname", column = "nickname")
    })
    String getUserName(@Param("userId") String userId);




}
