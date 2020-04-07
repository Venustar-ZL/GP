package com.zlei.gp.controller;

import com.zlei.gp.entity.User;
import com.zlei.gp.response.CommonResult;
import com.zlei.gp.service.MessageService;
import com.zlei.gp.service.UserService;
import com.zlei.gp.service.WebSocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ConcurrentModificationException;
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

    @Autowired
    private UserService userService;

    @GetMapping("/toMessage")
    public String toMessage(Map<String, Object> map, HttpSession session) {
        String userUuid = (String) session.getAttribute("userUuid");
        // 获取当前用户所接收的所有消息
        CommonResult commonResult = messageService.getMessageByName(userUuid);
        if (!commonResult.isSuccess()) {
            map.put("msg", commonResult.getMsg());
            return "/message/showMsg";
        }
        map.put("messageList", commonResult.getData());
        return "/message/showMsg";
    }

    @GetMapping("/toSendMessage")
    public String toSendMessage() {
        return "/message/sendMsg";
    }

    @PostMapping("/sendMessage")
    public String sendMessage(Map<String, Object> map, HttpSession session, @RequestParam("message") String message, @RequestParam("userName") String userName){
        String toUser = userName;
        String userUuid = (String) session.getAttribute("userUuid");
        CommonResult userCommonResult = userService.getUserInfo(userUuid);
        User user = (User)userCommonResult.getData();
        String fromUser = user.getUserName();
        CommonResult commonResult = messageService.insertMessage(message, fromUser, toUser);
        if (!commonResult.isSuccess()) {
            map.put("msg", commonResult.getMsg());
            return "/message/sendMsg";
        }
        return "/message/sendMsg";
    }

    @GetMapping("/toChat")
    public String toChat() {
        return "/message/message";
    }

//    @GetMapping("/connect")
//    public String connect() {
//        webSocketService.onOpen(session);
//        return "message/chat";
//    }

}
