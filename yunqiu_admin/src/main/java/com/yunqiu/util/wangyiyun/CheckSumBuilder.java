package com.yunqiu.util.wangyiyun;

import com.google.gson.Gson;
import com.yunqiu.controller.common.Config;
import com.yunqiu.util.DateKit;
import com.yunqiu.util.HttpsClientUtil;
import com.yunqiu.util.Utils;
import org.springframework.web.multipart.MultipartFile;

import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by guohaiying on 2017/3/2.
 */
public class CheckSumBuilder {
    public static String getCheckSum(String appSecret, String nonce, String curTime) {
        return encode("sha1", appSecret + nonce + curTime);
    }

    private static String encode(String algorithm, String value) {
        if (value == null) {
            return null;
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
            messageDigest.update(value.getBytes());
            return getFormattedText(messageDigest.digest());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static String getFormattedText(byte[] bytes) {
        int len = bytes.length;
        StringBuilder buf = new StringBuilder(len * 2);
        for (int j = 0; j < len; j++) {
            buf.append(HEX_DIGITS[(bytes[j] >> 4) & 0x0f]);
            buf.append(HEX_DIGITS[bytes[j] & 0x0f]);
        }
        return buf.toString();
    }

    private static final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    //请求网易云 获取返回数据
    public static String getScreenshots(String vid) {
        Map<String, Object> params = new HashMap<String, Object>();
        int nonce = Utils.getRandow(4);
        String AppKey = Config.WY_AppKey;
        long CurTime = DateKit.getDateStr();
        String CheckSum = CheckSumBuilder.getCheckSum(Config.WY_AppSecret, nonce + "", CurTime + "");
        params.put("AppKey", AppKey);
        params.put("Nonce", nonce);
        params.put("CurTime", CurTime);
        params.put("CheckSum", CheckSum);
        Map<String, Object> c_params = new HashMap<String, Object>();
        c_params.put("vid", vid);
        Gson gson = new Gson();
        String jsonStr = gson.toJson(c_params);

        String post = HttpsClientUtil.doPostSSL(Config.WY_URL + "/app/vod/video/get", jsonStr, params);
        return post;
    }


    //请求上传接口 获取返回数据
    public static String getImage(MultipartFile videoName) {
        Map<String, Object> c_params = new HashMap<String, Object>();
        Gson gson = new Gson();
        c_params.put("file",videoName);
        String jsonStr = gson.toJson(c_params);

        //上传图片
        String address = HttpsClientUtil.doPost(Config.Upload_Arress,jsonStr);
        return address;
    }


}
