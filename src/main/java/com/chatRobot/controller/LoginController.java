package com.chatRobot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    //登录
    @RequestMapping("/login")
    public String login(HttpSession session,String username,String password) throws Exception{
        //调用Service进行用户身份验证
        //....

        //在session中保存用户是否信息
        session.setAttribute("username",username);
        //重定向到商品列表页面
        return "redirect:/items/queryItems.do";
    }

    //退出
    @RequestMapping("/logout")
    public String logout(HttpSession session) throws Exception {
        //清除session
        session.invalidate();

        //重定向到商品列页面
        return "redirect:/items/queryItems.do";

    }
}
