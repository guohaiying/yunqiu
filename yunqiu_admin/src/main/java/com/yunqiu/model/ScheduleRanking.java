package com.yunqiu.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by guohaiying on 2017/2/18.
 */
public class ScheduleRanking {

    private String ranking_id;//主键
    private String schedule_id;//昵称
    private String team_id;//头像
    private int current_ranking;//身高（CM）
    private int lifting;//体重（KG）
    private int game_number;//性别1男 2女
    private int birthday;//出生年月
    private int victory;//年龄
    private int flat;//球龄，只可为整数
    private int negation;//惯用脚，1：左脚 2：右脚
    private int goal;//擅长位置
    private int lose;//国家
    private int integral;//省，外键，关联省数据表

    public String getRanking_id() {
        return ranking_id;
    }

    public String getSchedule_id() {
        return schedule_id;
    }

    public String getTeam_id() {
        return team_id;
    }

    public int getCurrent_ranking() {
        return current_ranking;
    }

    public int getLifting() {
        return lifting;
    }

    public int getGame_number() {
        return game_number;
    }

    public int getBirthday() {
        return birthday;
    }

    public int getVictory() {
        return victory;
    }

    public int getFlat() {
        return flat;
    }

    public int getNegation() {
        return negation;
    }

    public int getGoal() {
        return goal;
    }

    public int getLose() {
        return lose;
    }

    public int getIntegral() {
        return integral;
    }

    public void setRanking_id(String ranking_id) {
        this.ranking_id = ranking_id;
    }

    public void setSchedule_id(String schedule_id) {
        this.schedule_id = schedule_id;
    }

    public void setTeam_id(String team_id) {
        this.team_id = team_id;
    }

    public void setCurrent_ranking(int current_ranking) {
        this.current_ranking = current_ranking;
    }

    public void setLifting(int lifting) {
        this.lifting = lifting;
    }

    public void setGame_number(int game_number) {
        this.game_number = game_number;
    }

    public void setBirthday(int birthday) {
        this.birthday = birthday;
    }

    public void setVictory(int victory) {
        this.victory = victory;
    }

    public void setFlat(int flat) {
        this.flat = flat;
    }

    public void setNegation(int negation) {
        this.negation = negation;
    }

    public void setGoal(int goal) {
        this.goal = goal;
    }

    public void setLose(int lose) {
        this.lose = lose;
    }

    public void setIntegral(int integral) {
        this.integral = integral;
    }
}
