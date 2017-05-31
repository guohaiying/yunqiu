package com.yunqiu.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;


public class Integrate implements Serializable {

    private String id;//主键
    private int win;
    private int fail;
    private int tie;

    public String getId() {
        return id;
    }

    public int getWin() {
        return win;
    }

    public int getFail() {
        return fail;
    }

    public int getTie() {
        return tie;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setWin(int win) {
        this.win = win;
    }

    public void setFail(int fail) {
        this.fail = fail;
    }

    public void setTie(int tie) {
        this.tie = tie;
    }
}
