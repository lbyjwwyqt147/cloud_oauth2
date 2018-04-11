package com.example.oauth.server.service.role.impl;

import com.example.oauth.server.domain.role.dto.RoleDTO;
import com.example.oauth.server.domain.role.entity.SysRole;
import com.example.oauth.server.repository.role.RoleRepository;
import com.example.oauth.server.service.role.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/***
 * 角色 serviceImpl
 */
@Slf4j
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Transactional
    @Override
    public boolean save(RoleDTO roleDTO) {
        SysRole role = this.copyProperties(roleDTO);
        role.setStatus((byte)1);
        this.roleRepository.save(role);
        return true;
    }

    /**
     *  将RoleDTO 值 拷贝到 SysRole
     * @param roleDTO
     * @return
     */
    private SysRole copyProperties(RoleDTO roleDTO){
        SysRole role = new SysRole();
        BeanUtils.copyProperties(roleDTO,role);
        return role;
    }

}
