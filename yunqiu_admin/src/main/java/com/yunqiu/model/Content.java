package com.yunqiu.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;


public class Content implements Serializable {

    private String content_id;//主键
    private String content_title;
    private String img_url;
    private String content;
    private int browse_number;
    @DateTimeFormat( pattern = "yyyy-MM-dd HH:mm" )
    private Date create_time;
    private int sort;
    private String create_user;
    private int is_show;

    public String getContent_id() {
        return content_id;
    }

    public String getContent_title() {
        return content_title;
    }

    public String getImg_url() {
        return img_url;
    }

    public String getContent() {
        return content;
    }

    public int getBrowse_number() {
        return browse_number;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public int getSort() {
        return sort;
    }

    public String getCreate_user() {
        return create_user;
    }

    public int getIs_show() {
        return is_show;
    }

    public void setContent_id(String content_id) {
        this.content_id = content_id;
    }

    public void setContent_title(String content_title) {
        this.content_title = content_title;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setBrowse_number(int browse_number) {
        this.browse_number = browse_number;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public void setCreate_user(String create_user) {
        this.create_user = create_user;
    }

    public void setIs_show(int is_show) {
        this.is_show = is_show;
    }
}
