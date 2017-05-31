package com.yunqiu.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;


public class Banner implements Serializable {

    private String banner_id;//主键
    private String img_url;
    private String article_url;
    private String article_content;
    private int is_show;
    @DateTimeFormat( pattern = "yyyy-MM-dd HH:mm" )
    private Date create_time;
    private int sort;
    private String create_user;

    public String getBanner_id() {
        return banner_id;
    }

    public String getImg_url() {
        return img_url;
    }

    public String getArticle_url() {
        return article_url;
    }

    public String getArticle_content() {
        return article_content;
    }

    public int getIs_show() {
        return is_show;
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

    public void setBanner_id(String banner_id) {
        this.banner_id = banner_id;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public void setArticle_url(String article_url) {
        this.article_url = article_url;
    }

    public void setArticle_content(String article_content) {
        this.article_content = article_content;
    }

    public void setIs_show(int is_show) {
        this.is_show = is_show;
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
}
