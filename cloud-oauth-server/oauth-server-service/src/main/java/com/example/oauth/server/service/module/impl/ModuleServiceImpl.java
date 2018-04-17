package com.example.oauth.server.service.module.impl;

import com.alibaba.fastjson.JSON;
import com.example.oauth.server.common.util.DozerBeanMapperUtil;
import com.example.oauth.server.common.vo.tree.AbstractEasyuiTreeComponent;
import com.example.oauth.server.common.vo.tree.EasyuiTreeComposite;
import com.example.oauth.server.domain.module.dto.SysModuleDTO;
import com.example.oauth.server.domain.module.entity.SysModule;
import com.example.oauth.server.domain.module.vo.AbstractModuleTree;
import com.example.oauth.server.domain.module.vo.ModuleTreeComposite;
import com.example.oauth.server.domain.module.vo.ModuleTreeLeaf;
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
       // module.setModulePid(0L);
        this.moduleReository.save(module);
        boolean success = module.getId() != null && module.getId() > 0;
        return success;
    }

    @Override
    public List<AbstractModuleTree> listTreeGrid() {
        List<AbstractModuleTree> treeList = new LinkedList<>();
        // 获取 第一级 资源菜单数据
        List<SysModule> firstChildren = this.findByModulePid(0L);
        if (firstChildren != null && !firstChildren.isEmpty()){
            firstChildren.stream().forEach(item -> {
                AbstractModuleTree firstModuleTree = DozerBeanMapperUtil.copyProperties(item,ModuleTreeComposite.class);
                buildTree(item.getId(),firstModuleTree);
                treeList.add(firstModuleTree);

            });
        }
        //AbstractModuleTree firstModuleTree =  new ModuleTreeComposite();
        System.out.println(JSON.toJSONString(treeList));
        return treeList;
    }

    @Override
    public List<AbstractEasyuiTreeComponent> moduleTree(Long pid) {
        List<AbstractEasyuiTreeComponent> treeList =  new LinkedList<>();
        List<SysModule> firstChildren = this.findByModulePid(pid);
        if (firstChildren != null && !firstChildren.isEmpty()){
            firstChildren.stream().forEach(item -> {
                AbstractEasyuiTreeComponent firstModuleTree = new EasyuiTreeComposite();
                firstModuleTree.setId(item.getId());
                firstModuleTree.setText(item.getModuleName());
                treeList.add(firstModuleTree);
            });
        }
        return treeList;
    }


    private List<AbstractModuleTree> buildTree(Long id,AbstractModuleTree firstModuleTree){
        List<AbstractModuleTree> treeList = new LinkedList<>();
        // 获取 第一级 资源菜单数据
        List<SysModule> firstChildren = this.findByModulePid(id);
        if (firstChildren != null && !firstChildren.isEmpty()) {
            firstChildren.stream().forEach(item -> {
                AbstractModuleTree leafTree = DozerBeanMapperUtil.copyProperties(item, ModuleTreeLeaf.class);
                ModuleTreeComposite moduleTreeComposite = (ModuleTreeComposite) firstModuleTree;
                moduleTreeComposite.add(leafTree);
                buildTree(leafTree.getId(),leafTree);
            });
        }
        return treeList;
    }

    /**
     * 根据pid 获取下级信息
     * @param pid
     * @return
     */
    private List<SysModule> findByModulePid(Long pid){
        List<SysModule> moduleList = this.moduleReository.findByModulePid(pid);
        return moduleList;
    }

}
