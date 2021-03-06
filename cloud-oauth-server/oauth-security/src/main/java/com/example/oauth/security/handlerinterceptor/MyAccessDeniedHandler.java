package com.example.oauth.security.handlerinterceptor;

import com.example.oauth.server.common.exception.ErrorCodeEnum;
import com.example.oauth.server.common.restful.RestfulVo;
import com.example.oauth.server.common.restful.ResultUtil;
import com.example.oauth.server.common.util.HttpUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/***
 *
 * @FileName: MyAccessDeniedHandler
 * @Company:
 * @author    ljy
 * @Date      2018年05月15日
 * @version   1.0.0
 * @remark:   自定义权限不足 需要做的业务操作
 * @explain   当用户登录系统后访问资源时因权限不足则会进入到此类并执行相关业务
 *
 */
@Slf4j
@Component
public class MyAccessDeniedHandler  implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        StringBuffer msg = new StringBuffer("请求: ");
        msg.append(httpServletRequest.getRequestURI()).append(" 权限不足，无法访问系统资源.");
        log.info(msg.toString());
        ResultUtil.writeJavaScript(httpServletResponse,ErrorCodeEnum.AUTHORITY,msg.toString());


       /* boolean ajaxRequest = HttpUtils.isAjaxRequest(httpServletRequest);
        if (ajaxRequest){
            //如果是ajax请求 则返回403错
            ResultUtil.writeJavaScript(httpServletResponse,ErrorCodeEnum.AUTHORITY,msg.toString());
        }else {
            // 非ajax请求 则跳转到指定的403页面
            //此处省略...................
        }*/
    }
}
