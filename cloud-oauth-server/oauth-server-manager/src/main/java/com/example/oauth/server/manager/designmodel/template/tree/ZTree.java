package com.example.oauth.server.manager.designmodel.template.tree;

import com.example.oauth.server.common.util.DozerBeanMapperUtil;
import com.example.oauth.server.common.vo.tree.AbstractZTreeComponent;
import com.example.oauth.server.common.vo.tree.ZTreeComposite;
import com.example.oauth.server.domain.module.entity.SysModule;
import com.example.oauth.server.domain.module.vo.AbstractModuleTree;
import com.example.oauth.server.domain.module.vo.ModuleTreeComposite;
import org.springframework.stereotype.Component;

import java.util.List;


/***
 *  生成 ztree 树形结构
 */
@Component("zTree")
public class ZTree extends AbstractTree {

    @Override
    public Object bulidModuleTree(Long pid, Object leaf) {
        AbstractZTreeComponent zTreeComponent = (AbstractZTreeComponent) leaf;
        // 根据PID获取 资源菜单数据
        List<SysModule> firstChildren = this.moduleReository.findByModulePid(pid);
        if (firstChildren != null && !firstChildren.isEmpty()) {
            firstChildren.stream().forEach(item -> {
                AbstractZTreeComponent leafTree = DozerBeanMapperUtil.copyProperties(item, ZTreeComposite.class);
                ZTreeComposite moduleTreeComposite = (ZTreeComposite) zTreeComponent;
                moduleTreeComposite.add(leafTree);
                bulidModuleTree(leafTree.getId(),leafTree);
            });
        }
        return zTreeComponent;
    }
}
