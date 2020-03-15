package com.zlei.gp.entity;

import lombok.Data;

/**
 * @author ZLEI
 * @Title
 * @Description
 * @date 2020-03-15 14:31
 */
@Data
public class Manager {

    /**
     * 管理员Id
     */
    private String managerId;

    /**
     * 管理员姓名
     */
    private String managerName;

    /**
     * 管理员密码
     */
    private String password;

    /**
     * 管理员Uuid
     */
    private String managerUuid;

}
