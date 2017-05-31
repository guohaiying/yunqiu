package com.yunqiu.dao;

import com.yunqiu.model.Event;
import com.yunqiu.model.EventCollection;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * Created by 11366 on 2017/2/8.
 */

@Mapper
public interface EventCollectionMapper {
    /**
     * 收藏比赛
     * @param eventCollection
     */
    @Insert({
            "insert into pw_event_collection(collect_id,user_id,event_id) values",
            "(#{collect_id},#{user_id},#{event_id})"
    })
    void insert(EventCollection eventCollection);

    /**
     * 根据用户id跟比赛id查询收藏
     * @param user_id
     * @param event_id
     * @return
     */
    @Select("select * from pw_event_collection where user_id=#{user_id} and event_id=#{event_id}")
    EventCollection selectEventCollection(@Param("user_id") String user_id,@Param("event_id") String event_id);

    /**
     * 删除收藏
     * @param collect_id
     */
    @Delete("delete from pw_event_collection where collect_id=#{collect_id}")
    void deleteEventCollection(@Param("collect_id") String collect_id);

    /**
     * 获取用户收藏的所有比赛
     * @param user_id
     * @return
     */
    @Select("SELECT e.* FROM pw_event_collection AS c JOIN pw_event AS e ON e.event_id=c.event_id WHERE c.user_id = #{user_id}")
    List<Event> selectEventCollectionAll(@Param("user_id") String user_id);

    /**
     * 查询收藏列表
     * @param user_id
     * @param start_now
     * @param pageSize
     * @param status
     * @param time
     * @return
     */
    @Select({
            "SELECT e.event_id,steam.team_id AS sponsor_id,steam.team_name AS sponsor_name,steam.badge AS sponsor_badge,",
            "rteam.team_id AS rival_id,rteam.team_name AS rival_name,rteam.badge AS rival_badge,e.sponsor_score,e.rival_score,",
            "e.game_time,e.event_status FROM pw_event_collection AS c JOIN pw_event AS e ON e.event_id=c.event_id AND e.audit_status=2 ",
            "JOIN pw_team AS steam ON steam.team_id=e.sponsor_team JOIN pw_team AS rteam ON rteam.team_id=e.rival_team ",
            "WHERE c.user_id = #{user_id} LIMIT #{start_now},#{pageSize}"
    })
    List<Map<String,Object>> selectPagingEventCollection(@Param("user_id") String user_id, @Param("start_now") int start_now, @Param("pageSize") int pageSize,
                                          @Param("status") int status, @Param("time") String time);

    /**
     * 获取收藏的比赛的总数
     * @param user_id
     * @return
     */
    @Select("SELECT COUNT(collect_id) AS number FROM pw_event_collection WHERE user_id=#{user_id}")
    int selectEventCollectionTotal(@Param("user_id") String user_id);
}
