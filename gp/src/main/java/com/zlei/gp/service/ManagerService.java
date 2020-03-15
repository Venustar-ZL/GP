package com.zlei.gp.service;

import com.zlei.gp.response.CommonResult;

public interface ManagerService {

    public CommonResult getManagerLogin(String managerName, String password);

    public CommonResult getAllUser(String userName);

}
