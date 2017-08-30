package com.chatRobot.exception;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/*
* 自定义异常处理器，该处理器需要实现 HandlerExceptionResolver 接口
* ex 系统抛出的异常*/
public class CustomExceptionResolver implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest request,
             HttpServletResponse response, Object handler, Exception ex) {
        //handler就是处理器适配器要执行Handler对象（只有method）
        String msg = "";
        //如果捕捉到了自定义异常，将自定义异常的错误信息取出
        if(ex instanceof CustomException) {        //ex是一个CustomException类型对象
            msg = ((CustomException)ex).getMessage();
            //如果捕捉到了运行时异常，那么就给出一个错误信息“未知错误”
        }else{
            msg = "未知错误";
        }
        ModelAndView mv = new ModelAndView();
        mv.addObject("msg", msg);
        mv.setViewName("error");
        return mv;
    }
}
