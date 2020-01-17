package com.zlei.gp.service.impl;

import com.zlei.gp.entity.User;
import com.zlei.gp.mapper.UserMapper;
import com.zlei.gp.response.CommonResult;
import com.zlei.gp.response.ConstantEnum;
import com.zlei.gp.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName: LoginServiceImpl
 * @Description: 登录逻辑判断
 * @Date: 2020-01-16 15:16
 * @Author: ZhangLei
 * version: 1.0
 **/
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public CommonResult getUserInfo() {
        User user = userMapper.getUserInfo();
        return CommonResult.buildWithData(ConstantEnum.GLOBAL_SUCCESS, user);
    }
}
