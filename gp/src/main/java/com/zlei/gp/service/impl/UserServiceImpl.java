package com.zlei.gp.service.impl;

import com.zlei.gp.entity.Goods;
import com.zlei.gp.entity.PasswordInfo;
import com.zlei.gp.entity.User;
import com.zlei.gp.entity.UserNameInfo;
import com.zlei.gp.mapper.GoodsMapper;
import com.zlei.gp.mapper.UserMapper;
import com.zlei.gp.response.CommonResult;
import com.zlei.gp.response.ConstantEnum;
import com.zlei.gp.service.UserService;
import com.zlei.gp.utils.PasswordUtil;
import com.zlei.gp.utils.TimeUtil;
import com.zlei.gp.utils.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author ZLEI
 * @Title
 * @Description
 * @date 2020-02-21 20:00
 */
@Service
@SuppressWarnings("all")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private GoodsMapper goodsMapper;

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
        String userUuid = passwordInfo.getUserUuid();
        boolean passwordFlag = PasswordUtil.matches(password, userPassword);
        if (passwordFlag == false) {
            return CommonResult.buildWithDatAndMessage(ConstantEnum.GLOBAL_FALL_CUSTOM, null, "密码错误");
        }

        userMapper.updateLoginStatusToUp(userName);

        return CommonResult.buildWithData(ConstantEnum.GLOBAL_SUCCESS, userUuid);
    }

    @Override
    public CommonResult registerUser(String userName, String password) {

        // 判断用户名是否重复
        List<UserNameInfo> userNameInfos = userMapper.getAllUserName();
        if (userNameInfos.contains(userName)) {
            return CommonResult.buildWithDatAndMessage(ConstantEnum.GLOBAL_FALL_CUSTOM, null, "用户名已存在");
        }

        String userUuid = UuidUtil.getUUID();
        // 获取当前时间
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String createTime = sdf.format(date);

        // 对密码进行加密处理
        password = PasswordUtil.passwordEncode(password);
        userMapper.registerUser(userUuid, userName, password, createTime);

        return CommonResult.buildWithData(ConstantEnum.GLOBAL_SUCCESS, userUuid);
    }

    @Override
    public CommonResult getUserInfo(String userUuid) {
        // 获取用户详情
        User user = userMapper.getUserInfoById(userUuid);
        return CommonResult.buildWithData(ConstantEnum.GLOBAL_SUCCESS, user);
    }

    @Override
    public CommonResult updateUserInfo(String userName, String password, String userUuid) {

        // 用户名和密码不允许为空
        if (userName == null || userName.length() == 0) {
            return CommonResult.buildWithDatAndMessage(ConstantEnum.GLOBAL_FALL_CUSTOM, null, "用户名不允许为空");
        }
        if (password == null || password.length() == 0) {
            return CommonResult.buildWithDatAndMessage(ConstantEnum.GLOBAL_FALL_CUSTOM, null, "密码不允许为空");
        }

        // 判断用户名是否重复(先获取原来的用户信息比较)
        User user = userMapper.getUserInfoById(userUuid);
        if (!user.getUserName().equals(userName)) {
            // 用户名改变
            int userNameCount = userMapper.getUseNameCount(userName);
            if (userNameCount >= 1) {
                return CommonResult.buildWithDatAndMessage(ConstantEnum.GLOBAL_FALL_CUSTOM, null, "用户名已存在");
            }
        }

        // 密码加密
        password = PasswordUtil.passwordEncode(password);
        String updateTime = TimeUtil.getCurrentTime();
        userMapper.upadteUserInfo(userName, password, updateTime, userUuid);
        goodsMapper.updateUserInGoods(userName, userUuid);
        return CommonResult.buildWithData(ConstantEnum.GLOBAL_SUCCESS, null);
    }

    @Override
    public CommonResult deleteUserInfo(String userUuid) {
        userMapper.deleteUser(userUuid);
        goodsMapper.deleteAllGoodsByUser(userUuid);
        return CommonResult.buildWithData(ConstantEnum.GLOBAL_SUCCESS, null);
    }

    @Override
    public CommonResult getUserInfoByName(String userName) {
        User user = userMapper.getUserInfoByName(userName);
        if (user == null) {
            return CommonResult.buildWithDatAndMessage(ConstantEnum.GLOBAL_FALL_CUSTOM, null, "用户名不存在");
        }
        return CommonResult.buildWithData(ConstantEnum.GLOBAL_SUCCESS, user);
    }

    @Override
    public CommonResult getAllUserNotMe(String userUuid) {
        List<User> userList = userMapper.getAllUserNotMe(userUuid);
        if (userList == null) {
            return CommonResult.buildWithDatAndMessage(ConstantEnum.GLOBAL_FALL_CUSTOM, null, "暂无用户信息");
        }
        return CommonResult.buildWithData(ConstantEnum.GLOBAL_SUCCESS, userList);
    }

    @Override
    public CommonResult getLoginStatusByName(String userName) {
        int isLogin = userMapper.getLoginStatusByName(userName);
        if (isLogin == 1) {
            return CommonResult.buildWithDatAndMessage(ConstantEnum.GLOBAL_FALL_CUSTOM, null, "账号已登陆");
        }
        return CommonResult.buildWithData(ConstantEnum.GLOBAL_SUCCESS, null);
    }

    @Override
    public CommonResult updateLoginStatusToDown(String userName) {
        userMapper.updateLoginStatusToDown(userName);
        return null;
    }

}
