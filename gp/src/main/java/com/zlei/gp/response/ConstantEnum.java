package com.zlei.gp.response;

import com.zlei.gp.annotation.CodeEntity;

/**
 * 异常枚举
 *
 * @author ZhengYu
 * @version 1.0 2019/8/22 13:42
 **/
public enum ConstantEnum implements CodeEnumInterface {

    /**
     * 异常枚举
     */
    @CodeEntity(
            success = true,
            errCode = 0,
            msg = "操作成功"
    )
    GLOBAL_SUCCESS,
    @CodeEntity(
            success = false,
            errCode = 1,
            msg = "服务器开小差了，请稍后再试!"
    )
    GLOBAL_FAIL,
    @CodeEntity(
            success = false,
            errCode = 2,
            msg = "用户未登录!"
    )
    GLOBAL_NO_LOGIN,
    @CodeEntity(
            success = false,
            errCode = 3,
            msg = "操作太频繁，请稍后再试!"
    )
    GLOBAL_POST_FAST,
    @CodeEntity(
            success = false,
            errCode = 4,
            msg = "服务繁忙，请稍后再试！"
    )
    GLOBAL_FALL_BACK,
    @CodeEntity(
            success = false,
            errCode = 5,
            msg = "自定义错误信息！"
    )
    GLOBAL_FALL_CUSTOM,
    @CodeEntity(
            success = false,
            errCode = 6,
            msg = "人脸识别服务器网络不通"
    )
    GLOBAL_FACE_LIBRARY_NETWORK_FAIL,
    @CodeEntity(
            success = false,
            errCode = 100,
            msg = "参数不正确"
    )
    GLOBAL_PARAM_FAIL,

    @CodeEntity(
            success = false,
            errCode = 101,
            msg = "数据库操作失败"
    )
    GLOBAL_DATASOURCE_FAIL,

    @CodeEntity(
            success = false,
            errCode = 102,
            msg = "数据中心断开连接"
    )
    DATASOURCE_LOST,

    @CodeEntity(
            success = false,
            errCode = 7000,
            msg = "令牌无效"
    )
    INVALID_TOKEN,
    @CodeEntity(
            success = false,
            errCode = 104,
            msg = "人脸库错误"
    )
    GLOBAL_FALL_FACELIB;

    private ConstantEnum() {
    }


}
