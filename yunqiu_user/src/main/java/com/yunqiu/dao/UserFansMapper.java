package com.yunqiu.dao;

import com.yunqiu.model.UserFans;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * Created by 11366 on 2017/2/7
 */

@Mapper
public interface UserFansMapper {
    /**
     * 添加关注
     * @param userFans
     */
    @Insert({
            "insert into pw_user_fans(fans_id,user_id,focus_id) values",
            "(#{fans_id},#{user_id},#{focus})"
    })
    void insertUserFans(UserFans userFans);

    /**
     * 根据用户id跟关注的用户id查询关注
     * @param user_id
     * @param focus
     * @return
     */
    @Select("select * from pw_user_fans where user_id=#{user_id} and focus_id=#{focus}")
    UserFans selectFans(@Param("user_id") String user_id,@Param("focus") String focus);

    /**
     * 删除关注
     * @param fans_id
     */
    @Delete("delete from pw_user_fans where fans_id=#{fans_id}")
    void deleteFans(@Param("fans_id") String fans_id);

    /**
     * 分页查询粉丝列表
     * @param user_id
     * @param start_now
     * @param pageSize
     * @return
     */
    @Select({
            "SELECT u.user_id,u.nickname,u.portrait,u.position,u.province,u.city,u.area FROM pw_user_fans AS f JOIN ",
            "pw_users AS u ON u.user_id=f.user_id WHERE f.focus_id=#{user_id} LIMIT #{start_now},#{pageSize}"
    })
    List<Map<String,Object>> selectPagingFans(@Param("user_id") String user_id,@Param("start_now") int start_now,@Param("pageSize") int pageSize);

    /**
     * 获取粉丝总数
     * @param user_id
     * @return
     */
    @Select("SELECT COUNT(fans_id) AS number FROM pw_user_fans WHERE focus_id=#{user_id}")
    int selectFansTotal(@Param("user_id") String user_id);

    /**
     * 分页查询关注球员列表
     * @param user_id
     * @param start_now
     * @param pageSize
     * @return
     */
    @Select({
            "SELECT u.user_id,u.nickname,u.portrait,u.position,u.province,u.city,u.area FROM pw_user_fans AS f JOIN ",
            "pw_users AS u ON u.user_id=f.focus_id WHERE f.user_id=#{user_id} LIMIT #{start_now},#{pageSize}"
    })
    List<Map<String,Object>> selectPagingFocus(@Param("user_id") String user_id,@Param("start_now") int start_now,@Param("pageSize") int pageSize);

    /**
     * 获取关注球员总数
     * @param user_id
     * @return
     */
    @Select("SELECT COUNT(fans_id) AS number FROM pw_user_fans WHERE user_id=#{user_id}")
    int selectFocusTotal(@Param("user_id") String user_id);
}
