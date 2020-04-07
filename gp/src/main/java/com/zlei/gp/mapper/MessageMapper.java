package com.zlei.gp.mapper;

import com.zlei.gp.entity.Message;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import javax.websocket.server.PathParam;
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

    @Select("select message, fromUser, createTime from message_info where toUser = #{userName} and isRead = 0 order by fromUser")
    public List<Message> getMessageByName(@Param("userName") String userName);

    @Select("select message, fromUser, createTime from message_info where toUser = #{userName}")
    public List<Message> getAllMessageByName(@Param("userName") String userName);

    @Insert("insert into message_info (message, fromUser, toUser, createTime, isRead) values (#{message}, #{fromUser}, #{toUser}, #{createTime}, #{isRead})")
    public void insertMessage(@Param("message") String message, @Param("fromUser") String fromUser, @Param("toUser") String toUser, @Param("createTime") String createTime, @Param("isRead") int isRead);

}
