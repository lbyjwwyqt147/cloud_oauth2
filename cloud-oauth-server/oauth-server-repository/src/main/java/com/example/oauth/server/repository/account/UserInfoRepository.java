package com.example.oauth.server.repository.account;

import com.example.oauth.server.domain.account.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/***
 *  用户信息  Repository
 */
public interface UserInfoRepository extends JpaRepository<UserInfo,Long>,JpaSpecificationExecutor<UserInfo> {

    /**
     * 根据账号ID获取用户信息
     * @param accountId
     * @return
     */
    UserInfo findByAccountId(Long accountId);

}
