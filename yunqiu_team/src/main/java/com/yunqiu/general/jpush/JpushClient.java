package com.yunqiu.general.jpush;

import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.Notification;
import com.yunqiu.util.LoggerUtil;

/**
 * 极光推送工具类
 * @author 武尊
 * @time 2017-03-25
 */
public class JpushClient {
    private final static String APPKEY = "daefb44083dd5b7cf6b36ef0";
    private final static String SECRETKEY = "5e01419e7221894961ee1907";
    private final static JPushClient jPushClient = new JPushClient(SECRETKEY, APPKEY);

    /**
     * 根据别名给全平台用户推送消息
     * @param alert  通知内容
     * @param content 自定义消息
     * @param alias  别名
     * @return
     */
    public static boolean pushByAliasAndAll(String alert,String content,Audience alias){
        try {
            PushPayload pushPayload =PushPayload.newBuilder()
                    .setPlatform(Platform.all())                //设置平台
                    .setAudience(alias)         //设置接收客户
                    .setNotification(Notification.alert(alert)) //设置通知内容
                    .setOptions(Options.newBuilder()
                            .setApnsProduction(true)//true-推送生产环境 false-推送开发环境（测试使用参数）
                            .build())
                    /*.setMessage(Message.content(content))       //自定义消息*/
                    .build();
            PushResult pushResult = jPushClient.sendPush(pushPayload);
            if (pushResult.statusCode == 0){
                return true;
            }else {
                return false;
            }
        }catch (Exception e){
            LoggerUtil.outError(JpushClient.class,"根据别名给全平台用户推送消息时发送错误",e);
            return false;
        }
    }
}
