//package com.zlei.gp.handler;
//
//import com.zlei.gp.response.CommonResult;
//import com.zlei.gp.response.ConstantEnum;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//
//import java.rmi.ServerException;
//
///**
// * @author ZLEI
// * @Title
// * @Description
// * @date 2020-02-21 12:58
// */
//@Slf4j
//@RestControllerAdvice(value = "com.zlei.gp.controller")
//public class ExceptionHandler {
//
//    @org.springframework.web.bind.annotation.ExceptionHandler(value = ServerException.class)
//    public CommonResult serverExceptionHandler(ServerException s){
//        log.error("服务调用失败：{}",s.getMessage(), s);
//        return CommonResult.build(false, s.getErrCode(), s.getMessage());
//    }
//
//    @org.springframework.web.bind.annotation.ExceptionHandler(value = ParamException.class)
//    public CommonResult paramExceptionHandler(ParamException p){
//        log.error("参数错误：{}", p.getMessage(), p);
//        return CommonResult.buildWithDatAndMessage(ConstantEnum.GLOBAL_PARAM_FAIL, null, p.getMessage());
//    }
//
//    @org.springframework.web.bind.annotation.ExceptionHandler(value = Exception.class)
//    public CommonResult exceptionHandler(Exception e){
//        log.error("发生错误：{}", e.getMessage(), e);
//        return CommonResult.buildWithDatAndMessage(ConstantEnum.GLOBAL_FALL_CUSTOM, null, e.getMessage());
//    }
//
//}
