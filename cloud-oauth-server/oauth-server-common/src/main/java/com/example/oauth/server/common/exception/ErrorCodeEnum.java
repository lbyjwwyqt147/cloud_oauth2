package com.example.oauth.server.common.exception;

/***
 * 异常代码 值
 */

public enum ErrorCodeEnum {

    SUCCESS("ok.", "0"),
    FAIL("fail.", "1"),
    EXIST("数据已经存在.","10"),
    PARAMS("参数错误.","1002"),
    ERROR("程序运行中发生异常.","-1"),
    DELETE("删除成功.","3");

    private String message ;
    private String code ;

    ErrorCodeEnum(String message, String code) {
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
