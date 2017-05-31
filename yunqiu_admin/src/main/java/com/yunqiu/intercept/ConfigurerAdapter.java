package com.yunqiu.intercept;

import com.yunqiu.intercept.rules.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 拦截器适配器
 * @author 武尊
 * @time 2017-01-12
 * @version 1.0
 */

@Configuration
public class ConfigurerAdapter extends WebMvcConfigurerAdapter {
    /*###################注册拦截器#########################*/
    //登录验证拦截器注册
    @Bean
    public HandlerInterceptor getLoginInterceptor(){
        return new LoginInterceptor();
    }

    /*####################加载拦截器#######################*/
    //addPathPatterns 需要拦截的url
    //excludePathPatterns 不需要拦截，直接放行的url
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        System.out.println("###################################初始化");
        //登录验证拦截器加载并配置拦截路径
        registry.addInterceptor(getLoginInterceptor()).addPathPatterns("/view*//**").excludePathPatterns("/view/login")
         .addPathPatterns("/perm*//**").excludePathPatterns("/perm/login");
         super.addInterceptors(registry);
    }
}
