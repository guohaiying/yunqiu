package com.yunqiu.model;

import java.util.Map;

/**
 * Created by guohaiying on 2017/3/3.
 */
public class WYYEntity {

    private String code;
    private String msg;
    private Map<String,Object> ret;

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public Map<String, Object> getRet() {
        return ret;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setRet(Map<String, Object> ret) {
        this.ret = ret;
    }
}
