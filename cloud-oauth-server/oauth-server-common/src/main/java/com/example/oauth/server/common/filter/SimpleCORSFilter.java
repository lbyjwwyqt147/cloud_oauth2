package com.example.oauth.server.common.filter;

import com.example.oauth.server.common.util.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


/***
 *
 * @FileName: SimpleCORSFilter
 * @Company:
 * @author    ljy
 * @Date      2018年05月11日
 * @version   1.0.0
 * @remark:   自定义跨域访问过滤器
 *
 */
@Slf4j
@Component
public class SimpleCORSFilter implements Filter {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        String []  allowDomain = {"http://localhost:63342","http://192.168.213.130:8080"};
        Set<String> allowedOrigins= new HashSet<String>(Arrays.asList(allowDomain));
        String originHeader = request.getHeader("Origin");
        log.info("Origin:"+originHeader);
        String token = request.getHeader(tokenHeader);
        final String authToken = token.substring(tokenHead.length());
        log.info("token 值："+authToken);
        TokenUtils.setToken(authToken);
      //  if (allowedOrigins.contains(originHeader)) {
            /* 设置浏览器跨域访问 */
            response.setHeader("Access-Control-Allow-Origin", originHeader);//支持限定的域名或者IP访问
            response.setHeader("Access-Control-Allow-Methods", "POST,GET,OPTIONS,DELETE,PUT");//支持的http 动作
            response.setHeader("Access-Control-Max-Age", "3600");
            response.setHeader("Access-Control-Allow-Headers", "x-requested-with,Authorization,token");//相应头
            response.setHeader("Access-Control-Allow-Credentials", "true"); //跨域cookie设置
            filterChain.doFilter(servletRequest, servletResponse);
      //  }
    }

    public void init(FilterConfig filterConfig) {

    }

    public void destroy() {

    }

}