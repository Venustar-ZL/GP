package com.zlei.gp.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @ClassName: PasswordUtil
 * @Description: 密码工具类
 * @Date: 2020-01-16 15:56
 * @Author: ZhangLei
 * version: 1.0
 **/
public class PasswordUtil {

    /**
     * 密码加密方法
     */
    private static PasswordEncoder passwordEncode = new BCryptPasswordEncoder();


    /**
     * 密码加密
     * @param password  原密码
     * @return      加密后的密码
     */
    public static  String passwordEncode(String password){
        return  passwordEncode.encode(password);
    }

    /**
     * 密码匹配
     * @param decodePass            编码前的密码
     * @param encodedPassword       编码后的密码（数据库存储的密码）
     * @return
     */
    public static  Boolean  matches(String decodePass,String encodedPassword){
        return passwordEncode.matches(decodePass,encodedPassword);
    }

}
