package com.zlei.gp.controller;

import com.zlei.gp.entity.User;
import com.zlei.gp.response.CommonResult;
import com.zlei.gp.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @ClassName: MessageController
 * @Description: 消息控制转发
 * @Date: 2020-03-17 13:41
 * @Author: ZhangLei
 * version: 1.0
 **/
@Controller
public class MessageController {

    @Autowired
    private MessageService messageService;

    @GetMapping("/toMessage")
    public String toMessage(Map<String, Object> map, HttpSession session) {
        String userUuid = (String) session.getAttribute("userUuid");
        // 获取当前用户所接收的所有消息
        CommonResult commonResult = messageService.getAMessageByName(userUuid);
        if (!commonResult.isSuccess()) {
            map.put("msg", commonResult.getMsg());
            return "/message/showMsg";
        }
        map.put("messageList", commonResult.getData());
        return "/message/showMsg";
    }

}
