package com.example.oauth.security.handlerinterceptor;

import com.example.oauth.server.common.exception.ErrorCodeEnum;
import com.example.oauth.server.common.restful.RestfulVo;
import com.example.oauth.server.common.restful.ResultUtil;
import com.example.oauth.server.common.util.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/***
 *
 * @FileName: MyAuthenticationSuccessHandler
 * @Company:
 * @author    ljy
 * @Date      2018年05月15日
 * @version   1.0.0
 * @remark:   用户登录系统成功后 需要做的业务操作
 * @explain   当用户登录系统成功后则会进入到此类并执行相关业务
 *
 */
@Slf4j
@Component
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        //获得授权后可得到用户信息
        User userDetails =  (User) authentication.getPrincipal();
        //将身份 存储到SecurityContext里
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authentication);
        StringBuffer msg = new StringBuffer("用户：");
        msg.append(userDetails.getUsername()).append(" 成功登录系统.");
        log.info(msg.toString());
        //生成token 用于权限效验
        String token = TokenUtils.userToken(userDetails.getPassword(),userDetails.getUsername());
        //将token 放在cookie中
        Cookie tokenCookie = new Cookie("token",token);
        httpServletResponse.addCookie(tokenCookie);
        Map<String,String> map = new HashMap<>();
        map.put("token",token);
        map.put("msage",msg.toString());
        RestfulVo restfulVo = ResultUtil.resultInfo(ErrorCodeEnum.LOGIN,map);
        ResultUtil.writeJavaScript(httpServletResponse,restfulVo);
    }
}
