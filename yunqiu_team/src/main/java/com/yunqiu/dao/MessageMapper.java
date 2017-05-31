package com.yunqiu.dao;

import com.yunqiu.model.Message;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * 推送消息
 * @author 武尊
 * @time 2017-03-25
 */
@Mapper
public interface MessageMapper {

    /**
     * 添加消息
     * @param message
     */
    @Insert({
            "insert into pw_message(message_id,message_title,message,message_type,type_id,specific_type,specific_id,push_time,user_id,is_look) ",
            "values(#{message_id},#{message_title},#{message},#{message_type},#{type_id},#{specific_type},#{specific_id},#{push_time},#{user_id},#{is_look})"
    })
    void insert(Message message);
}
