package com.yunqiu;

import com.yunqiu.general.interceptor.LoginInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * msg：启动API服务
 * time：2016-12-30
 * user：武尊
 * version：1.0
 */

@SpringBootApplication
@ServletComponentScan  //缓存的配置注解
@Configuration //拦截器
@EnableEurekaClient //向服务注册中心注册服务
@EnableDiscoveryClient
public class Application extends WebMvcConfigurerAdapter {

    /**
     * 提前加载拦截器，解决 @Autowired对象为null问题
     * @return
     */
    @Bean
    public HandlerInterceptor getMyInterceptor(){
        return new LoginInterceptor();
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 多个拦截器组成一个拦截器链
        // addPathPatterns 用于添加拦截规则
        // excludePathPatterns 用于排除拦截
        registry.addInterceptor(getMyInterceptor()).addPathPatterns("/center/inte/**").addPathPatterns("/fans/inte/**");
        super.addInterceptors(registry);
    }

    public static void main(String[] args){
        SpringApplication.run(Application.class,args);
    }
}
