package com.zlei.gp.entity;

import lombok.Data;

/**
 * @ClassName:
 * @Description: 消息体
 * @Date: 2020-04-01 17:28
 * @Author: ZhangLei
 * version: 1.0
 **/
@Data
public class SocketMsg {

    /**
     *  聊天类型
     *  0：群聊
     *  1：单聊
     */
    private int type;

    /**
     *  发送者
     */
    private String fromUser;

    /**
     *  接收者
     */
    private String toUser;

    /**
     *  消息内容
     */
    private String msg;

}
