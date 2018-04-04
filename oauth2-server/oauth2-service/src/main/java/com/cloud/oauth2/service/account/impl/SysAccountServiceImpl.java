package com.cloud.oauth2.service.account.impl;


import com.cloud.oauth2.account.vo.AccountVO;
import com.cloud.oauth2.repository.account.SysAccountRepository;
import com.cloud.oauth2.service.account.SysAccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

/***
 *  账户 serviceImpl
 */
@Slf4j
public class SysAccountServiceImpl implements SysAccountService {

    @Autowired
    private SysAccountRepository accountRepository;


    @Override
    public AccountVO findByUserAccount(String userAccount) {
        return null;
    }

    @Override
    public AccountVO findByUserAccountAndUserPwd(String userAccount, String userPwd) {
        return null;
    }

    @Override
    public AccountVO findByUserAccountAndUserEmail(String userAccount, String userEmail) {
        return null;
    }

    @Override
    public boolean saveAccount() {
        return false;
    }
}
