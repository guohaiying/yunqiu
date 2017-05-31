package com.yunqiu.dao;


import com.yunqiu.model.Banner;
import com.yunqiu.model.Integrate;
import com.yunqiu.model.Menu;
import com.yunqiu.model.PageCrt;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface IntegrateMapper {

    @Insert({
            "insert into aa_integrate(id,win,fail,tie) values(",
            "#{id},#{win},#{fail},#{tie})"
    })
    int insertIntegrate(Integrate integrate);

    @Update("update aa_integrate set win=#{win},fail=#{fail},tie=#{tie} where id=#{id}")
    int updateIntegrate(Integrate integrate);

    @Select("select * from aa_integrate where 1=1 ")
    @Results({
            @Result(id = true, property = "menuId", column = "menu_id")
    })
    Integrate findIdIntegrate(@Param("id") String id);
}
