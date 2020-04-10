//package com.zlei.gp.component;
//
//import com.zlei.gp.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.PreDestroy;
//import javax.servlet.http.HttpSession;
//
///**
// * @ClassName: EndGlobalKeyBoard
// * @Description: 程序关闭之前要执行的方法
// * @Date: 2020-04-10 16:24
// * @Author: ZhangLei
// * version: 1.0
// **/
//@Component
//public class EndGlobalKeyBoard {
//
//    @Autowired
//    private UserService userService;
//
//    @PreDestroy
//    public void destory(HttpSession session) throws Exception{
//        String userName = (String)session.getAttribute("loginUser");
//        userService.updateLoginStatusToDown(userName);
//    }
//
//}
