package com.example.oauth.server.common.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.oauth.server.common.redis.RedisKeys;
import com.example.oauth.server.common.redis.RedisUtil;
import com.example.oauth.server.common.vo.user.UserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/***
 *
 * @FileName: UserUtils
 * @Company:
 * @author    ljy
 * @Date      2018年05月120日
 * @version   1.0.0
 * @remark:   用户信息操作工具类
 *
 */

@Component
public class UserUtils {
    @Autowired
    private RedisUtil redisUtil;

    @Value("${jwt.header}")
    private String tokenHeader;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    /**
     * 根据token 获取用户信息
     * @param token
     * @return
     */
    public UserDetail getUser(String token){
        String userKey =  RedisKeys.USER_KEY;
        Object object = redisUtil.hget(userKey,token);
        if(object != null){
            UserDetail userDetail = JSON.parseObject(object.toString(),UserDetail.class);
            return userDetail;
        }
        return null;
    }

    /**
     * 根据token 获取用户信息
     * @return
     */
    public UserDetail getUserDetail(){
        String token = TokenUtils.getToken();
        String userKey =  RedisKeys.USER_KEY;
        Object object = redisUtil.hget(userKey,token);
        if(object != null){
            UserDetail userDetail = JSON.parseObject(object.toString(),UserDetail.class);
            return userDetail;
        }
        return null;
    }

    /**
     * 用户ID
     * @return
     */
    public Long getUserId(){
        UserDetail userDetail = this.getUserDetail();
        if(userDetail != null){
            return userDetail.getAccountId();
        }
        return null;
    }

    /**
     * 获取用户token值
     * @param request
     * @return
     */
    public String getUserToken(HttpServletRequest request){
        String token = request.getHeader(tokenHeader);
        final String authToken = token.substring(tokenHead.length());
        return authToken;
    }
}
