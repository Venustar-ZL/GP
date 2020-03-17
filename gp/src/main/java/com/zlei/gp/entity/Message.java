package com.zlei.gp.entity;

import lombok.Data;

/**
 * @ClassName: Message
 * @Description: 消息
 * @Date: 2020-03-17 16:33
 * @Author: ZhangLei
 * version: 1.0
 **/
@Data
public class Message {

    /**
     * 消息
     */
    private String message;

    /**
     * 发送人
     */
    private String fromUser;

    /**
     * 接收人
     */
    private String toUser;

    /**
     * 发送时间
     */
    private String createTime;

}
