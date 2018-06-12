package com.example.oauth.server.manager.designmodel.template.tree;

import com.example.oauth.server.common.vo.tree.AbstractEasyuiTreeComponent;
import com.example.oauth.server.common.vo.tree.EasyuiTreeComposite;
import com.example.oauth.server.domain.module.entity.SysModule;
import org.springframework.stereotype.Component;

import java.util.List;

/***
 *  easyui tree 结构数据
 */
@Component("easyuiTree")
public class EasyuiTree extends AbstractTree {

    @Override
    public Object bulidModuleTree(Long pid, Byte moduleType,Object leaf) {
        AbstractEasyuiTreeComponent easyuiTreeComponent = (AbstractEasyuiTreeComponent) leaf;
        // 根据PID获取 资源菜单数据
        List<SysModule> firstChildren = this.moduleReository.findByModulePidAndModuleTypeNot(pid,moduleType);
        if (firstChildren != null && !firstChildren.isEmpty()) {
            firstChildren.stream().forEach(item -> {
                AbstractEasyuiTreeComponent firstModuleTree = new EasyuiTreeComposite();
                firstModuleTree.setId(item.getId());
                firstModuleTree.setText(item.getModuleName());
                //firstModuleTree.setState("closed");
                EasyuiTreeComposite moduleTreeComposite = (EasyuiTreeComposite) easyuiTreeComponent;
                moduleTreeComposite.add(firstModuleTree);
                bulidModuleTree(firstModuleTree.getId(),moduleType,firstModuleTree);
            });
        }
        return easyuiTreeComponent;
    }

    @Override
    public Object bulidModuleTree(Long pid, Byte moduleType, List<Long> moduleIds, Object leaf) {
        return null;
    }

    @Override
    public Object findTreeChildren(Object leaf, List<SysModule> moduleList) {
        AbstractEasyuiTreeComponent easyuiTree = (AbstractEasyuiTreeComponent) leaf;
        moduleList.stream().forEach(item ->{
            if (easyuiTree.getId().equals(item.getModulePid())){
                AbstractEasyuiTreeComponent firstModuleTree = new EasyuiTreeComposite();
                firstModuleTree.setId(item.getId());
                firstModuleTree.setText(item.getModuleName());
                //firstModuleTree.setState("closed");
                EasyuiTreeComposite moduleTreeComposite = (EasyuiTreeComposite) easyuiTree;
                moduleTreeComposite.add(firstModuleTree);
                findTreeChildren(firstModuleTree,moduleList);
            }
        });
        return easyuiTree;
    }

    @Override
    public Object findTreeChildren(Object leaf, List<SysModule> moduleList, List<Long> moduleIds) {
        return null;
    }
}
