package com.chatRobot.interceptor;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/*登录认证拦截器*/
/*需求：1.用户请求url
* 2.拦截器进行拦截校验
* 如果请求的url是公开地址（无需登录即可访问的url），让放行
* 如果用户session不存在，跳转到登录页面
* 如果用户session存在，让放行*/

public class LoginInterceptor implements HandlerInterceptor {

    //进入Handler方法之前执行
    //用于身份认证，身份授权(比如身份认证，如果通过表示当前用户没有登录，需要此方法拦截不再向下执行）
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取请求的url
        String url = request.getRequestURI();
        //判断url是否为公开地址（实际使用时将公开地址配置在配置文件中）
        //这里公开地址是登录提交的地址
        if(url.indexOf("login.do")>=0) {
            //如果进行登录提交，放行
            return true;
        }
        //判断session
        HttpSession session = request.getSession();
        //从session中取出用户身份信息
        String username = (String) session.getAttribute("username");
        if(username!=null) {
            //身份存在，放行
            return true;
        }
        //执行到这里表示用户身份需要认证，跳转登录页面
        request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request,response);

        //return false表示拦截，不向下执行
        //true表示放行
        return false;
    }

    //这个方法只会在当前这个Interceptor的preHandle方法返回值为true的时候才会执行。
    //进入Handler方法之后，返回modelAndView之前执行
    //应用场景从modelAndView出发：将公用的模型数据（比如菜单导航）再这里传到视图，也可以再这里同意指定视图
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    //该方法也是需要当前对应的Interceptor的preHandle方法的返回值为true时才会执行。
    //执行Handler完成执行此方法
    //应用场景：同意异常处理，同意日志处理（清理资源）
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
