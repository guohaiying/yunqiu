package com.yunqiu;

import com.yunqiu.util.LoggerUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by 11366 on 2017/1/9.
 */

@SpringBootApplication
public class Application {

    public static void main(String[] args){
        LoggerUtil.outDebug(Application.class,"#########开始启动后台管理系统tomcat端口监听#########");
        SpringApplication.run(Application.class,args);
        LoggerUtil.outDebug(Application.class,"#########启动后台管理系统tomcat端口监听成功#########");
    }
}
