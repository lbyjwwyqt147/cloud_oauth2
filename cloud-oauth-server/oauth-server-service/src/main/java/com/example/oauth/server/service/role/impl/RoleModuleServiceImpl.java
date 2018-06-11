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
        String[] moduleIds = roleModuleDTO.getModuleIds();
        boolean moduleIdsEmpty = moduleIds != null && moduleIds.length > 0;
        if(moduleIdsEmpty){
            List<SysRoleModule> roleModuleList = new LinkedList<>();
            for (String moduleId:moduleIds) {
                SysRoleModule roleModule = new SysRoleModule();
                roleModule.setModuleId(Long.valueOf(moduleId));
                roleModule.setRoleId(roleModuleDTO.getRoleId());
                roleModuleList.add(roleModule);
            }
            //先删除角色资源 再重新保存
            this.roleModuleRepository.deleteByRoleId(roleModuleDTO.getRoleId());
            success = this.roleModuleRepository.saveAll(roleModuleList) != null;
        }
        return success;
    }

    @Transactional
    @Override
    public boolean deleteByRoleIdAndModuleIdIn(Long roleId, List<Long> moduleList) {
        Long resultNumber = this.roleModuleRepository.deleteByRoleIdAndModuleIdIn(roleId,moduleList);
        boolean success = resultNumber > 0;
        return success;
    }

}
