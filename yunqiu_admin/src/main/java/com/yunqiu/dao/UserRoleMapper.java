package com.yunqiu.dao;


import com.yunqiu.model.UserRole;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface UserRoleMapper {

    /**
     * 添加
     * @param userRole
     */
    @Insert({
            "insert into aa_user_role(ur_id,admin_id,role_id) values(",
            "#{urId},#{adminId},#{roleId})"
    })
    int insertUserRole(UserRole userRole);


    /**
     * 根据用户删除用户和角色的关联
     * @param adminId
     */
    @Delete("DELETE FROM aa_user_role WHERE admin_id = #{adminId}")
    int deleteRoleByUserId(@Param("adminId") String adminId);


}
