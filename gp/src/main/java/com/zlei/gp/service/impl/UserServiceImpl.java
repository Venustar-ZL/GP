package com.zlei.gp.service.impl;

import com.zlei.gp.entity.PasswordInfo;
import com.zlei.gp.entity.User;
import com.zlei.gp.entity.UserNameInfo;
import com.zlei.gp.mapper.UserMapper;
import com.zlei.gp.response.CommonResult;
import com.zlei.gp.response.ConstantEnum;
import com.zlei.gp.service.UserService;
import com.zlei.gp.utils.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ZLEI
 * @Title
 * @Description
 * @date 2020-02-21 20:00
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    // 根据账号名称获得账号密码并校验
    @Override
    public CommonResult getPasswordByName(String userName, String password) {

        if (userName == null || userName.length() == 0) {
            return CommonResult.buildWithDatAndMessage(ConstantEnum.GLOBAL_FALL_CUSTOM, null, "用户名不能为空");
        }

        if (password == null || password.length() == 0) {
            return CommonResult.buildWithDatAndMessage(ConstantEnum.GLOBAL_FALL_CUSTOM, null, "密码不能为空");
        }

        // 判断用户名是否存在
        List<UserNameInfo> userNameInfos = userMapper.getAllUserName();
        List<String> names = new ArrayList<>();
        for (UserNameInfo userNameInfo : userNameInfos) {
            names.add(userNameInfo.getUserName());
        }

        if (!names.contains(userName)) {
            return CommonResult.buildWithDatAndMessage(ConstantEnum.GLOBAL_FALL_CUSTOM, null, "用户名不存在");
        }

        PasswordInfo passwordInfo = userMapper.getPasswordByName(userName);
        String userPassword = passwordInfo.getPassword();
        boolean passwordFlag = PasswordUtil.matches(password, userPassword);
        if (passwordFlag == false) {
            return CommonResult.buildWithDatAndMessage(ConstantEnum.GLOBAL_FALL_CUSTOM, null, "密码错误");
        }

        return CommonResult.buildWithData(ConstantEnum.GLOBAL_SUCCESS, null);
    }

    @Override
    public CommonResult registerUser(String userName, String password) {

        // 判断用户名是否重复
        List<UserNameInfo> userNameInfos = userMapper.getAllUserName();
        if (userNameInfos.contains(userName)) {
            return CommonResult.buildWithDatAndMessage(ConstantEnum.GLOBAL_FALL_CUSTOM, null, "用户名已存在");
        }

        // 对密码进行加密处理
        password = PasswordUtil.passwordEncode(password);
        userMapper.registerUser(userName, password);

        return CommonResult.buildWithData(ConstantEnum.GLOBAL_SUCCESS, null);
    }

}
