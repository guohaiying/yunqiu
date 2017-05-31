package com.yunqiu.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 工具类:时间处理
 * 作者：武尊
 * 日期：2015-02-23
 */
public class DateKit {
	
	/**
     * 获取当前时间和传入时间相差的小时数
     * @param time：
     * 要比较的时间
     * @return
     */
	public static  long getIntervalHours (Date time) {
		//获取当前时间的秒数
		long nowSeconds= getDateStr();
		//获取要比较时间的秒数
	    long comPareSeconds= time.getTime()/1000;
		return (comPareSeconds-nowSeconds)/60/60;	
	}
	
	/**
     * 获取当前时间和传入时间相差的分钟数
     * @param time：
     * 要比较的时间
     * @return
     */
	public static  long getIntervalMin (Date time) {
		//获取当前时间的秒数
		long nowSeconds= getDateStr();
		//获取要比较时间的秒数
	    long comPareSeconds= time.getTime()/1000;
		return (comPareSeconds-nowSeconds)/60;	
	}
	/**
     * String类型转时间类型
     * @param str：
     * 时间的格式 yyyy-MM-dd HH:mm:ss 获取到2015-01-15 14:45:15 
     * yyyy-MM-dd HH:mm 获取到2015-01-15 14:45
     * @param dateString:时间,列如：2014-10-14
     * @return
     */
	public static Date DTStringtoDate(String str,String dateString) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(dateString);
			Date date = sdf.parse(str);
			return date;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	/**
     * Date类型转String类型
     * @param str：
     * 时间的格式 yyyy-MM-dd HH:mm:ss 获取到2015-01-15 14:45:15 
     * yyyy-MM-dd HH:mm 获取到2015-01-15 14:45
     * @param dateTime:时间,列如：2014-10-14
     * @return
     */
	public static String DTDatetoString(String str,Date dateTime) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(str);
			String date = sdf.format(dateTime);
			return date;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 获取当前时间(Date类型)
	 * 
	 * @param str
	 *            :时间的格式 yyyy-MM-dd HH:mm:ss 获取到2015-01-15 14:45:15 yyyy-MM-dd
	 *            HH:mm 获取到2015-01-15 14:45
	 * @return
	 */
	public static Date getDateTime(String str) {
		SimpleDateFormat sdf = new SimpleDateFormat(str);
		Date dateTime = new Date();
		try {
			dateTime = sdf.parse(sdf.format(new Date()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dateTime;
	}
	/**
	 * 获取当前时间(String类型)
	 * 
	 * @param str
	 *            :时间的格式 yyyy-MM-dd HH:mm:ss 获取到2015-01-15 14:45:15 yyyy-MM-dd
	 *            HH:mm 获取到2015-01-15 14:45
	 * @return
	 */
	public static String getStringTime(String str) {
		SimpleDateFormat sdf = new SimpleDateFormat(str);
		String dateTime = null;
		try {
			dateTime = sdf.format(new Date());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dateTime;
	}
	/**
	 * 获取当月第一天   
	 * @return
	 */
    public static String getFirstDayOfMonth(){     
       String str = "";   
       SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");       
       Calendar lastDate = Calendar.getInstance();   
       lastDate.set(Calendar.DATE,1);//设为当前月的1号   
       str=sdf.format(lastDate.getTime());
       return str;     
    }
    /**
     * 计算当月最后一天,返回字符串
     * @return
     */
    public static String getDefaultDay(){     
       String str = "";   
       SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");       
       Calendar lastDate = Calendar.getInstance();   
       lastDate.set(Calendar.DATE,1);//设为当前月的1号   
       lastDate.add(Calendar.MONTH,1);//加一个月，变为下月的1号   
       lastDate.add(Calendar.DATE,-1);//减去一天，变为当月最后一天   
       str=sdf.format(lastDate.getTime());
       return str;     
    }
    /**
     * 获取本周第一天
     * @return
     */
    public static Date getFirstDayOfWeek() {
        Calendar c = new GregorianCalendar();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.setTime(DateKit.getDateTime("yyyy-MM-dd"));
        c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek()); // Monday
        return c.getTime();
    }
    /**
     * 获取本周最后一天
     * @return
     */
    public static Date getLastDayOfWeek() {
        Calendar c = new GregorianCalendar();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.setTime(DateKit.getDateTime("yyyy-MM-dd"));
        c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek() + 6); // Sunday
        return c.getTime();
    }
    /**
     * 获取当前日期指定前或者后几天
     * @param number 往前或者往后推的时间(正数后，负数前)
     * @return
     */
    public static String getOneDay(int number){
    	//获取当前系统时间
    	Date dateTime=new Date();
    	//得到日历 
    	Calendar calendar=Calendar.getInstance();
    	//把当前时间赋值给日历
    	calendar.setTime(dateTime);
    	//设置日历时间
    	calendar.add(Calendar.DAY_OF_MONTH, number);
    	//定义日期格式化
    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	//得到日期
    	return sdf.format(calendar.getTime());
    }
    /**
     * 获取当前时间前或者后几小时
     * @param number 正数前  负数 后
     * @return
     */
    public static String getOneHour(int number){
    	Calendar calendar = Calendar.getInstance();
		/* HOUR_OF_DAY 指示一天中的小时 */
		calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - number);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println("当前的时间：" + df.format(new Date()));
		return df.format(calendar.getTime());
    }
    /**
     * 计算时间相差几天
     * @param stTime  时间一,开始时间
     * @param endTime 时间二,结束时间
     * @return
     */
    public static String timeDifference(String stTime,String endTime){
    	String timeDifference="";
    	try{
    	   SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd");
      	   Date begin=dfs.parse(stTime);
      	   Date end = dfs.parse(endTime);
      	   long between=(end.getTime()-begin.getTime())/1000;//除以1000是为了转换成秒

      	   long day1=between/(24*3600);
      	 /*  long hour1=between%(24*3600)/3600;//时
      	   long minute1=between%3600/60;//分
      	   long second1=between%60/60;//秒*/ 
      	   timeDifference=timeDifference+day1;
    	}catch (Exception e) {
			// TODO: handle exception
		}
    	return timeDifference;
    }
    /**
     * 计算时间相差几小时
     * @param stTime  时间一,开始时间
     * @param endTime 时间二,结束时间
     * @return
     */
    public static String timeDifferences(String stTime,String endTime){
    	String timeDifference="";
    	try{
    	   SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd HH:mm");
      	   Date begin=dfs.parse(stTime);
      	   Date end = dfs.parse(endTime);
      	   long between=(end.getTime()-begin.getTime())/1000;//除以1000是为了转换成秒

      	   long day1=between/(24*3600);
      	   long hour1=between%(24*3600)/3600;//时
      	 /*long minute1=between%3600/60;//分
      	   long second1=between%60/60;//秒*/ 
      	   timeDifference=timeDifference+(day1*24+hour1);
    	}catch (Exception e) {
			// TODO: handle exception
		}
    	return timeDifference;
    }
    /**
     * 计算时间相差多少秒
     * @param stTime  时间一,开始时间
     * @param endTime 时间二,结束时间
     * @return
     */
    public static long timeDifferenceMm(String stTime,String endTime){
    	long time=0;
    	try{
    	   SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      	   Date begin=dfs.parse(stTime);
      	   Date end = dfs.parse(endTime);
      	   long between=(end.getTime()-begin.getTime())/1000;//除以1000是为了转换成秒
      	   time=between;
    	}catch (Exception e) {
			// TODO: handle exception
		}
    	return time;
    }
    /**
     * 是否已经过超过一分钟
     * @param date 需要判断的时间
     * @return
     */
    public static boolean isLongerthanMinute(Date date){
        Calendar cal = Calendar.getInstance();
         
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date);
        cal1.add(Calendar.MINUTE, +2);
         
        return cal1.compareTo(cal) == 1?false:true;
    }
    /**
     * 获取当日为星期几
     * 1.星期日；2.星期一；3.星期二；4.星期三
     * 5.星期四；6.星期五；7.星期六
     */
    public static int getWeek(){
    	Calendar c =Calendar.getInstance();
    	return c.get(Calendar.DAY_OF_WEEK);
    }
    
    /** 
     * 获得本天的结束时间，即2012-01-01 23:59:59 
     * 
     * @return 
     */ 
    public static Date getCurrentDayEndTime() { 
    	SimpleDateFormat shortSdf=new SimpleDateFormat("yyyy-MM-dd");
    	SimpleDateFormat longSdf =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = new Date(); 
        try { 
            now = longSdf.parse(shortSdf.format(now) + " 23:59:59"); 
        } catch (Exception e) { 
            e.printStackTrace(); 
        } 
        return now; 
    }
    
    /**
     * 获取当前已秒为但我的时间戳
     * @return
     */
    public static long getDateStr(){
		Date date= DateKit.getDateTime("yyyy-MM-dd HH:mm:ss");
    	return date.getTime()/1000;
    }
    
}
