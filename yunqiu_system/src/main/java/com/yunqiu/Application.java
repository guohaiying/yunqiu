package com.yunqiu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * msg：启动API服务
 * time：2016-12-30
 * user：武尊
 * version：1.0
 */

@SpringBootApplication
@ServletComponentScan  //缓存的配置注解
public class Application extends WebMvcConfigurerAdapter {


    public static void main(String[] args){
        SpringApplication.run(Application.class,args);
    }
}
