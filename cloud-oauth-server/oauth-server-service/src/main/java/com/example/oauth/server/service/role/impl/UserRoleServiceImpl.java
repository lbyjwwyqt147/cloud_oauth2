package com.example.oauth.server.service.role.impl;

import com.example.oauth.server.domain.role.dto.UserRoleDTO;
import com.example.oauth.server.domain.role.entity.SysUserRole;
import com.example.oauth.server.repository.role.UserRoleRepository;
import com.example.oauth.server.service.role.UserRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

/***
 * 人员角色 serviceImpl
 */
@Slf4j
@Service
public class UserRoleServiceImpl implements UserRoleService {
    @Autowired
    private UserRoleRepository userRoleRepository;

    @Transactional
    @Override
    public boolean batchSave(UserRoleDTO userRoleDTO) {
        boolean success = false;
        List<Long> roleIds = userRoleDTO.getRoleIds();
        boolean roleIdsEmpty = roleIds != null && !roleIds.isEmpty();
        if (roleIdsEmpty){
            List<SysUserRole> userRoleList = new LinkedList<>();
            roleIds.stream().forEach(item -> {
                SysUserRole userRole = new SysUserRole();
                userRole.setRoleId(item);
                userRole.setUserId(userRoleDTO.getUserId());
                userRoleList.add(userRole);
            });
            success = this.userRoleRepository.saveAll(userRoleList) != null;
        }
        return success;
    }
}
