package com.yunqiu.dao;


import com.yunqiu.model.AdminUser;
import com.yunqiu.model.PageCrt;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * 后台管理员账户数据库操作
 * @author 武尊
 * @time 2017-01-11
 * @version 1.0
 */

@Mapper
public interface UsersMapper {

    /**
     * 添加管理员用户
     * @param adminUser
     */
    @Insert({
            "insert into aa_user(admin_id,user_name,password,phone,email,createTime,status,remark) values(",
            "#{adminId},#{userName},#{password},#{phone},#{email},#{createTime},#{status},#{remark})"
    })
    int insertUsers(AdminUser adminUser);

    /**
     * 修改管理员资料
     * @param adminUser
     */
    @Update("update aa_user set user_name=#{userName},phone=#{phone},email=#{email},remark=#{remark} where admin_id=#{adminId}")
    int updateUser(AdminUser adminUser);

    /**
     * 修改登录密码
     * @param password
     * @param adminId
     */
    @Update("update aa_user set password=#{password} where admin_id=#{adminId}")
    int updateUserByPassword(@Param("password") String password,@Param("adminId") String adminId);

    /**
     * 修改账户状态
     * @param status
     * @param adminId
     */
    @Update("update aa_user set status=#{status} where admin_id=#{adminId}")
    int updateUserByStatus(@Param("status") int status,@Param("adminId") String adminId);

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
     * 根据账户查询用户
     * @param userName
     * @return
     */
    @Select("select * from aa_user where 1=1 AND user_name=#{userName}")
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
    AdminUser selectUserByUserName(@Param("userName") String userName);

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
            "select count(*) as count from (select au.*,(select GROUP_CONCAT(aur.role_id)  from aa_user_role aur where aur.admin_id =au.admin_id) as roleId\n" +
                    ",(select GROUP_CONCAT((select ar.role_name from aa_role ar where ar.role_id=aur.role_id))  from aa_user_role aur where aur.admin_id =au.admin_id) as roleName\n" +
                    "from aa_user au) a where 1=1 " +
                    "<when test='map.userName!=null'>",
            "AND a.user_name like '%${map.userName}%'",
            "</when>",
            "<when test='map.phone!=null'>",
            "AND a.phone like '%${map.phone}%'",
            "</when>",
            "<when test='map.status!=null and map.status!=0'>",
            "AND a.status = #{map.status}",
            "</when>",
            "<when test='map.role!=null'>",
            "AND a.roleId like '%${map.role}%'",
            "</when>",
            "</script> "})
    @Results({
            @Result(id = true, property = "count", column = "count")
    })
    int selectTotalPage(PageCrt page);


    /** 分页查询*/
    @Select({"<script>",
            "select * from (select au.admin_id as adminId,au.user_name as userName,au.password,au.phone,au.email,au.createTime,status,remark,(select GROUP_CONCAT(aur.role_id)  from aa_user_role aur where aur.admin_id =au.admin_id) as roleId\n" +
                    ",(select GROUP_CONCAT((select ar.role_name from aa_role ar where ar.role_id=aur.role_id))  from aa_user_role aur where aur.admin_id =au.admin_id) as roleName\n" +
                    "from aa_user au) a where 1=1 " +
            "<when test='map.userName!=null'>",
            "AND a.user_name like '%${map.userName}%'",
            "</when>",
            "<when test='map.phone!=null'>",
            "AND a.phone like '%${map.phone}%'",
            "</when>",
            "<when test='map.status!=null and map.status!=0'>",
            "AND a.status = #{map.status}",
            "</when>",
            "<when test='map.role!=null'>",
            "AND a.roleId like '%${map.role}%'",
            "</when>" +
             "<when test='sidx!=null'>",
            "ORDER BY ${sidx} ${sord}",
            "</when> LIMIT #{index},#{rows}",
            "</script> "})
    @Results({
            @Result(id = true, property = "adminId", column = "admin_id"),
            @Result(property = "userName", column = "user_name"),
            @Result(property = "password", column = "password"),
            @Result(property = "phone", column = "phone"),
            @Result(property = "email", column = "email"),
            @Result(property = "createTime", column = "createTime"),
            @Result(property = "status", column = "status"),
            @Result(property = "remark", column = "remark")
    })
    List<Map> selectePaging(PageCrt page);

}
