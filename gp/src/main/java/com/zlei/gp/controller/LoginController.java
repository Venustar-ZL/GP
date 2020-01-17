package com.zlei.gp.controller;

import com.zlei.gp.response.CommonResult;
import com.zlei.gp.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @ClassName: LoginController
 * @Description: 登录
 * @Date: 2020-01-13 14:41
 * @Author: ZhangLei
 * version: 1.0
 **/
@Controller
@Slf4j
public class LoginController {

    @Autowired
    private LoginService loginService;

    @RequestMapping("/index")
    public String index () {
        return "/main/index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(HttpSession session, String username, String password, Map<String, Object> map) {
        log.info(username);
        //判断用户名不为空，并且密码为123，则登录 成功
        if (!StringUtils.isEmpty(username)&& "123".equals(password)) {
            session.setAttribute("loginUser", username);
            //登录成功
            //重定向 redirect：可以重定向到任意一个请求中（包括其他项目），地址栏改变
            return "redirect:/main.html";
        }

        //登录失败
        map.put("msg", "用户名或密码错误");

        return "/main/login";
    }

    /**
     * 退出登录
     * @return
     */
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        //1. 清空session中的用户信息
        session.removeAttribute("loginUser");
        //2. 再将session进行注销
        session.invalidate();
        //3. 返回登录页面
        return "redirect:/index.html";
    }

    /**
     * 获取账号详情
     * @return
     */
    public CommonResult getUesrInfo() {
        return loginService.getUserInfo();
    }

}
