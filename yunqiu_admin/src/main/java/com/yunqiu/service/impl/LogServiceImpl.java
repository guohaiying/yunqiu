package com.yunqiu.service.impl;

import com.yunqiu.dao.LogMapper;
import com.yunqiu.model.Log;
import com.yunqiu.service.LogService;
import com.yunqiu.util.LoggerUtil;
import com.yunqiu.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 管理员操作日志
 * @author 武尊
 * @time 2017-01-16
 * @version 1.0
 */

@Service
public class LogServiceImpl implements LogService {
    @Autowired
    private LogMapper logMapper;

    /**
     * 添加日志
     * @param type 日志类型
     * @param describe 描述
     */
    public void insertLog(int type,String adminId,String describe){
        try {
            Log log = new Log();
            log.setLogId(Utils.getID(22));
            log.setAdminId(adminId);
            log.setType(type);
            log.setDetailedMsg(describe);
            log.setRecordTime(new Date());
            logMapper.insertLog(log);
        }catch (Exception e){
            LoggerUtil.outError(LogServiceImpl.class,"记录管理员操作日志时发生错误",e);
        }
    }
}
