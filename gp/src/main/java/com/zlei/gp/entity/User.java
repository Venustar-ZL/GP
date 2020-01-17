package com.zlei.gp.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName: User
 * @Description: 用户
 * @Date: 2020-01-16 16:12
 * @Author: ZhangLei
 * version: 1.0
 **/
@Data
public class User implements Serializable {

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;
}
