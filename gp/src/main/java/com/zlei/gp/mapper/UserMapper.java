package com.zlei.gp.mapper;

import com.zlei.gp.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;


/**
 * @ClassName: UserMapper
 * @Description: 用户
 * @Date: 2020-01-16 16:20
 * @Author: ZhangLei
 * version: 1.0
 **/
@Mapper
@Component
public interface UserMapper{

    @Select("select * from userinfo")
    public User getUserInfo();

}
