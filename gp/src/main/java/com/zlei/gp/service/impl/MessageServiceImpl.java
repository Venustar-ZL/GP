package com.zlei.gp.service.impl;

import com.zlei.gp.entity.Message;
import com.zlei.gp.entity.User;
import com.zlei.gp.mapper.MessageMapper;
import com.zlei.gp.mapper.UserMapper;
import com.zlei.gp.response.CommonResult;
import com.zlei.gp.response.ConstantEnum;
import com.zlei.gp.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: MessageServiceImpl
 * @Description: 消息服务实现类
 * @Date: 2020-03-17 16:42
 * @Author: ZhangLei
 * version: 1.0
 **/
@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MessageMapper messageMapper;

    @Override
    public CommonResult getAMessageByName(String userUuid) {

        User user = userMapper.getUserInfoById(userUuid);
        String userName = user.getUserName();
        List<Message> messageList = messageMapper.getMessageByName(userName);
        if (messageList == null || messageList.size() == 0) {
            return CommonResult.buildWithDatAndMessage(ConstantEnum.GLOBAL_FALL_CUSTOM, null, "暂无消息");
        }
        return CommonResult.buildWithDatAndMessage(ConstantEnum.GLOBAL_SUCCESS, messageList, "获取成功");
    }

    @Override
    public CommonResult getAllMessageByName(String userUuid) {
        User user = userMapper.getUserInfoById(userUuid);
        String userName = user.getUserName();
        List<Message> messageList = messageMapper.getAllMessageByName(userName);

        return CommonResult.buildWithDatAndMessage(ConstantEnum.GLOBAL_SUCCESS, messageList, "获取成功");
    }
}
