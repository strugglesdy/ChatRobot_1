package com.chatRobot.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HandlerInterceptor1 implements HandlerInterceptor {

    //进入Handler方法之前执行
    //用于身份认证，身份授权(比如身份认证，如果通过表示当前用户没有登录，需要此方法拦截不再向下执行）
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //return false表示拦截，不向下执行
        //true表示放行
        return true;
    }

    //进入Handler方法之后，返回modelAndView之前执行
    //应用场景从modelAndView出发：将公用的模型数据（比如菜单导航）再这里传到视图，也可以再这里同意指定视图
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle1");
    }

    //执行Handler完成执行此方法
    //应用场景：同意异常处理，同意日志处理
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion1");
    }
}
