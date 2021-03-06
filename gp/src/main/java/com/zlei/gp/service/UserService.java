package com.zlei.gp.service;

import com.zlei.gp.response.CommonResult;


public interface UserService {

    public CommonResult registerUser(String userName, String password);

    public CommonResult getPasswordByName(String userName, String password);

    public CommonResult getUserInfo(String userUuid);

    public CommonResult updateUserInfo(String userName, String password, String userUuid);

    public CommonResult deleteUserInfo(String userUuid);

    public CommonResult getUserInfoByName(String userName);

    public CommonResult getAllUserNotMe(String userUuid);

    public CommonResult getLoginStatusByName(String userName);

    public CommonResult updateLoginStatusToDown(String userName);


}
