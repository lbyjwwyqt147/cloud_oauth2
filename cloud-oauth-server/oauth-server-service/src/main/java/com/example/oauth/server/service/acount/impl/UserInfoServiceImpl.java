package com.example.oauth.server.service.acount.impl;

import com.example.oauth.server.common.util.DozerBeanMapperUtil;
import com.example.oauth.server.domain.account.entity.UserInfo;
import com.example.oauth.server.domain.account.vo.UserInfoVO;
import com.example.oauth.server.repository.account.UserInfoRepository;
import com.example.oauth.server.service.acount.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/***
 *  用户信息 serviceImpl
 */
@Slf4j
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Override
    public UserInfoVO findByAccountId(Long accountId) {
        UserInfo userInfo = userInfoRepository.findByAccountId(accountId);
        return DozerBeanMapperUtil.copyProperties(userInfo,UserInfoVO.class);
    }
}
