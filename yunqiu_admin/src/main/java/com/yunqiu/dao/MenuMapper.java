package com.yunqiu.dao;


import com.yunqiu.model.Menu;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface MenuMapper {
    /** 查询所有*/
    @Select("select * from aa_menu order by sort asc")
    @Results({
            @Result(id = true, property = "menuId", column = "menu_id"),
            @Result(property = "name", column = "name"),
            @Result(property = "url", column = "url"),
            @Result(property = "icon", column = "icon"),
            @Result(property = "type", column = "type"),
            @Result(property = "father", column = "father"),
    })
    List<Menu> selecteAllMenu();


    /**
     * 根据id查询管理员
     * @param menuId
     * @return
     */
    @Select("select * from aa_menu where 1=1 AND menu_id=#{menuId}")
    @Results({
            @Result(id = true, property = "menuId", column = "menu_id"),
            @Result(property = "name", column = "name"),
            @Result(property = "url", column = "url"),
            @Result(property = "icon", column = "icon"),
            @Result(property = "type", column = "type"),
            @Result(property = "father", column = "father"),
            @Result(property = "sort", column = "sort")
    })
    Menu findIdMenu(@Param("menuId") String menuId);


    /**
     * 查询用户的按钮权限
     * @param menuId
     * @return
     */
    @Select("select m.`name` as name,m.url\n" +
            "    from aa_menu m,aa_power p where m.menu_id=p.menu_id \n" +
            " and m.type=3 and p.role_id in (select role_id from aa_user_role where admin_id=#{userId}) and m.father = #{menuId} order by m.sort asc")
    @Results({
            @Result(id = true, property = "menuName", column = "menuName"),
            @Result(property = "url", column = "url")
    })
    List<Menu> findUserButtonMenu(@Param("userId") String userId, @Param("menuId") String menuId);

    /**
     * 查询用户的菜单权限
     * @param userId
     * @return
     */
    @Select("select  ap.menu_id as menuId,am.name,am.father,am.url,am.icon,am.type\n" +
            "      from aa_power ap,aa_menu am\n" +
            "where \n" +
            "ap.menu_id = am.menu_id and am.type!=3 and\n" +
            "ap.role_id in (select role_id from aa_user_role where admin_id = #{userId}) GROUP BY ap.menu_id ORDER BY am.sort asc")
    @Results({
            @Result(id = true, property = "menuId", column = "menu_id"),
            @Result(property = "name", column = "name"),
            @Result(property = "url", column = "url"),
            @Result(property = "icon", column = "icon"),
            @Result(property = "type", column = "type"),
            @Result(property = "father", column = "father"),
            @Result(property = "sort", column = "sort")
    })
    List<Menu> getMenu(@Param("userId")  String userId);

}
