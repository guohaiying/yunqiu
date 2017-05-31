package com.yunqiu.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by guohaiying on 2017/2/18.
 */
public class AppUser {

    private String userId;//主键
    private String nickname;//昵称
    private String portrait;//头像
    private int stature;//身高（CM）
    private double weight;//体重（KG）
    private int sex;//性别1男 2女
    @DateTimeFormat( pattern = "yyyy-MM-dd" )
    private Date birthday;//出生年月
    private int age;//年龄
    private int veteran;//球龄，只可为整数
    private int foot;//惯用脚，1：左脚 2：右脚
    private String position;//擅长位置
    private String countries;//国家
    private String province;//省，外键，关联省数据表
    private String city;//市，外键，关联市数据表
    private String area;//区 ，外键，关联区数据表
    private String status;//账户状态 1：正常 2：冻结
    private String login_time;//最后登录时间
    private String label;//个人标签，多个以”,”号隔开
    private String standby;//备用字段

    public String getUserId() {
        return userId;
    }

    public String getNickname() {
        return nickname;
    }

    public String getPortrait() {
        return portrait;
    }

    public int getStature() {
        return stature;
    }

    public double getWeight() {
        return weight;
    }

    public int getSex() {
        return sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public int getAge() {
        return age;
    }

    public int getVeteran() {
        return veteran;
    }

    public int getFoot() {
        return foot;
    }

    public String getPosition() {
        return position;
    }

    public String getCountries() {
        return countries;
    }

    public String getProvince() {
        return province;
    }

    public String getCity() {
        return city;
    }

    public String getArea() {
        return area;
    }

    public String getStatus() {
        return status;
    }

    public String getLogin_time() {
        return login_time;
    }

    public String getLabel() {
        return label;
    }

    public String getStandby() {
        return standby;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public void setStature(int stature) {
        this.stature = stature;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setVeteran(int veteran) {
        this.veteran = veteran;
    }

    public void setFoot(int foot) {
        this.foot = foot;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setCountries(String countries) {
        this.countries = countries;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setLogin_time(String login_time) {
        this.login_time = login_time;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setStandby(String standby) {
        this.standby = standby;
    }

}
