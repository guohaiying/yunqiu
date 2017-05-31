package com.yunqiu.model;

import java.io.Serializable;

/**
 * 用户云五数据
 */
public class UserCloudData implements Serializable {
    private String cloud_id;
    private String user_id;
    private int attack_gross;
    private int attack_gains;
    private int defensive_gross;
    private int defensive_gains;
    private int physical_gross;
    private int physical_gains;
    private int technology_gross;
    private int technology_gains;
    private int aggressive_gross;
    private int aggressive_gains;
    private int power;
    private int gains;

    public String getCloud_id() {
        return cloud_id;
    }

    public void setCloud_id(String cloud_id) {
        this.cloud_id = cloud_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public int getAttack_gross() {
        return attack_gross;
    }

    public void setAttack_gross(int attack_gross) {
        this.attack_gross = attack_gross;
    }

    public int getAttack_gains() {
        return attack_gains;
    }

    public void setAttack_gains(int attack_gains) {
        this.attack_gains = attack_gains;
    }

    public int getDefensive_gross() {
        return defensive_gross;
    }

    public void setDefensive_gross(int defensive_gross) {
        this.defensive_gross = defensive_gross;
    }

    public int getDefensive_gains() {
        return defensive_gains;
    }

    public void setDefensive_gains(int defensive_gains) {
        this.defensive_gains = defensive_gains;
    }

    public int getPhysical_gross() {
        return physical_gross;
    }

    public void setPhysical_gross(int physical_gross) {
        this.physical_gross = physical_gross;
    }

    public int getPhysical_gains() {
        return physical_gains;
    }

    public void setPhysical_gains(int physical_gains) {
        this.physical_gains = physical_gains;
    }

    public int getTechnology_gross() {
        return technology_gross;
    }

    public void setTechnology_gross(int technology_gross) {
        this.technology_gross = technology_gross;
    }

    public int getTechnology_gains() {
        return technology_gains;
    }

    public void setTechnology_gains(int technology_gains) {
        this.technology_gains = technology_gains;
    }

    public int getAggressive_gross() {
        return aggressive_gross;
    }

    public void setAggressive_gross(int aggressive_gross) {
        this.aggressive_gross = aggressive_gross;
    }

    public int getAggressive_gains() {
        return aggressive_gains;
    }

    public void setAggressive_gains(int aggressive_gains) {
        this.aggressive_gains = aggressive_gains;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getGains() {
        return gains;
    }

    public void setGains(int gains) {
        this.gains = gains;
    }
}
