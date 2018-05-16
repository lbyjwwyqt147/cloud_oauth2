package com.example.oauth.server.common.exception;

/***
 * 异常代码 值
 */

public enum ErrorCodeEnum {

    SUCCESS("ok.", "0"),
    FAIL("fail.", "1"),
    EXIST("数据已经存在.","10"),
    PARAMS("参数错误.","1002"),
    ERROR("程序执行发生错误异常.","500"),
    DELETE("删除成功.","3"),
    AUTHORITY("无访问权限.","403"),
    LOGIN("未登录系统","-1");


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
