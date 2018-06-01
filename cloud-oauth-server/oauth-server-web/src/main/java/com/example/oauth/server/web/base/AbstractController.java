package com.example.oauth.server.web.base;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/auth/v1/api/")
public class AbstractController {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    public String getUserToken(HttpServletRequest request){
        String token = request.getHeader(tokenHeader);
        final String authToken = token.substring(tokenHead.length());
        return authToken;
    }

}
