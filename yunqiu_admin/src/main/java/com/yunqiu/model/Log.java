package com.yunqiu.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 *  操作日志
 *  @author 武尊
 *  @time 2017-01-10
 *  @version 1.0
 */
public class Log implements Serializable {
    private String logId;//主键
    private int type;//类型 1：登陆 2：修改 3：删除 4：新增 5：查询
    private String adminId;//操作人
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date recordTime;//操作时间
    private String detailedMsg;//描述

    public String getLogId() {
        return logId;
    }

    public void setLogId(String logId) {
        this.logId = logId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public Date getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(Date recordTime) {
        this.recordTime = recordTime;
    }

    public String getDetailedMsg() {
        return detailedMsg;
    }

    public void setDetailedMsg(String detailedMsg) {
        this.detailedMsg = detailedMsg;
    }
}
