package com.example.oauth.server.repository.account;

/**
 *  账号 用户表关联查询 的结果集
 */
public interface UserInfoResultSets {

    //用户帐号ID
    Long getId();
    //用户名称
    String getUserName();
    //邮箱
    String getUserEmail();


}
