package com.cloud.oauth2.service.role.impl;


import com.cloud.oauth2.repository.role.SysUserRoleRepository;
import com.cloud.oauth2.service.role.SysUserRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

/***
 * 用户角色 serviceImpl
 */
@Slf4j
public class SysUserRoleServiceImpl implements SysUserRoleService {

    @Autowired
    private SysUserRoleRepository userRoleRepository;

}
