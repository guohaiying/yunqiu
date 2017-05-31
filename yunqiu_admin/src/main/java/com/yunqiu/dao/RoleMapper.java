package com.yunqiu.dao;


import com.yunqiu.model.PageCrt;
import com.yunqiu.model.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;


@Mapper
public interface RoleMapper {


    /** 查询总数*/
    @Select("select count(*) as count from aa_role")
    @Results({
            @Result(id = true, property = "count", column = "count")
    })
    int selectTotalPage();


    /** 分页查询*/
    //@Select("select * from aa_role LIMIT #{index},#{rows}")
    @Select({"<script>",
            "select role_id as roleId,role_name as roleName,remark,createTime from aa_role " +
             "<when test='sidx!=null'>",
                "ORDER BY ${sidx} ${sord}",
             "</when> LIMIT #{index},#{rows}",
            "</script> "})
    @Results({
            @Result(id = true, property = "roleId", column = "role_id"),
            @Result(property = "roleName", column = "role_name"),
            @Result(property = "remark", column = "remark"),
            @Result(property = "createTime", column = "createTime")
    })
    List<Map> selectePaging(PageCrt page);

    /** 查询所有*/
    @Select("select * from aa_role")
    @Results({
            @Result(id = true, property = "roleId", column = "role_id"),
            @Result(property = "roleName", column = "role_name"),
            @Result(property = "remark", column = "remark"),
            @Result(property = "createTime", column = "createTime")
    })
    List<Map<String, String>> selecteAllRole();

    /**
     * 添加角色
     * @param role
     */
    @Insert({
            "insert into aa_role(role_id,role_name,createTime,remark) values(",
            "#{roleId},#{roleName},#{createTime},#{remark})"
    })
    int insertRole(Role role);

    /**
     * 修改角色资料
     * @param role
     */
    @Update("update aa_role set role_name=#{roleName},remark=#{remark} where role_id=#{roleId}")
    int updateRole(Role role);

    /**
     * 根据id删除角色
     * @param roleId
     */
    @Delete("DELETE FROM aa_role WHERE role_id = #{roleId}")
    int deleteRoleById(@Param("roleId") String roleId);

}
