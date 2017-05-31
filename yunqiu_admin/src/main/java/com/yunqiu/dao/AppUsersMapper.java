package com.yunqiu.dao;


import com.yunqiu.model.AdminUser;
import com.yunqiu.model.AppUser;
import com.yunqiu.model.PageCrt;
import com.yunqiu.model.UserAuths;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface AppUsersMapper {

    /**
     * 添加用户基本资料
     * @param appUser
     */
    @Insert({
            "insert into pw_users(user_id,nickname,standby,birthday,age) values(",
            "#{userId},#{nickname},#{standby},#{birthday},#{age})"
    })
    int insertUsers(AppUser appUser);


    /** 查询是否注册过该账户总数*/
    @Select({"select count(*) as count from pw_user_auths where identifier=#{identifier}"})
    @Results({
            @Result(id = true, property = "count", column = "count")
    })
    int selectByPhone(@Param("identifier") String identifier);

    /**
     * 添加用户账户
     * @param userAuth
     */
    @Insert({
            "insert into pw_user_auths(auth_id,user_id,identity_type,identifier,registration_time,registration_ip,credential) values(",
            "#{authId},#{userId},#{identityType},#{identifier},#{registrationTime},#{registrationIp},#{credential})"
    })
    int insertUsersAuth(UserAuths userAuth);

    /**
     * 修改资料
     * @param user
     */
    @Update("update pw_users set nickname=#{nickname},portrait=#{portrait},stature=#{stature},weight=#{weight},birthday=#{birthday},\n" +
            "sex=#{sex},age=#{age},foot=#{foot},position=#{position},countries=#{countries},province=(select province from sy_province where province_id=#{province}),city=(select city from sy_city where city_id=#{city}),area=(select area from sy_area where area_id=#{area}),label=#{label}\n" +
            " where user_id=#{userId}")
    int updateUser(AppUser user);

    /**
     * 修改账户状态
     * @param status
     * @param adminId
     */
    @Update("update aa_user set status=#{status} where admin_id=#{adminId}")
    int updateUserByStatus(@Param("status") int status, @Param("adminId") String adminId);

    /**
     * 修改账户密码
     * @param credential
     * @param userId
     */
    @Update("update pw_user_auths set credential=#{credential},identifier=#{identifier} where user_id=#{userId}")
    int upPassword(@Param("credential") String credential,@Param("userId") String userId,@Param("identifier") String identifier);

    /**
     * 根据id删除管理员账户
     * @param adminId
     */
    @Delete("DELETE FROM aa_user WHERE admin_id = #{adminId}")
    int deleteUserById(@Param("adminId") String adminId);

    /**
     * 根据id查询管理员
     * @param adminId
     * @return
     */
    @Select("select * from aa_user where 1=1 AND admin_id=#{adminId}")
    @Results({
            @Result(id = true, property = "adminId", column = "admin_id"),
            @Result(property = "userName", column = "user_name"),
            @Result(property = "password", column = "password"),
            @Result(property = "compellation", column = "compellation"),
            @Result(property = "phone", column = "phone"),
            @Result(property = "email", column = "email"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "status", column = "status"),
            @Result(property = "remark", column = "remark")
    })
    AdminUser selectUserById(@Param("adminId") String adminId);

    /**
     * 查询所有管理员
     * @return
     */
    @Select("select * from aa_user")
    @Results({
            @Result(id = true, property = "adminId", column = "admin_id"),
            @Result(property = "userName", column = "user_name"),
            @Result(property = "password", column = "password"),
            @Result(property = "compellation", column = "compellation"),
            @Result(property = "phone", column = "phone"),
            @Result(property = "email", column = "email"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "status", column = "status"),
            @Result(property = "remark", column = "remark")
    })
    List<AdminUser> selectUserAll();



    /** 查询总数*/
    @Select({"<script>",
            "select count(*) as count from (select pua.auth_id as authId,pua.user_id as userId,pua.identifier,pu.nickname,pu.sex,pu.portrait,pu.age,IFNULL(pu.province,'') as province,IFNULL(pu.city,'') as city,IFNULL(pu.area,'') as area,pu.`status`\n" +
                    ",pu.birthday,pu.standby from pw_user_auths pua,pw_users pu where pua.user_id=pu.user_id GROUP BY pua.user_id) a where 1=1 " +
             "<when test='map.identifier!=null'>",
            "AND a.identifier like '%${map.identifier}%'",
            "</when>",
            "<when test='map.nickname!=null'>",
            "AND a.nickname like '%${map.nickname}%'",
            "</when>",
            "<when test='map.sex!=null and map.sex!=0'>",
            "AND a.sex = '${map.sex}'",
            "</when>",
            "<when test='map.status!=null and map.status!=0'>",
            "AND a.status = '${map.status}'",
            "</when>",
            "</script> "})
    @Results({
            @Result(id = true, property = "count", column = "count")
    })
    int selectTotalPage(PageCrt page);


    /** 分页查询*/
    @Select({"<script>",
            "select pua.auth_id as authId,pua.user_id as userId,pua.identifier,pu.nickname,pu.sex,pu.portrait,pu.age,IFNULL(pu.province,'') as province,IFNULL(pu.city,'') as city,IFNULL(pu.area,'') as area,pu.`status`\n" +
                    ",pu.birthday,pu.standby,pu.stature,pu.weight,pu.province,pu.city,pu.area,pu.label,pu.foot,pu.position,pu.label from pw_user_auths pua,pw_users pu where pua.user_id=pu.user_id  " +
             "<when test='map.identifier!=null'>",
            "AND pua.identifier like '%${map.identifier}%'",
            "</when>",
            "<when test='map.nickname!=null'>",
            "AND pu.nickname like '%${map.nickname}%'",
            "</when>",
            "<when test='map.sex!=null and map.sex!=0'>",
            "AND pu.sex = '${map.sex}'",
            "</when>",
            "<when test='map.status!=null and map.status!=0'>",
            "AND pu.status = '${map.status}'",
            "</when>",
            " GROUP BY pua.user_id ",
             "<when test='sidx!=null'>",
            "ORDER BY ${sidx} ${sord}",
            "</when>  LIMIT #{index},#{rows}",
            "</script> "})
    @Results({
            @Result(id = true, property = "userId", column = "userId"),
            @Result(property = "identifier", column = "identifier"),
            @Result(property = "nickname", column = "nickname"),
            @Result(property = "sex", column = "sex"),
            @Result(property = "portrait", column = "portrait"),
            @Result(property = "age", column = "age"),
            @Result(property = "pca", column = "pca"),
            @Result(property = "status", column = "status")
    })
    List<Map> selectePaging(PageCrt page);


    /** 查询所有用户*/
    @Select("select pua.auth_id as authId,pua.user_id as userId,pua.identifier,pu.nickname,pu.sex,pu.portrait,pu.age,IFNULL(pu.province,'') as province,IFNULL(pu.city,'') as city,IFNULL(pu.area,'') as area,pu.`status`\n" +
            ",pu.birthday,pu.standby,pu.stature,pu.weight,pu.province,pu.city,pu.area,pu.label,pu.foot,pu.position,pu.label from pw_user_auths pua,pw_users pu where pua.user_id=pu.user_id")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "provinceId", column = "province_id"),
    })
    List<AppUser> getAllUser();

}
