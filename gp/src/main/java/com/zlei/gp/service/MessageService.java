package com.zlei.gp.service;

import com.zlei.gp.response.CommonResult;

public interface MessageService {

    public CommonResult getMessageByName(String userUuid);

    public CommonResult getAllMessageByName(String userUuid);

}
