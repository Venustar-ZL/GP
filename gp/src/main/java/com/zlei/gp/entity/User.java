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

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 修改时间
     */
    private String updateTime;

    /**
     * 发布商品数量
     */
    private String goodsCount;
}
