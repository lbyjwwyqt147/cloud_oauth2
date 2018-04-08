package com.cloud.oauth2.service.role.impl;


import com.cloud.oauth2.repository.role.SysRoleModuleRepository;
import com.cloud.oauth2.service.role.SysRoleModuleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

/***
 * 角色资源 serviceImpl
 */
@Slf4j
public class SysRoleModuleServiceImpl implements SysRoleModuleService {

    @Autowired
    private SysRoleModuleRepository roleModuleRepository;
}
