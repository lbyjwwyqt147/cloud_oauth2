package com.example.oauth.server.service.module.impl;

import com.example.oauth.server.common.util.DozerBeanMapperUtil;
import com.example.oauth.server.domain.module.dto.SysModuleDTO;
import com.example.oauth.server.domain.module.entity.SysModule;
import com.example.oauth.server.domain.module.vo.AbstractModuleTree;
import com.example.oauth.server.domain.module.vo.ModuleTreeComposite;
import com.example.oauth.server.repository.module.ModuleReository;
import com.example.oauth.server.service.module.ModuleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.LinkedList;
import java.util.List;

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
        String[] ignoreProperties = new String[]{"updateId","updateUserName"};
        SysModule module = DozerBeanMapperUtil.copyProperties(moduleDTO,SysModule.class,ignoreProperties);
        module.setStatus((byte)1);
        module.setCreateTime(Instant.now());
        module.setModulePid(0L);
        this.moduleReository.save(module);
        boolean success = module.getId() != null && module.getId() > 0;
        return success;
    }

    @Override
    public List<AbstractModuleTree> listTreeGrid() {
        List<AbstractModuleTree> treeList = new LinkedList<>();
        // 获取 第一级 资源菜单数据
        List<SysModule> firstChildren = this.moduleReository.findByModulePid(0L);
        if (firstChildren != null && !firstChildren.isEmpty()){
            firstChildren.stream().forEach(item -> {
                AbstractModuleTree firstModuleTree = DozerBeanMapperUtil.copyProperties(item,ModuleTreeComposite.class);
                treeList.add(firstModuleTree);
            });
        }
        //AbstractModuleTree firstModuleTree =  new ModuleTreeComposite();

        return treeList;
    }
}
