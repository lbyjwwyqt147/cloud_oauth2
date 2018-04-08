package com.cloud.oauth2.service.module.impl;


import com.cloud.oauth2.repository.module.SysModuleRepository;
import com.cloud.oauth2.service.module.SysModuleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

/***
 *  资源菜单 serviceImpl
 */
@Slf4j
public class SysModuleServiceImpl implements SysModuleService {

    @Autowired
    private SysModuleRepository moduleRepository;
}
