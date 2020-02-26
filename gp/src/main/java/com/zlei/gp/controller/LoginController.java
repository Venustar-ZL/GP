package com.zlei.gp.controller;

import com.zlei.gp.entity.PasswordInfo;
import com.zlei.gp.response.CommonResult;
import com.zlei.gp.service.UserService;
import com.zlei.gp.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
    private UserService userService;

    @Autowired
    private RedisUtil redisUtil;

    @RequestMapping("/index")
    public String index () {
        return "/main/index";
    }

    // 登录校验
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(HttpSession session, String userName, String password, Map<String, Object> map) {
        log.info(userName);

        CommonResult commonResult = userService.getPasswordByName(userName, password);
        if (!commonResult.isSuccess()) {
            map.put("msg", commonResult.getMsg());
            return "/main/login";
        }

        // 登录成功
        // 存入redis
        //redisUtil.set("login-" + userName, userName);
        session.setAttribute("loginUser", userName);
        session.setAttribute("userUuid", commonResult.getData());
        return "redirect:/main.html";
    }

    /**
     * 退出登录
     * @return
     */
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("loginUser");
        session.invalidate();
        return "redirect:/index.html";
    }


}
