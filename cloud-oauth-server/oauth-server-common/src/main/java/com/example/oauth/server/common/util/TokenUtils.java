package com.example.oauth.server.common.util;

/***
 *
 * @FileName: TokenUtils
 * @Company:
 * @author    ljy
 * @Date      2018年05月15日
 * @version   1.0.0
 * @remark:  token工具类， 生成token、校验token 功能
 * @explain
 *
 *
 */
public class TokenUtils {

    public static final  String SECRET_KEY = "user-token";

    /**
     * 创建用户token
     * @param userId
     * @param userAccount
     * @return
     */
    public static String userToken(String userId,String userAccount) {
        String key = userId+userAccount+SECRET_KEY;
        return MD5Utils.getMD5String(key);
    }

}
