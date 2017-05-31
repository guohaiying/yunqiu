package com.yunqiu.dao;

import com.yunqiu.model.TeamCloudData;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 云五数据
 */

@Mapper
public interface TeamCloudDataMapper {
    /**
     * 添加云五数据
     * @param teamCloudData
     */
    @Insert({
            "insert into pw_team_cloudData(cloud_id,team_id,attack_gross,attack_gains,defensive_gross,defensive_gains,",
            "physical_gross,physical_gains,technology_gross,technology_gains,aggressive_gross,aggressive_gains,mean_power,gains,count_time) values(",
            "#{cloud_id},#{team_id},#{attack_gross},#{attack_gains},#{defensive_gross},#{defensive_gains},#{physical_gross},",
            "#{physical_gains},#{technology_gross},#{technology_gains},#{aggressive_gross},#{aggressive_gains},#{mean_power},#{gains},#{count_time})"
    })
    void insert(TeamCloudData teamCloudData);

    /**
     * 查询云五数据
     * @param team_id
     * @return
     */
    @Select({
            "select * from pw_team_cloudData where team_id=#{team_id} ORDER BY count_time DESC LIMIT #{number}"
    })
    List<TeamCloudData> selectUserCloudDataByUserId(@Param("team_id") String team_id, @Param("number") int number);

    @Select({
            "select * from pw_team_cloudData where team_id=#{team_id} ORDER BY count_time DESC LIMIT 1"
    })
    TeamCloudData selectUserCloudDataByUserIdOne(@Param("team_id") String team_id);
}
