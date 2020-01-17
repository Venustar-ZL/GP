package com.zlei.gp.mapper;

import com.zlei.gp.entity.User;
import org.springframework.stereotype.Service;

/**
 * @ClassName: UserMapper
 * @Description: 用户
 * @Date: 2020-01-16 16:20
 * @Author: ZhangLei
 * version: 1.0
 **/
@Mapper
public interface UserMapper {

    public User getUserInfo();

}
