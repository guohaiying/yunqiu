package com.yunqiu.dao;

import com.yunqiu.model.UserAuths;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * 用户账户数据持久化操作
 * @author 武尊
 * @time 2016-12-31
 * @version 1.0
 */

@Mapper
public interface UserAuthsMapper {
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
     * 根据id删除账户信息
     * @param authsId
     * @return
     */
    @Delete( "delete from pw_user_auths where auth_id=#{authsId}")
    void deleteUserAuthsById(@Param("authsId") String authsId);

    /**
     * 根据账户查询用户
     * @param identifier 手机号、第三方UUID
     * @param identityType 账户类型 1：手机号 2：微信 3：QQ
     * @return
     */
    @Select("select * from pw_user_auths where 1=1 And identifier=#{identifier} AND identity_type=#{identityType}")
    UserAuths selectUserAuthsByIdentifier(@Param("identifier") String identifier,@Param("identityType") int identityType);

    /**
     * 根据用户id跟账户类型查询账户
     * @param user_id
     * @param identityType
     * @return
     */
    @Select("select * from pw_user_auths where 1=1 And user_id=#{user_id} AND identity_type=#{identityType}")
    UserAuths selectUserAuthsByUserIdAndType(@Param("user_id") String user_id,@Param("identityType") int identityType);

    /**
     * 根据用户id查询所有账户
     * @param user_id
     * @return
     */
    @Select("select identifier,identity_type from pw_user_auths where 1=1 And user_id=#{user_id}")
    List<Map<String,Object>> selectUserAuthsByUserId(@Param("user_id") String user_id);

    /**
     * 修改登录凭证（密码）
     * @param credential
     * @param userId
     * @param identityType
     */
    @Update("update pw_user_auths set credential=#{credential} where user_id=#{userId} AND identity_type=#{identityType}")
    void updateCredential(@Param("credential") String credential,@Param("userId") String userId,@Param("identityType") int identityType);

    /**
     * 修改登录手机号
     * @param phone
     * @param userId
     */
    @Update("update pw_user_auths set identifier=#{phone} where user_id=#{userId} AND identity_type=1")
    void updatePhone(@Param("phone") String phone,@Param("userId") String userId);
}
