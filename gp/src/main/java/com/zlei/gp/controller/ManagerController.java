package com.zlei.gp.controller;

import com.zlei.gp.response.CommonResult;
import com.zlei.gp.service.ManagerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @author ZLEI
 * @Title
 * @Description
 * @date 2020-03-15 14:56
 */
@Controller
@Slf4j
public class ManagerController {

    @Autowired
    private ManagerService managerService;

    @GetMapping("/showAllUser")
    public String showAllUser(Map<String, Object> map, @RequestParam(value = "userName", required = false) String userName) {
        CommonResult commonResult = managerService.getAllUser(userName);
        if (!commonResult.isSuccess()) {
            map.put("msg", commonResult.getMsg());
            return "/manager/showUsers";
        }
        map.put("userList", commonResult.getData());
        map.put("userName", userName);
        return "/manager/showUsers";
    }

}
