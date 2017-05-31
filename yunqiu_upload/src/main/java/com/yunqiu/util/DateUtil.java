package com.yunqiu.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期处理工具类
 * @author 武尊
 * @version V1.0.0
 * @time 2016/8/18
 */
public class DateUtil {
    //时间格式（年）
    public static final String FORMAT_Y = "yyyy";
    //时间格式（月）
    public static final String FORMAT_M = "MM";
    //时间格式（日）
    public static final String FORMAT_D = "dd";
    //时间格式（小时）
    public static final String FORMAT_H = "HH";
    //时间格式（分钟）
    public static final String FORMAT_F = "mm";
    //时间格式（秒）
    public static final String FORMAT_S = "ss";
    //时间格式（年-月）
    public static final String FORMAT_YM = "yyyy-MM";
    //时间格式（年-月-日）
    public static final String FORMAT_YMD = "yyyy-MM-dd";
    //时间格式（年-月-日 时）
    public static final String FORMAT_YMDH = "yyyy-MM-dd HH";
    //时间格式（年-月-日 时:分）
    public static final String FORMAT_YMDHM = "yyyy-MM-dd HH:mm";
    //时间格式（年-月-日 时：分：秒）
    public static final String FORMAT_YMDHMS = "yyyy-MM-dd HH:mm:ss";
    //时间格式（时:分）
    public static final String FORMAT_HM = "HH:mm";
    //时间格式（时:分:秒）
    public static final String FORMAT_HMS = "HH:mm:ss";

    /**
     * 判断是否为合法的时间字符串
     * @author 武尊
     * @time 2016/8/19
     * @version V1.0.0
     * @param dateString 时间字符串
     * @param dateFormat 时间格式（例如：2016-08-19 15:15:15 yyyy-MM-dd hh:mm:ss）
     * @return true 合法  false 不合法
     */
    public static boolean isDate(String dateString,String dateFormat){
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
            simpleDateFormat.setLenient(false);
            simpleDateFormat.format(simpleDateFormat.parse(dateString));
            return true;
        }catch (ParseException e){
            return false;
        }
    }

    /**
     * 获取当前时间的时间戳
     * @author 武尊
     * @time 2016/8/19
     * @version V1.0.0
     * @return
     */
    public static long getTimestamp(){
        return System.currentTimeMillis();
    }

    /**
     * 获取指定时间的时间戳
     * @author 武尊
     * @time 2016/8/19
     * @version V1.0.0
     * @param dateString 时间字符串
     * @param dateFormat 时间格式
     * @return
     */
    public static long getTimestamp(String dateString,String dateFormat){
        try {
            if (!isDate(dateString,dateFormat)){
                return 0;
            }
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
            Date date = simpleDateFormat.parse(dateString);
            long millisTime = date.getTime();
            return millisTime;
        }catch (Exception e){
            LoggerUtil.outError(DateUtil.class,"",e);
            return 0;
        }
    }

    /**
     * 获取当前时间(Date类型)
     * @author 武尊
     * @time 2016/8/19
     * @version V1.0.0
     * @param dateFormat 时间格式
     * @return
     */
    public static Date getDateTime(String dateFormat){
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
            Date dateTime = new Date();
            dateTime = simpleDateFormat.parse(simpleDateFormat.format(dateTime));
            return dateTime;
        }catch (Exception e){
            return null;
        }
    }

    /**
     * 获取当前时间(String类型)
     * @author 武尊
     * @time 2016/8/19
     * @version V1.0.0
     * @param dateFormat 时间格式
     * @return
     */
    public static String getStringTime(String dateFormat){
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
            String dateTime = null;
            dateTime = simpleDateFormat.format(new Date());
            return dateTime;
        }catch (Exception e){
            return null;
        }
    }

    /**
     * Date类型转String类型
     * @author 武尊
     * @time 2016/8/19
     * @version V1.0.0
     * @param date 时间
     * @param dateFormat 时间格式
     * @return
     */
    public static String DTDateToString(Date date,String dateFormat){
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
            String dateTime = simpleDateFormat.format(date);
            return dateTime;
        }catch(Exception e){
            return null;
        }
    }

    /**
     * String类型转Date类型
     * @author 武尊
     * @time 2016/8/19
     * @version V1.0.0
     * @param date 时间
     * @param dateFormat 时间格式
     * @return
     */
    public static Date DTStringToDate(String date,String dateFormat){
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
            Date dateTime = simpleDateFormat.parse(date);
            return dateTime;
        }catch (Exception e){
            return null;
        }
    }

    /**
     * 获取unix时间，从1970-01-01 00:00:00开始的秒数
     * @param date
     * @return long
     */
    public static long getUnixTime(Date date) {
        if( null == date ) {
            return 0;
        }
        return date.getTime()/1000;
    }
}
