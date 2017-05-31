package com.yunqiu.dao;

import com.yunqiu.model.Log;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 后台管理员操作日志
 * @author 武尊
 * @time 2017-01-11
 * @version 1.0
 */

@Mapper
public interface LogMapper {
    /**
     * 添加日志
     * @param log
     */
    @Insert("insert into aa_log(log_id,type,admin_id,record_time,detailed_msg) values(#{logId},#{type},#{adminId},#{recordTime},#{detailedMsg})")
    void insertLog(Log log);

    /**
     * 根据id删除日志
     * @param logId
     */
    @Delete("delete from aa_log where log_id = #{logId}")
    void deleteLogById(@Param("logId") String logId);

    /**
     * 清空日志表（删除所有日志）
     */
    @Delete("delete from aa_log")
    void deleteLogAll();

    /**
     * 查询所有日志
     * @return
     */
    @Select("select * from aa_log")
    @Results({
            @Result(id = true, property = "logId", column = "log_id"),
            @Result(property = "type", column = "type"),
            @Result(property = "adminId", column = "admin_id"),
            @Result(property = "recordTime", column = "record_time"),
            @Result(property = "describe", column = "describe"),
    })
    List<Log> selectLog();
}
