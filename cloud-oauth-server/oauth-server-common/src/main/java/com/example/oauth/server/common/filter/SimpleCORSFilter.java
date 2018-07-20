package com.example.oauth.server.common.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
public class SimpleCORSFilter extends  OncePerRequestFilter {


    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String originHeader = httpServletRequest.getHeader("Origin");
        log.info("Origin:"+originHeader);
        /* 设置浏览器跨域访问 */
        httpServletResponse.setHeader("Access-Control-Allow-Origin", originHeader);//支持限定的域名或者IP访问
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "POST,GET,OPTIONS,DELETE,PUT");//支持的http 动作
        httpServletResponse.setHeader("Access-Control-Max-Age", "3600");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", "Content-Type,x-requested-with,Authorization,token");//相应头
        httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true"); //跨域cookie设置
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}