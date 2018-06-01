package com.example.oauth.server.common.exception;

/***
 * 异常代码 值
 */

public enum ErrorCodeEnum {

    SUCCESS("ok.", "0"),
    FAIL("fail.", "1"),
    EXIST("数据已经存在.","10"),
    PARAMS("参数错误.","400"),
    ERROR("服务器遇到错误，无法完成请求,请联系管理员.","500"),
    DELETE("删除成功.","3"),
    AUTHORITY("无访问权限,请联系管理员.","403"),
    LOGIN_WITHOUT("登录超时,请重新登录.","-1"),
    LOGIN_FAIL("登录失败.","-2"),
    LOGIN_INCORRECT("登录账户或者密码错误.","-3"),
    TOKEN_INVALID("无效的用户token.","401");

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
