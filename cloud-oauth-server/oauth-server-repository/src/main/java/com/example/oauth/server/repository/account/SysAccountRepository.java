package com.example.oauth.server.repository.account;

import com.example.oauth.server.domain.account.entity.SysAccount;
import org.springframework.data.jpa.repository.JpaRepository;

/***
 * 账户  Repository
 */
public interface SysAccountRepository  extends JpaRepository<SysAccount,Long> {

    /**
     * 根据账户获取账户信息
     * @param userAccount 账户
     * @return
     */
    SysAccount findByUserAccount(String userAccount);

    /**
     * 根据 账户和密码获取账户信息
     * @param userAccount  账户
     * @param userPwd  密码
     * @return
     */
    SysAccount findByUserAccountAndUserPwd(String userAccount,String userPwd);

    /**
     * 根据 账户和注册邮箱获取账户信息
     * @param userAccount  账户
     * @param userEmail   注册邮箱
     * @return
     */
    SysAccount findByUserAccountAndUserEmail(String userAccount,String userEmail);
}
