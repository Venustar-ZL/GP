package com.zlei.gp.controller;

import com.zlei.gp.entity.Goods;
import com.zlei.gp.entity.User;
import com.zlei.gp.mapper.GoodsMapper;
import com.zlei.gp.mapper.UserMapper;
import com.zlei.gp.response.CommonResult;
import com.zlei.gp.service.GoodsService;
import com.zlei.gp.service.ManagerService;
import com.zlei.gp.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: UserController
 * @Description: 用户控制
 * @Date: 2020-03-16 09:58
 * @Author: ZhangLei
 * version: 1.0
 **/
@Controller
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private ManagerService managerService;

    @GetMapping("/getUserInfo/{userUuid}")
    public String getUserInfo(Map<String, Object> map, @PathVariable("userUuid") String userUuid) {
        // 获取用户详情
        CommonResult commonResult = userService.getUserInfo(userUuid);
        if (!commonResult.isSuccess()) {
            return "/manager/showUsers";
        }
        map.put("user", commonResult.getData());

        // 获取用户所上传的商品
        CommonResult goodsCommonResult = goodsService.getGoodsInfoByUser(userUuid);
        if (!goodsCommonResult.isSuccess()) {
            return "/manager/showUsers";
        }
        map.put("goodsList", goodsCommonResult.getData());
        return "/manager/userInfo";
    }

    /**
    * @Description: 转发到获取所有用户界面
    * @Param: [map]
    * @return: java.lang.String
    * @Author: ZhangLei
    * @Date: 2020/3/16
    */
    @GetMapping("/toShowAllUser")
    public String toShowAllUser(Map<String, Object> map) {
        CommonResult commonResult = managerService.getAllUser(null);
        if (!commonResult.isSuccess()) {
            map.put("msg", commonResult.getMsg());
            return "/manager/showUsers";
        }
        map.put("userList", commonResult.getData());
        return "/manager/showUsers";
    }

    @GetMapping("/toUpdateUserInfo")
    public String toUpdateUserInfo(Map<String, Object> map, HttpSession session) {
        String userUuid = (String)session.getAttribute("userUuid");
        CommonResult commonResult = userService.getUserInfo(userUuid);
        if (!commonResult.isSuccess()) {
            map.put("msg", commonResult.getMsg());
            return "/manager/showUsers";
        }
        map.put("user", commonResult.getData());
        map.put("userUuid", userUuid);
        return "/manager/updateUser";
    }

    @PostMapping("/updateUserInfo/{userUuid}")
    public String updateUserInfo(HttpSession session, Map<String, Object> map, String userName, String password, @PathVariable("userUuid") String userUuid) {
        CommonResult commonResult = userService.updateUserInfo(userName, password, userUuid);
        if (!commonResult.isSuccess()) {
            map.put("msg", commonResult.getMsg());
            return "/manager/updateUser";
        }

        CommonResult allUser = managerService.getAllUser(null);
        if (!allUser.isSuccess()) {
            map.put("msg", allUser.getMsg());
            return "/manager/showUsers";
        }
        map.put("userList", allUser.getData());
        session.setAttribute("loginUser", userName);
        return "/manager/showUsers";
    }

    @GetMapping("/deleteUpdateUserInfo/{userUuid}")
    public String deleteUserInfo(Map<String, Object> map, @PathVariable("userUuid") String userUuid) {
        CommonResult commonResult = userService.deleteUserInfo(userUuid);
        if (!commonResult.isSuccess()) {
            map.put("msg", "删除失败");
            return "/manager/showUsers";
        }
        CommonResult allUser = managerService.getAllUser(null);
        if (!allUser.isSuccess()) {
            map.put("msg", allUser.getMsg());
            return "/manager/showUsers";
        }
        map.put("userList", allUser.getData());

        return "/manager/showUsers";
    }

}
