package com.cloud.oauth2.service.account;


import com.cloud.oauth2.account.dto.AccountDTO;
import com.cloud.oauth2.account.vo.AccountVO;

/***
 * 账户 service
 */
public interface SysAccountService {

    /**
     * 根据账户获取账户信息
     * @param userAccount 账户
     * @return
     */
    AccountVO findByUserAccount(String userAccount);

    /**
     * 根据 账户和密码获取账户信息
     * @param userAccount  账户
     * @param userPwd  密码
     * @return
     */
    AccountVO findByUserAccountAndUserPwd(String userAccount,String userPwd);

    /**
     * 根据 账户和注册邮箱获取账户信息
     * @param userAccount  账户
     * @param userEmail   注册邮箱
     * @return
     */
    AccountVO findByUserAccountAndUserEmail(String userAccount,String userEmail);

    /**
     * 保持账号信息
     * @param accountDTO
     * @return
     */
    boolean saveAccount(AccountDTO accountDTO);

}
