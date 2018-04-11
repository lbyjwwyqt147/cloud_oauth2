package com.example.oauth.server.common.exception;

/***
 *
 */
public class DescribeException extends RuntimeException {

    private static final long serialVersionUID = 7439455905102777637L;


    public DescribeException() {
        super();
    }

    /**
     * 继承exception，加入错误状态值
     * @param exceptionEnum
     */
    public DescribeException(ErrorCodeEnum exceptionEnum) {
        super(exceptionEnum.getMessage());
    }

    /**
     * 自定义错误信息
     * @param message
     */
    public DescribeException(String message) {
        super(message);
    }

    public DescribeException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public DescribeException(Throwable throwable) {
        super(throwable);
    }
}
