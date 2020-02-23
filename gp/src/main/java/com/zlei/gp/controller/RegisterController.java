package com.zlei.gp.controller;

import com.zlei.gp.response.CommonResult;
import com.zlei.gp.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author ZLEI
 * @Title
 * @Description 注册账号
 * @date 2020-02-21 13:39
 */
@Controller
@Slf4j
public class RegisterController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public String registerUser(HttpSession session, String userName, String password, Map<String, Object> map) {
        CommonResult commonResult = userService.registerUser(userName, password);
        if (!commonResult.isSuccess()) {
            map.put("msg", commonResult.getMsg());
            return "/main/register";
        }
        session.setAttribute("loginUser", userName);
        return "/main/login";
    }

}
