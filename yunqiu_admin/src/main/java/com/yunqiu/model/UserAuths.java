package com.yunqiu.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * Created by guohaiying on 2017/2/18.
 */
public class UserAuths {

    private String authId; // 主键
    private String userId; //外键，关联用户信息
    private int identityType;//账户类型 1：手机号 2：微信 3：QQ
    private String identifier;//登录账户，第三方存储第三方的唯一标识
    private String credential;//登录密码，第三方可为空，或存储授权token
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date registrationTime;//注册时间
    private String registrationIp;//注册IP
    private String loginTime;//最后登录时间
    private String standby;//备用字段

    public String getAuthId() {
        return authId;
    }

    public String getUserId() {
        return userId;
    }

    public int getIdentityType() {
        return identityType;
    }

    public String getIdentifier() {
        return identifier;
    }

    public String getCredential() {
        return credential;
    }

    public Date getRegistrationTime() {
        return registrationTime;
    }

    public String getRegistrationIp() {
        return registrationIp;
    }

    public String getLoginTime() {
        return loginTime;
    }

    public String getStandby() {
        return standby;
    }

    public void setAuthId(String authId) {
        this.authId = authId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setIdentityType(int identityType) {
        this.identityType = identityType;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public void setCredential(String credential) {
        this.credential = credential;
    }

    public void setRegistration_time(Date registrationTime) {
        this.registrationTime = registrationTime;
    }

    public void setRegistrationIp(String registrationIp) {
        this.registrationIp = registrationIp;
    }

    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }

    public void setStandby(String standby) {
        this.standby = standby;
    }

}
