package com.example.oauth.server.service.module.impl;

import com.example.oauth.server.domain.module.dto.SysModuleDTO;
import com.example.oauth.server.domain.module.entity.SysModule;
import com.example.oauth.server.repository.module.ModuleReository;
import com.example.oauth.server.service.module.ModuleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/***
 * 资源菜单　serviceImpl
 */
@Slf4j
@Service
public class ModuleServiceImpl implements ModuleService {

    @Autowired
    private ModuleReository moduleReository;

    @Transactional
    @Override
    public boolean saveModule(SysModuleDTO moduleDTO) {
        SysModule module = this.copyProperties(moduleDTO);
        this.moduleReository.save(module);
        return true;
    }

    /**
     *  将SysModuleDTO 值 拷贝到 SysModule
     * @param moduleDTO
     * @return
     */
    private SysModule copyProperties(SysModuleDTO moduleDTO){
        SysModule module = new SysModule();
        BeanUtils.copyProperties(moduleDTO,module);
        return module;
    }
}
