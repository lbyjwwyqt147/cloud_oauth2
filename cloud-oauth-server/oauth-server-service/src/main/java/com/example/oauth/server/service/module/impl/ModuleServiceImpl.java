package com.example.oauth.server.service.module.impl;

import com.example.oauth.server.common.util.DozerBeanMapperUtil;
import com.example.oauth.server.common.vo.tree.AbstractEasyuiTreeComponent;
import com.example.oauth.server.common.vo.tree.AbstractZTreeComponent;
import com.example.oauth.server.common.vo.tree.EasyuiTreeComposite;
import com.example.oauth.server.common.vo.tree.ZTreeComposite;
import com.example.oauth.server.domain.module.dto.SysModuleDTO;
import com.example.oauth.server.domain.module.entity.SysModule;
import com.example.oauth.server.domain.module.vo.AbstractModuleTree;
import com.example.oauth.server.domain.module.vo.ModuleTreeComposite;
import com.example.oauth.server.manager.designmodel.template.tree.AbstractTree;
import com.example.oauth.server.repository.module.ModuleReository;
import com.example.oauth.server.repository.role.RoleModuleRepository;
import com.example.oauth.server.service.module.ModuleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
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
    @Autowired
    private RoleModuleRepository roleModuleRepository;
    @Autowired
    private AbstractTree zTree;
    @Autowired
    private AbstractTree easyuiTree;

    @Transactional
    @Override
    public boolean saveModule(SysModuleDTO moduleDTO) {
        String[] ignoreProperties = new String[]{"updateId","updateUserName"};
        SysModule module = DozerBeanMapperUtil.copyProperties(moduleDTO,SysModule.class,ignoreProperties);
        this.moduleReository.save(module);
        boolean success = module.getId() != null && module.getId() > 0;
        return success;
    }

    @Transactional
    @Override
    public boolean singleDeleteById(Long id) {
        try {
            //删除 关联的角色资源数据
            this.roleModuleRepository.deleteByModuleId(id);
            //删除资源 child 节点数据
            this.moduleReository.deleteByModulePid(id);
            //删除资源数据
            this.moduleReository.deleteById(id);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<AbstractModuleTree> listTreeGrid() {
        List<AbstractModuleTree> treeList = new LinkedList<>();
        // 获取 第一级 资源菜单数据
        List<SysModule> firstChildren = this.findByModulePid(0L);
        if (firstChildren != null && !firstChildren.isEmpty()){
            firstChildren.stream().forEach(item -> {
                AbstractModuleTree firstModuleTree = DozerBeanMapperUtil.copyProperties(item,ModuleTreeComposite.class);
                firstModuleTree =  buildTree(item.getId(),firstModuleTree,null);
                treeList.add(firstModuleTree);
            });
        }
        return treeList;
    }

    @Override
    public List<AbstractEasyuiTreeComponent> moduleTree(Long pid) {
        List<AbstractEasyuiTreeComponent> treeList =  new LinkedList<>();
        byte type = 3;
        List<SysModule> firstChildren = this.moduleReository.findByModulePidAndModuleTypeNot(pid,type);
        if (firstChildren != null && !firstChildren.isEmpty()){
            firstChildren.stream().forEach(item -> {
                AbstractEasyuiTreeComponent firstModuleTree = new EasyuiTreeComposite();
                firstModuleTree.setId(item.getId());
                firstModuleTree.setText(item.getModuleName());
                firstModuleTree = (AbstractEasyuiTreeComponent) easyuiTree.bulidModuleTree(firstModuleTree.getId(),type,firstModuleTree);
                treeList.add(firstModuleTree);
            });
        }
        return treeList;
    }

    @Override
    public List<AbstractZTreeComponent> roleModuleTree(Long pid, Long roleId) {
        List<AbstractZTreeComponent> treeList = new LinkedList<>();
        byte type = 0;
        // 获取 第一级 资源菜单数据
        List<SysModule> firstChildren = this.findByModulePid(0L);
        if (firstChildren != null && !firstChildren.isEmpty()){
            // 根据角色ID 获取角色分配的资源ID
            List<Long> roleModuleIds = this.roleModuleRepository.findModuleIdByRoleId(roleId);
            firstChildren.stream().forEach(item -> {
                AbstractZTreeComponent firstModuleTree = new ZTreeComposite(item.getId(),item.getModuleName(),"");
                firstModuleTree.setParent(true);
                firstModuleTree.setPid(item.getModulePid());
                if (roleModuleIds != null && roleModuleIds.contains(BigInteger.valueOf(item.getId()))){
                    firstModuleTree.setChecked(true);
                }
               // if(roleModuleIds != null && !roleModuleIds.isEmpty()){
                    firstModuleTree = (AbstractZTreeComponent) zTree.bulidModuleTree(item.getId(),type,roleModuleIds,firstModuleTree);
              //  }
                treeList.add(firstModuleTree);
            });
        }
        return treeList;
    }

    @Override
    public List<SysModule> findAllModuleExcludeDirectory() {
        return this.moduleReository.findByModuleTypeNot((byte)1);
    }

    @Override
    public List<SysModule> findByRoleModule() {
        return this.moduleReository.findByRoleModule();
    }

    @Override
    public List<AbstractModuleTree> userModuleTree(Long userId) {
        List<AbstractModuleTree> treeList = new LinkedList<>();
        List<SysModule> moduleList = this.moduleReository.findByUserModuleNot(userId,(byte)3);
        if (moduleList != null && !moduleList.isEmpty()){
            moduleList.stream().forEach(item -> {
                if (item.getModulePid()==0){
                    AbstractModuleTree firstModuleTree = DozerBeanMapperUtil.copyProperties(item,ModuleTreeComposite.class);
                    firstModuleTree =  buildUserModuelTree(firstModuleTree,moduleList);
                    treeList.add(firstModuleTree);
                }
            });
        }
        return treeList;
    }

    @Override
    public List<SysModule> findByUserModule(Long userId) {
        return this.moduleReository.findByUserModule(userId);
    }

    /**
     * 构建用户拥有的资源菜单树
     * @return
     */
    private AbstractModuleTree buildUserModuelTree(AbstractModuleTree firstModuleTree,List<SysModule> moduleList){
        moduleList.stream().forEach(item ->{
            if (firstModuleTree.getId() == item.getModulePid()){
                AbstractModuleTree leafTree = DozerBeanMapperUtil.copyProperties(item, ModuleTreeComposite.class);
                ModuleTreeComposite moduleTreeComposite = (ModuleTreeComposite) firstModuleTree;
                moduleTreeComposite.add(leafTree);
            }
        });
        return firstModuleTree;
    }


    /**
     * 构建 treeGrid 树结构数据
     * @param id
     * @param firstModuleTree
     * @return
     */
    private AbstractModuleTree buildTree(Long id,AbstractModuleTree firstModuleTree,Byte type){
        // 根据PID获取 资源菜单数据
        List<SysModule> firstChildren =  null;
        if (type != null){
            firstChildren = this.moduleReository.findByModulePidAndModuleType(id,(byte)2);
        }else {
            firstChildren = this.findByModulePid(id);
        }
        if (firstChildren != null && !firstChildren.isEmpty()) {
            firstChildren.stream().forEach(item -> {
                AbstractModuleTree leafTree = DozerBeanMapperUtil.copyProperties(item, ModuleTreeComposite.class);
                ModuleTreeComposite moduleTreeComposite = (ModuleTreeComposite) firstModuleTree;
                moduleTreeComposite.add(leafTree);
                buildTree(leafTree.getId(),leafTree,type);
            });
        }
        return firstModuleTree;
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
