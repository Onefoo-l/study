package com.onefool.common.exception;

import com.onefool.common.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @className: GlobalExceptionHandler
 * @author: Kevin
 * @date: 2023/3/21
 **/
@Slf4j
@RestControllerAdvice //标识他是一个全局异常处理器
public class GlobalExceptionHandler {

    //1. 方法用来针对 系统的异常处理 相当于代替之前的controller来给前端返回了
    @ExceptionHandler(Exception.class) //标识当出现系统的异常的时候才会执行这个方法
    public Result handlerSystemException(Exception e){
      log.error("后台出现异常，原因{}",e.getMessage());
      return Result.errorMessage("你的网络有异常");
    }

    //2.方法用来针对 业务上的异常处理
    @ExceptionHandler(CustomizeException.class)
    public Result handlerBException(CustomizeException e){
        log.error("业务异常,{}",e.getMessage());
        return Result.errorMessage(e.getMessage());
    }
}
