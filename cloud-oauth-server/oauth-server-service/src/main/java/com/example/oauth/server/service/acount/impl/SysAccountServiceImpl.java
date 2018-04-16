package com.example.oauth.server.service.acount.impl;

import com.example.oauth.server.domain.account.dto.AccountDTO;
import com.example.oauth.server.domain.account.entity.SysAccount;
import com.example.oauth.server.domain.account.vo.AccountVO;
import com.example.oauth.server.repository.account.SysAccountRepository;
import com.example.oauth.server.service.acount.SysAccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

/***
 *  账户 serviceImpl
 */
@Slf4j
@Service
public class SysAccountServiceImpl implements SysAccountService {

    @Autowired
    private SysAccountRepository accountRepository;


    @Override
    public AccountVO findByUserAccount(String userAccount) {
        SysAccount account = this.accountRepository.findByUserAccount(userAccount);
        return this.copyProperties(account);
    }

    @Override
    public AccountVO findByUserAccountAndUserPwd(String userAccount, String userPwd) {
        SysAccount account = this.accountRepository.findByUserAccountAndUserPwd(userAccount,userPwd);
        return this.copyProperties(account);
    }

    @Override
    public AccountVO findByUserAccountAndUserEmail(String userAccount, String userEmail) {
        SysAccount account = this.accountRepository.findByUserAccountAndUserEmail(userAccount,userEmail);
        return this.copyProperties(account);
    }

    @Transactional
    @Override
    public boolean saveAccount(AccountDTO accountDTO) {
        SysAccount account = this.copyProperties(accountDTO);
        if (account != null){
            account.setCreateTime(Instant.now());
            account.setStatus((byte) 1);
            this.accountRepository.save(account);
            log.info("保存后的 id值：" + account.getId());
            return true;
        }
        return false;
    }

    /**
     *  SysAccount 值拷贝到 AccountVO 对象中
     * @param account
     * @return
     */
    private AccountVO copyProperties(SysAccount account){
       if (account != null){
          AccountVO accountVO = new AccountVO();
          BeanUtils.copyProperties(account,accountVO);
          return accountVO;
       }
       return null;
    }

    /**
     *  AccountDTO 值拷贝到 SysAccount 对象中
     * @param accountDTO
     * @return
     */
    private SysAccount copyProperties(AccountDTO accountDTO){
        if (accountDTO != null){
            SysAccount account = new SysAccount();
            BeanUtils.copyProperties(accountDTO,account);
            return account;
        }
        return null;
    }
}