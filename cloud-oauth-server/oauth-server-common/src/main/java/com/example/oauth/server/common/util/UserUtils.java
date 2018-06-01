package com.example.oauth.server.common.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.oauth.server.common.redis.RedisKeys;
import com.example.oauth.server.common.redis.RedisUtil;
import com.example.oauth.server.common.vo.user.UserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
}
