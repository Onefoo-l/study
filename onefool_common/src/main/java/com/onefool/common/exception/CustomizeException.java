package com.onefool.common.exception;


import com.onefool.common.pojo.StatusCode;

/**
 * Onefool
 * version 1.0.0
 * 自定义异常类 用于业务上的自定义异常
 */
public class CustomizeException extends RuntimeException{

    //错误的状态码
    private Integer code;

    //错误信息
    private String message;

    public CustomizeException(){

    }
    public CustomizeException(Integer code, String message){
        this.code=code;
        this.message=message;
    }

    public CustomizeException(String message){
        //自定义的异常的状态
        this.code= StatusCode.CUSTOM_FAILURE.code();
        this.message=message;
    }


    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }


}