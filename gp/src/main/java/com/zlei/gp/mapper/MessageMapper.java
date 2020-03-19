package com.zlei.gp.mapper;

import com.zlei.gp.entity.Message;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @ClassName: MessageMapper
 * @Description: 消息dao
 * @Date: 2020-03-17 16:38
 * @Author: ZhangLei
 * version: 1.0
 **/
@Mapper
@Component
public interface MessageMapper {

    @Select("select message, fromUser, createTime from message_info where toUser = #{userName} order by createTime desc limit 1 group by toUser")
    public List<Message> getMessageByName(@Param("userName") String userName);

    @Select("select message, fromUser, createTime from message_info where toUser = #{userName}")
    public List<Message> getAllMessageByName(@Param("userName") String userName);

}
