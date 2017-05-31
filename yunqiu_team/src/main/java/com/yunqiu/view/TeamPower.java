package com.yunqiu.view;

import java.util.Map;

/**
 * 球队战力值
 */
public class TeamPower {
    private int power_number;//总战力值
    private int status;//状态 0：保持不变 1：上升  2：下降
    private int float_number;//升降值
    private Map<String,Integer> attack;//进攻数据，3个值，与以上参数名一致
    private Map<String,Integer> guard;//防守数据，3个值，与以上参数名一致
    private Map<String,Integer> stamina;//体能数据，3个值，与以上参数名一致
    private Map<String,Integer> science;//技术数据，3个值，与以上参数名一致
    private Map<String,Integer> aggressive;//侵略性数据，3个值，与以上参数名一致

    public int getPower_number() {
        return power_number;
    }

    public void setPower_number(int power_number) {
        this.power_number = power_number;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getFloat_number() {
        return float_number;
    }

    public void setFloat_number(int float_number) {
        this.float_number = float_number;
    }

    public Map<String, Integer> getAttack() {
        return attack;
    }

    public void setAttack(Map<String, Integer> attack) {
        this.attack = attack;
    }

    public Map<String, Integer> getGuard() {
        return guard;
    }

    public void setGuard(Map<String, Integer> guard) {
        this.guard = guard;
    }

    public Map<String, Integer> getStamina() {
        return stamina;
    }

    public void setStamina(Map<String, Integer> stamina) {
        this.stamina = stamina;
    }

    public Map<String, Integer> getScience() {
        return science;
    }

    public void setScience(Map<String, Integer> science) {
        this.science = science;
    }

    public Map<String, Integer> getAggressive() {
        return aggressive;
    }

    public void setAggressive(Map<String, Integer> aggressive) {
        this.aggressive = aggressive;
    }
}
