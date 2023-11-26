package com.demos.config;

import com.demos.interceptor.LoginnInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

//登录拦截器的注册
@Configuration//加载当前的拦截器并进行注册
public class LoginInterceptorConfig implements WebMvcConfigurer {
    //配置拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //创建自定义拦截器对象
        HandlerInterceptor interceptor=new LoginnInterceptor();

        //配置白名单，存放在一个List集合里面
//        List<String> patterns=new ArrayList<>();
//        patterns.add("/bootstrap3/**");
//        patterns.add("/css/**");
//        patterns.add("/images/**");
//        patterns.add("/js/**");
//        patterns.add("/web/register.html");
//        patterns.add("/web/login.html");
//        patterns.add("/web/index.html");
//        patterns.add("/web/product.html");
//        patterns.add("/users/reg");
//        patterns.add("/users/login");
        //完成拦截器的注册
        registry.addInterceptor(interceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/bootstrap3/**",
                                     "/css/**",
                                     "/images/**",
                                     "/js/**",
                                     "/web/register.html",
                                     "/web/index.html",
                                     "/web/product.html",
                                     "/users/reg",
                                     "/users/login",
                                     "/web/login.html");

    }

}
