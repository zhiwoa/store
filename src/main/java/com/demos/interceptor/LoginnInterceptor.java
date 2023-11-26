package com.demos.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//登录页面的拦截器
public class LoginnInterceptor implements HandlerInterceptor  {
    //检测全局session对象中是否有uid数据，如果有则方向，如果没有重定向到index中
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        // 获取session对象
        Object obj = request.getSession().getAttribute("uid");
        if(obj == null){//说明没有登录过系统
            response.sendRedirect("/web/login.html");
            //结束后续的调用
            return  false;
        }
        System.out.println("当前访问页面："+request.getRequestURI());

        return true;
    }
}
