package com.example.oauth.server.service.acount;

import com.example.oauth.server.domain.account.entity.UserInfo;
import com.example.oauth.server.domain.account.vo.UserInfoVO;

/***
 * 账户 service
 */
public interface UserInfoService {

    /**
     * 根据账号ID获取用户信息
     * @param accountId
     * @return
     */
    UserInfoVO findByAccountId(Long accountId);



}
