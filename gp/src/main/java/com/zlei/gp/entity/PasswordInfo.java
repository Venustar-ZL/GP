package com.zlei.gp.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author ZLEI
 * @Title
 * @Description
 * @date 2020-02-21 12:55
 */
@Data
public class PasswordInfo implements Serializable {

    /**
     * 用户Uuid
     */
    private String userUuid;

    /**
     * 密码
     */
    private String password;

}
