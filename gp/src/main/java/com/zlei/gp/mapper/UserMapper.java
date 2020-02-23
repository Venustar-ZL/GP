package com.zlei.gp.mapper;

import com.zlei.gp.entity.PasswordInfo;
import com.zlei.gp.entity.User;
import com.zlei.gp.entity.UserNameInfo;
import com.zlei.gp.response.CommonResult;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;


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

    @Select("select password from userinfo where userName = #{userName}")
    public PasswordInfo getPasswordByName(@Param("userName") String userName);

    @Insert("insert into userinfo (userName, password) values (#{userName}, #{password})")
    public void registerUser(@Param("userName") String userName, @Param("password") String password);

    @Select("select userName from userinfo")
    public List<UserNameInfo> getAllUserName();

}
