package com.yunqiu.dao;

import com.yunqiu.model.UserCloudData;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 云五数据
 */

@Mapper
public interface UserCloudDataMapper {
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
     * 查询用户云五数据
     * @param user_id
     * @return
     */
    @Select({
            "select * from pw_user_cloudData where user_id=#{user_id}"
    })
    UserCloudData selectUserCloudDataByUserId(@Param("user_id") String user_id);
}
