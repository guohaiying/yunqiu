package com.yunqiu.general.sendMsg.util;

import com.yunqiu.util.GsonUtil;
import com.yunqiu.util.HttpsClientUtil;
import com.yunqiu.util.LoggerUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 云片短信发送
 * @author 武尊
 * @time 2016-12-31
 * @version 1.0
 */
public class SendSMS {
    //智能匹配模板发送接口的http地址
    private static final String URI_SEND_SMS = "https://sms.yunpian.com/v1/sms/send.json";
    //编码格式。发送编码格式统一用UTF-8
    private static final String ENCODING = "UTF-8";
    //修改为您的apikey.apikey可在官网（http://www.yunpian.com)登录后获取
    private static final String APIKEY = "3b9379e9bd188a1bc6bf9a97db206b87 ";

    /**
     * 发送短信验证码
     * @param type 1.注册
     * @param mobile 接受验证码的手机号
     * @param code 验证码
     */
   public static Map<String,Object> sendCode(int type, String mobile, String code){
       //设置您要发送的内容(内容必须和某个模板匹配）
       String text = "";
       try {
           //根据不同业务，发送不一样的短信内容
           switch (type){
               case 1 :
                   //注册发送验证码
                   text = "【云球yunqiu】您的验证码是"+ code+"。如非本人操作，请忽略本短信";
                   break;
               case 2:
                   text = "【云球yunqiu】您的验证码是"+ code+"。如非本人操作，请忽略本短信";
                   break;
               case 3:
                   text = "【云球yunqiu】您的验证码是"+ code+"。如非本人操作，请忽略本短信";
                   break;
               case 4:
                   text = "【云球yunqiu】您的验证码是"+ code+"。如非本人操作，请忽略本短信";
                   break;
               case 5:
                   text = "【云球yunqiu】您的验证码是"+ code+"。如非本人操作，请忽略本短信";
                   break;
               case 6:
                   text = "【云球yunqiu】您的验证码是"+ code+"。如非本人操作，请忽略本短信";
                   break;
           }
           //封装请求参数
           Map<String,Object> params = new HashMap<>();
           params.put("apikey",APIKEY);
           params.put("mobile",mobile);
           params.put("text",text);
           //发送请求
           String result = HttpsClientUtil.doPostSSL(URI_SEND_SMS,params);
           LoggerUtil.outDebug(SendSMS.class,"发送验证码的返回信息===="+result);
           //判断是否发送成功
           Map<String,Object> map = GsonUtil.stringToMap(result);
           return map;
       }catch (Exception e){
           LoggerUtil.outError(SendSMS.class,"发送短信验证码时发生系统异常，详情:",e);
       }
       return null;
   }

}
