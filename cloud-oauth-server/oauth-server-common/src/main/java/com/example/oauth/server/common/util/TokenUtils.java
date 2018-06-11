package com.example.oauth.server.common.util;


/***
 * token 工具类
 */
public class TokenUtils {

    private static final ThreadLocal<String> LOCAL_TOKEN = new ThreadLocal<>();

    /**
     * 设置token
     * @param token
     */
    public static final void setToken(String token){
        LOCAL_TOKEN.set(token);
    }

    /**
     * 获取token
     * @return
     */
    public static final String getToken(){
        String token = LOCAL_TOKEN.get();
        LOCAL_TOKEN.remove();
        return token;
    }
}
