package com.zlei.gp.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName: LoginHandlerInterceptor
 * @Description: 登录拦截器
 * @Date: 2020-01-14 15:34
 * @Author: ZhangLei
 * version: 1.0
 **/
public class LoginHandlerInterceptor implements HandlerInterceptor {

    //调用目标方法之前被拦截
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object loginUser = request.getSession().getAttribute("loginUser");
        if( loginUser != null) {
            //已经登录过,放行
            return true;
        }
        //没有登录过
        request.setAttribute("msg", "没有权限，请先登录！");
        request.getRequestDispatcher("/index.html").forward(request, response);
        return false;
    }

}
