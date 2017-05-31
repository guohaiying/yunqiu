package com.yunqiu.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 推送消息
 * @author 武尊
 * @time 2017-03-25
 */
public class Message implements Serializable {
    private String message_id;
    private String message_title;
    private String message;
    private int message_type;
    private String type_id;
    private int specific_type;
    private String specific_id;
    private Date push_time;
    private String user_id;
    private int is_look;

    public String getMessage_id() {
        return message_id;
    }

    public void setMessage_id(String message_id) {
        this.message_id = message_id;
    }

    public String getMessage_title() {
        return message_title;
    }

    public void setMessage_title(String message_title) {
        this.message_title = message_title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getMessage_type() {
        return message_type;
    }

    public void setMessage_type(int message_type) {
        this.message_type = message_type;
    }

    public String getType_id() {
        return type_id;
    }

    public void setType_id(String type_id) {
        this.type_id = type_id;
    }

    public int getSpecific_type() {
        return specific_type;
    }

    public void setSpecific_type(int specific_type) {
        this.specific_type = specific_type;
    }

    public String getSpecific_id() {
        return specific_id;
    }

    public void setSpecific_id(String specific_id) {
        this.specific_id = specific_id;
    }

    public Date getPush_time() {
        return push_time;
    }

    public void setPush_time(Date push_time) {
        this.push_time = push_time;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public int getIs_look() {
        return is_look;
    }

    public void setIs_look(int is_look) {
        this.is_look = is_look;
    }
}
