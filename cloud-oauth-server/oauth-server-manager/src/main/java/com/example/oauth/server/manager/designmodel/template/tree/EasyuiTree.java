package com.example.oauth.server.manager.designmodel.template.tree;

import com.example.oauth.server.common.util.DozerBeanMapperUtil;
import com.example.oauth.server.common.vo.tree.AbstractEasyuiTreeComponent;
import com.example.oauth.server.common.vo.tree.AbstractZTreeComponent;
import com.example.oauth.server.common.vo.tree.EasyuiTreeComposite;
import com.example.oauth.server.common.vo.tree.ZTreeComposite;
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
}
