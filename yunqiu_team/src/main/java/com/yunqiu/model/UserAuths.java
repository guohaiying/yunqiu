package com.yunqiu.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by 11366 on 2016/12/30.
 */
public class UserAuths implements Serializable {
    private String auth_id;
    private String user_id;
    private int identity_type;
    private String identifier;
    private String credential;
    private Date registration_time;
    private String  registration_ip;
    private Date login_time;
    private String standby;

    public String getAuth_id() {
        return auth_id;
    }

    public void setAuth_id(String auth_id) {
        this.auth_id = auth_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public int getIdentity_type() {
        return identity_type;
    }

    public void setIdentity_type(int identity_type) {
        this.identity_type = identity_type;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getCredential() {
        return credential;
    }

    public void setCredential(String credential) {
        this.credential = credential;
    }

    public Date getRegistration_time() {
        return registration_time;
    }

    public void setRegistration_time(Date registration_time) {
        this.registration_time = registration_time;
    }

    public String getRegistration_ip() {
        return registration_ip;
    }

    public void setRegistration_ip(String registration_ip) {
        this.registration_ip = registration_ip;
    }

    public Date getLogin_time() {
        return login_time;
    }

    public void setLogin_time(Date login_time) {
        this.login_time = login_time;
    }

    public String getStandby() {
        return standby;
    }

    public void setStandby(String standby) {
        this.standby = standby;
    }
}
