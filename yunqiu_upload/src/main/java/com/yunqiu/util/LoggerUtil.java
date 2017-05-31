package com.yunqiu.util;


import org.apache.log4j.Logger;

/**
 * 日志输出
 * @time 2016-12-30
 * @user 武尊
 * @version 1.0
 */
public class LoggerUtil {

    /**
     * debug日志输出
     * @time 2016-12-30
     * @user 武尊
     * @verison 1.0
     * @param clazz
     * @param msg
     */
    public static void outDebug(Class<? extends Object> clazz,String msg){
        Logger logger = Logger.getLogger(clazz);
        logger.debug(msg);
    }

    /**
     * error日志输出
     * @time 2016-12-30
     * @user 武尊
     * @version 1.0
     * @param clazz
     * @param msg
     * @param e
     */
    public static void outError(Class<? extends Object> clazz, String msg, Exception e){
        Logger logger = Logger.getLogger(clazz);
        if (e != null){
            logger.error(msg,e);
        }else{
            logger.error(msg);
        }
    }


}
