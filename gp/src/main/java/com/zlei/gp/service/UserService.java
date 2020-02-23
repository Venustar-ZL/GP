package com.zlei.gp.service;

import com.zlei.gp.response.CommonResult;


public interface UserService {

    public CommonResult registerUser(String userName, String password);

    public CommonResult getPasswordByName(String userName, String password);


}
