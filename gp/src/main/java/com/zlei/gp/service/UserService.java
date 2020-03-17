package com.zlei.gp.service;

import com.zlei.gp.response.CommonResult;


public interface UserService {

    public CommonResult registerUser(String userName, String password);

    public CommonResult getPasswordByName(String userName, String password);

    public CommonResult getUserInfo(String userUuid);

    public CommonResult updateUserInfo(String userName, String password, String userUuid);

    public CommonResult deleteUserInfo(String userUuid);


}
