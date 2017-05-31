package com.yunqiu.dao;

import com.yunqiu.model.Users;
import org.apache.ibatis.annotations.*;

import java.util.Date;

/**
 * 用户基本资料数据持久化操作
 * @author 武尊
 * @time 2016-12-31
 * @version 1.0
 */

@Mapper
public interface UsersMapper {
    /**
     * 添加用户基本资料
     * @return
     */
    @Insert( "insert into pw_users(user_id,birthday,status,login_time) values(#{userId},#{birthday},#{status},#{loginTime})")
    void insertUsers(@Param("userId") String userId,@Param("birthday") Date birthday,@Param("status") int status,@Param("loginTime") Date loginTime);

    /**
     * 根据用户ID查询资料
     * @param userId
     * @return
     */
    @Select("select * from pw_users where 1=1 AND user_id=#{userId}")
    Users selectUsersByUserId(@Param("userId") String userId);

    /**
     * 修改用户基本资料
     * @param users
     */
    @Update({
            "update pw_users set nickname=#{nickname},portrait=#{portrait},stature=#{stature},",
            "weight=#{weight},sex=#{sex},birthday=#{birthday},age=#{age},veteran=#{veteran},foot=#{foot},",
            "position=#{position},countries=#{countries},province=#{province},city=#{city},",
            "area=#{area},label=#{label},standby=#{standby} where user_id=#{user_id}"
    })
    void updateUsersByUserId(Users users);
}
