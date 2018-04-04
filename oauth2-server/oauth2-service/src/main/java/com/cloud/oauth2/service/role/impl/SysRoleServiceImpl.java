package com.cloud.oauth2.service.role.impl;



import com.cloud.oauth2.repository.role.SysRoleRepository;
import com.cloud.oauth2.service.role.SysRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

/***
 *  角色 serviceImpl
 */
@Slf4j
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    private SysRoleRepository roleRepository;
}
