package com.zlei.gp.service.impl;

import com.zlei.gp.entity.Manager;
import com.zlei.gp.entity.ManagerNameInfo;
import com.zlei.gp.entity.User;
import com.zlei.gp.mapper.ManagerMapper;
import com.zlei.gp.mapper.UserMapper;
import com.zlei.gp.response.CommonResult;
import com.zlei.gp.response.ConstantEnum;
import com.zlei.gp.service.ManagerService;
import com.zlei.gp.utils.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ZLEI
 * @Title
 * @Description
 * @date 2020-03-15 14:43
 */
@Service
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    private ManagerMapper managerMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public CommonResult getManagerLogin(String managerName, String password) {

        Manager manager = managerMapper.getManagerInfo(managerName);
        if (manager == null) {
            return CommonResult.buildWithDatAndMessage(ConstantEnum.GLOBAL_FALL_CUSTOM, null, "管理员不存在");
        }
//        String managerPassword = manager.getPassword();
//        boolean passwordFlag = PasswordUtil.matches(password, managerPassword);
//        if (passwordFlag == false) {
//            return CommonResult.buildWithDatAndMessage(ConstantEnum.GLOBAL_FALL_CUSTOM, null, "密码错误");
//        }
        if (!manager.getPassword().equals(password)) {
            return CommonResult.buildWithDatAndMessage(ConstantEnum.GLOBAL_FALL_CUSTOM, null, "密码错误");
        }
        return CommonResult.buildWithData(ConstantEnum.GLOBAL_SUCCESS, manager.getManagerId());
    }

    @Override
    public CommonResult getAllUser(String userName) {
        List<User> userList = null;

        if (userName == null || userName.length() == 0) {
            userList = userMapper.getAllUser();
        }
        else {
            userList = userMapper.getAllUserByName(userName);
        }
        if (userList.size() == 0) {
            return CommonResult.buildWithDatAndMessage(ConstantEnum.GLOBAL_FALL_CUSTOM, null, "暂无用户信息");
        }

        return CommonResult.buildWithDatAndMessage(ConstantEnum.GLOBAL_SUCCESS, userList, "查询成功");
    }
}
