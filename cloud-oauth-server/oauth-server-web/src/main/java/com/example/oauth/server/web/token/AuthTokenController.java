package com.example.oauth.server.web.token;

import com.example.oauth.security.jwt.JwtTokenUtil;
import com.example.oauth.server.common.restful.RestfulVo;
import com.example.oauth.server.common.restful.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@RestController
public class AuthTokenController {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Value("${jwt.header}")
    private String tokenHeader;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    /**
     * 刷新token
     * @param request
     * @return
     * @throws AuthenticationException
     */
    @RequestMapping(value = "oauth/refresh", method = RequestMethod.GET)
    public RestfulVo refreshAndGetAuthenticationToken(
            HttpServletRequest request) throws AuthenticationException {
        String token = request.getHeader(tokenHeader);
        final String authToken = token.substring(tokenHead.length());
        String refreshedToken = jwtTokenUtil.refreshToken(authToken);
        ConcurrentMap<String, String> map = new ConcurrentHashMap<>();
        map.put("token", tokenHead+refreshedToken);
        return ResultUtil.restfulInfo(true,map);
    }

}
