package com.cloud.oauth2.repository.account;

import com.cloud.oauth2.account.entity.SysAccountDO;
import com.cloud.oauth2.account.vo.AccountVO;
import org.springframework.data.jpa.repository.JpaRepository;

/***
 * 账户
 */
public interface SysAccountRepository  extends JpaRepository<SysAccountDO,Long> {

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


}
