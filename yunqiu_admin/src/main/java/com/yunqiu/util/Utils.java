package com.yunqiu.util;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 工具类
 *
 * @version 1.0
 * @time 2016-12-30
 * @user 武尊
 */
public class Utils {
    final static char[] digits = { '0', '1', '2', '3', '4', '5', '6', '7', '8',
            '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l',
            'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y',
            'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L',
            'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y',
            'Z' };
    /**
     * ID生成
     * @return
     * @time 2016-12-30
     * @user 武尊
     * @version 1.0
     */
    public static String getID(int size) {
        Random random = new Random();
        char[] cs = new char[size];
        for (int i = 0; i < cs.length; i++) {
            cs[i] = digits[random.nextInt(digits.length)];
        }
        return new String(cs);
    }

    /**
     * 获取指定长度的随机数
     *
     * @param lenght
     * @return
     * @time 2016-12-30
     * @user 武尊
     * @version 1.0
     */
    public static int getRandow(int lenght) {
        Random rm = new Random();
        // 获得随机数
        double pross = (1 + rm.nextDouble()) * Math.pow(10, lenght);
        // 将获得的获得随机数转化为字符串
        String fixLenthString = String.valueOf(pross);
        return Integer.parseInt(fixLenthString.substring(1, lenght + 1));
    }

    /**
     * 验证手机号格式
     *
     * @param phone 需要验证的电话号码字符串
     * @return true 正确 false 错误
     * @author 邓时武
     * @version 1.0
     * @time 2016-12-30
     * @status 正常
     */
    public static boolean isMobilephone(String phone) {
        if (phone.startsWith("86") || phone.startsWith("+86")) {
            phone = phone.substring(phone.indexOf("86") + 2);
        }
        Pattern p = Pattern.compile("^((13[0-9])|(14[0-9])|(15[0-9])|(17[0-9])|(18[0-9]))\\d{8}$");
        Matcher m = p.matcher(phone);
        return m.matches();
    }

    /**
     * 验证字符是否为空
     *
     * @param str 需要验证的字符串
     * @return true：为空  false：非空
     * @author 邓时武
     * @version 1.0
     * @time 2016-12-30
     * @status 正常
     */
    public static boolean isNull(String str) {
        if (str != null && !str.trim().equals("")) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 获取ip
     *
     * @param request
     * @return
     */
    public static String getIpAddress(HttpServletRequest request) {
        try {
            String ip = request.getHeader("X-Real-IP");
            if (!Utils.isNull(ip) && !"unknown".equalsIgnoreCase(ip)) {
                return ip;
            }
            ip = request.getHeader("X-Forwarded-For");
            if (!Utils.isNull(ip) && !"unknown".equalsIgnoreCase(ip)) {
                // 多次反向代理后会有多个IP值，第一个为真实IP。
                int index = ip.indexOf(",");
                if (index != -1) {
                    return ip.substring(0, index);
                } else {
                    return ip;
                }
            } else {
                return request.getRemoteAddr();
            }
        } catch (Exception e) {
            LoggerUtil.outError(Utils.class, "获取IP地址时发生错误", e);
            return "0.0.0.0";
        }
    }

    /**
     * 获取指定范围（60--80）内的随机数
     * @return
     */
    public static int getRandowByScope(){
        return (int)(60+Math.random()*(80-60+1));
    }

    /**
     * SHA加密
     * @param inputStr
     * @return
     */
    public static String SHAEncrypt(String inputStr) {
        BigInteger sha = null;
        byte[] inputData = inputStr.getBytes();
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA");
            messageDigest.update(inputData);
            sha = new BigInteger(messageDigest.digest());
        } catch (Exception e) {
            LoggerUtil.outError(Utils.class, "SHA加密时发生错误", e);
        }
        return sha.toString();
    }

}
