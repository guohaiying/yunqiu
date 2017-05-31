package com.yunqiu.dao;

import com.yunqiu.model.Team;
import com.yunqiu.model.UserAuths;
import com.yunqiu.model.UserCloudData;
import org.apache.ibatis.annotations.*;

import java.util.Date;

/**
 * Created by 11366 on 2017/3/10.
 */

@Mapper
public interface TestMapper {
    /**
     * 添加用户基本资料
     * @return
     */
    @Insert({
            "insert into pw_users(user_id,nickname,stature,weight,birthday,age,status,login_time) values",
            "(#{userId},#{nickname},#{stature},#{weight},#{birthday},#{age},#{status},#{loginTime})"
    })
    void insertUsers(@Param("userId") String userId, @Param("birthday") Date birthday,
                     @Param("status") int status, @Param("loginTime") Date loginTime,
                     @Param("stature") int stature,@Param("weight") Double weight,
                     @Param("age") int age,@Param("nickname") String nickname);

    /**
     * 添加云五数据
     * @param userCloudData
     */
    @Insert({
            "insert into pw_user_cloudData(cloud_id,user_id,attack_gross,attack_gains,defensive_gross,defensive_gains,",
            "physical_gross,physical_gains,technology_gross,technology_gains,aggressive_gross,aggressive_gains,power,gains) values(",
            "#{cloud_id},#{user_id},#{attack_gross},#{attack_gains},#{defensive_gross},#{defensive_gains},#{physical_gross},",
            "#{physical_gains},#{technology_gross},#{technology_gains},#{aggressive_gross},#{aggressive_gains},#{power},#{gains})"
    })
    void insert(UserCloudData userCloudData);

    /**
     * 添加账户
     * @param userAuths
     * @return
     */
    @Insert({
            "insert into pw_user_auths(auth_id,user_id,identity_type,",
            "identifier,credential,registration_time,registration_ip,login_time,standby) values(",
            "#{auth_id},#{user_id},#{identity_type},#{identifier},#{credential},#{registration_time},",
            "#{registration_ip},#{login_time},#{standby})"
    })
    void insertUserAuths(UserAuths userAuths);

    /**
     * 根据球队名称查询球队
     * @param team_name
     * @return
     */
    @Select("SELECT * FROM pw_team WHERE team_name=#{team_name}")
    Team selectTeam(@Param("team_name") String team_name);

    /**
     * 根据账户查询用户
     * @param identifier 手机号、第三方UUID
     * @param identityType 账户类型 1：手机号 2：微信 3：QQ
     * @return
     */
    @Select("select * from pw_user_auths where 1=1 And identifier=#{identifier} AND identity_type=#{identityType}")
    UserAuths selectUserAuthsByIdentifier(@Param("identifier") String identifier,@Param("identityType") int identityType);

    /**
     * 修改登录凭证（密码）
     * @param credential
     * @param userId
     * @param identityType
     */
    @Update("update pw_user_auths set credential=#{credential} where user_id=#{userId} AND identity_type=#{identityType}")
    void updateCredential(@Param("credential") String credential,@Param("userId") String userId,@Param("identityType") int identityType);
}
