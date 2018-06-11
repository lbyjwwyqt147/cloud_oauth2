package com.example.oauth.security.handlerinterceptor;

import com.example.oauth.server.common.exception.ErrorCodeEnum;
import org.springframework.security.core.AuthenticationException;

public class MyAuthenticationException extends AuthenticationException {

    public MyAuthenticationException(String msg, Throwable t) {
        super(msg, t);
    }

    public MyAuthenticationException(String msg) {
        super(msg);
    }

    /**
     * 加入错误状态值
     * @param exceptionEnum
     */
    public MyAuthenticationException(ErrorCodeEnum exceptionEnum) {
        super(exceptionEnum.getMessage());
    }

}
