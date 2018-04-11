package com.example.oauth.server.service.role.impl;

import com.example.oauth.server.domain.role.dto.RoleModuleDTO;
import com.example.oauth.server.domain.role.entity.SysRoleModule;
import com.example.oauth.server.repository.role.RoleModuleRepository;
import com.example.oauth.server.service.role.RoleModuleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

/***
 * 角色资源 serviceImpl
 */
@Slf4j
@Service
public class RoleModuleServiceImpl implements RoleModuleService {

    @Autowired
    private RoleModuleRepository roleModuleRepository;

    @Transactional
    @Override
    public boolean batchSave(RoleModuleDTO roleModuleDTO) {
        boolean success = false;
        List<Long> moduleIds = roleModuleDTO.getModuleIds();
        boolean moduleIdsEmpty = moduleIds != null && !moduleIds.isEmpty();
        if(moduleIdsEmpty){
            List<SysRoleModule> roleModuleList = new LinkedList<>();
            moduleIds.stream().forEach(item -> {
               SysRoleModule roleModule = new SysRoleModule();
               roleModule.setModuleId(item);
               roleModule.setRoleId(roleModuleDTO.getRoleId());
               roleModuleList.add(roleModule);
            });
            success = this.roleModuleRepository.saveAll(roleModuleList) != null;
        }
        return success;
    }
}
