package com.example.oauth.security.handlerinterceptor;

import com.example.oauth.server.common.exception.ErrorCodeEnum;
import com.example.oauth.server.common.restful.ResultUtil;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/***
 *
 * @FileName: MyAuthenticationFailureHandler
 * @Company:
 * @author    ljy
 * @Date      2018年05月15日
 * @version   1.0.0
 * @remark:   用户登录系统失败后 需要做的业务操作
 * @explain   当用户登录系统失败后则会进入到此类并执行相关业务
 *
 */
@Component
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        ResultUtil.writeJavaScript(httpServletResponse,ErrorCodeEnum.FAIL,"登录失败.");
    }
}
