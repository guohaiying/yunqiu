package com.yunqiu.dao;


import com.yunqiu.model.Power;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface PowerMapper {

    /** 查询所有*/
    @Select("select menu_id from aa_power where role_id = #{roleId}")
    @Results({
            @Result(id = true, property = "menuId", column = "menu_id")
    })
    List<String> getMenuRoleByRoleId(String roleId);


    /**
     * 添加权限
     * @param power
     */
    @Insert({
            "insert into aa_power(power_id,role_id,menu_id) values(",
            "#{powerId},#{roleId},#{menuId})"
    })
    int insertPower(Power power);


    /**
     * 根据roleId删除角色
     * @param roleId
     */
    @Delete("DELETE FROM aa_power WHERE role_id = #{roleId}")
    int deleteRoleById(@Param("roleId") String roleId);

    /** 查询所有*/
    @Select("select power_id, menu_id,role_id\n" +
            "        from aa_power\n" +
            "        where role_id in (select role_id from aa_user_role where admin_id = #{userId}) GROUP BY menu_id")
    @Results({
            @Result(id = true, property = "menuId", column = "menu_id")
    })
    List<Power> getMenuRoleByRoleIdList(@Param("userId") String userId);
}

