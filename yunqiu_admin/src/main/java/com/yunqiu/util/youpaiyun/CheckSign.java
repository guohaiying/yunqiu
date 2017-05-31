package com.yunqiu.util.youpaiyun;

import com.yunqiu.controller.common.Config;
import com.yunqiu.util.Utils;
import main.java.com.UpYun;

import java.io.File;

/**
 * Created by Administrator on 2017/3/20 0020.
 */
public class CheckSign {

    //请求上传接口 获取返回数据
    public static String uploadImage(File file) {
        try{
            UpYun upyun = new UpYun(Config.YPY_Bucket, Config.YPY_UserName, Config.YPY_PassWord);

            upyun.setTimeout(60);//手动设置超时时间：默认为30秒
            upyun.setApiDomain(UpYun.ED_AUTO);//选择最优的接入点

            upyun.setContentMD5(UpYun.md5(file));
            String fileName=Utils.getID(20);
            String filePath = "manage/"+fileName;

            boolean boole = upyun.writeFile( filePath , file, true);
            if(boole){
                /*return Config.YPY_URL+filePath;*/
                return filePath;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }


}
