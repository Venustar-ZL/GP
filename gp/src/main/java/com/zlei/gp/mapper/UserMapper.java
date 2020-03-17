package com.zlei.gp.mapper;

import com.zlei.gp.entity.PasswordInfo;
import com.zlei.gp.entity.User;
import com.zlei.gp.entity.UserNameInfo;
import com.zlei.gp.response.CommonResult;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

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

    @Select("select password, userUuid from user_info where userName = #{userName}")
    public PasswordInfo getPasswordByName(@Param("userName") String userName);

    @Insert("insert into user_info (userUuid, userName, password, createTime) values (#{userUuid}, #{userName}, #{password}, #{createTime})")
    public void registerUser(@Param("userUuid") String userUuid, @Param("userName") String userName, @Param("password") String password, @Param("createTime") String createTime);

    @Select("select userName from user_info")
    public List<UserNameInfo> getAllUserName();

    @Select("select userUuid, userName, createTime, updateTime, goodsCount from user_info where userUuid = #{userUuid}")
    public User getUserInfoById(@Param("userUuid") String userUuid);

    @Select("select userUuid, userName, createTime, updateTime, goodsCount from user_info where userName = #{userName}")
    public User getUserInfoByName(@Param("userName") String userName);

    @Select("select userUuid, userName, createTime, updateTime, goodsCount from user_info")
    public List<User> getAllUser();

    @Select("select userUuid, userName, createTime, updateTime, goodsCount from user_info where userName = #{userName}")
    public List<User> getAllUserByName(@Param("userName") String userName);

    @Update("update user_info set goodsCount = #{goodsCount} where userUuid = #{userUuid}")
    public void updateCount(@Param("goodsCount") Integer goodsCount, @Param("userUuid") String userUuid);

    @Update("update user_info set userName = #{userName}, password = #{password} where userUuid = #{userUuid}")
    public void upadteUserInfo(@Param("userName") String userName, @Param("password") String password, @Param("userUuid") String userUuid);

    @Select("select count(*) from user_info where userName = #{userName}")
    public Integer getUseNameCount(@Param("userName") String userName);

    @Delete("delete from user_info where userUuid = #{userUuid}")
    public void deleteUser(@Param("userUuid") String userUuid);
}
