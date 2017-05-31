package com.yunqiu.util;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Random;
import java.util.UUID;
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
    /**
     * 生成22长度的唯一字符串UUID
     *
     * @return
     * @time 2016-12-30
     * @user 武尊
     * @version 1.0
     */
    public static String getUUID() {
        //采用URL Base64字符，即把“+/”换成“-_”
        char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_=".toCharArray();

        UUID uuid = UUID.randomUUID();
        long msb = uuid.getMostSignificantBits();
        long lsb = uuid.getLeastSignificantBits();
        char[] out = new char[24];
        int tmp = 0, idx = 0;
        // 循环写法
        int bit = 0, bt1 = 8, bt2 = 8;
        int mask = 0x00, offsetm = 0, offsetl = 0;
        for (; bit < 16; bit += 3, idx += 4) {
            offsetm = 64 - (bit + 3) * 8;
            offsetl = 0;
            tmp = 0;
            if (bt1 > 3) {
                mask = (1 << 8 * 3) - 1;
            } else if (bt1 >= 0) {
                mask = (1 << 8 * bt1) - 1;
                bt2 -= 3 - bt1;
            } else {
                mask = (1 << 8 * ((bt2 > 3) ? 3 : bt2)) - 1;
                bt2 -= 3;
            }
            if (bt1 > 0) {
                bt1 -= 3;
                tmp = (int) ((offsetm < 0) ? msb : (msb >>> offsetm) & mask);
                if (bt1 < 0) {
                    tmp <<= Math.abs(offsetm);
                    mask = (1 << 8 * Math.abs(bt1)) - 1;
                }
            }
            if (offsetm < 0) {
                offsetl = 64 + offsetm;
                tmp |= ((offsetl < 0) ? lsb : (lsb >>> offsetl)) & mask;
            }

            if (bit == 15) {
                out[idx + 3] = alphabet[64];
                out[idx + 2] = alphabet[64];
                tmp <<= 4;
            } else {
                out[idx + 3] = alphabet[tmp & 0x3f];
                tmp >>= 6;
                out[idx + 2] = alphabet[tmp & 0x3f];
                tmp >>= 6;
            }
            out[idx + 1] = alphabet[tmp & 0x3f];
            tmp >>= 6;
            out[idx] = alphabet[tmp & 0x3f];
        }
        return new String(out, 0, 22);
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
