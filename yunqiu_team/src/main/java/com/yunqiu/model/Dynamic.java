package com.yunqiu.model;

import java.util.Date;

/**
 * 动态实体类
 */
public class Dynamic {
    private String dynamic_id;
    private String user_id;
    private int dynamic_type;
    private String business_id;
    private String dynamic_desc;
    private int is_look;
    private Date create_time;

    public String getDynamic_id() {
        return dynamic_id;
    }

    public void setDynamic_id(String dynamic_id) {
        this.dynamic_id = dynamic_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public int getDynamic_type() {
        return dynamic_type;
    }

    public void setDynamic_type(int dynamic_type) {
        this.dynamic_type = dynamic_type;
    }

    public String getBusiness_id() {
        return business_id;
    }

    public void setBusiness_id(String business_id) {
        this.business_id = business_id;
    }

    public String getDynamic_desc() {
        return dynamic_desc;
    }

    public void setDynamic_desc(String dynamic_desc) {
        this.dynamic_desc = dynamic_desc;
    }

    public int getIs_look() {
        return is_look;
    }

    public void setIs_look(int is_look) {
        this.is_look = is_look;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }
}
