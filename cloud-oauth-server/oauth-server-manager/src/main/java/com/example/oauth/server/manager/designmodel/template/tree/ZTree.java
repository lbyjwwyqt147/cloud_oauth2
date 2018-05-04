package com.example.oauth.server.manager.designmodel.template.tree;

import com.alibaba.fastjson.JSON;
import com.example.oauth.server.common.vo.tree.AbstractZTreeComponent;
import com.example.oauth.server.common.vo.tree.ZTreeComposite;
import com.example.oauth.server.domain.module.entity.SysModule;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/***
 *  生成 ztree 树形结构
 */
@Component("zTree")
public class ZTree extends AbstractTree {

    @Override
    public Object bulidModuleTree(Long pid,Byte moduleType, Object leaf) {
        AbstractZTreeComponent zTreeComponent = (AbstractZTreeComponent) leaf;
        // 根据PID获取 资源菜单数据
        List<SysModule> firstChildren = this.moduleReository.findByModulePid(pid);
        if (firstChildren != null && !firstChildren.isEmpty()) {
            Map<String,String> map = new HashMap<>();
            firstChildren.stream().forEach(item -> {
                AbstractZTreeComponent leafTree = new ZTreeComposite(item.getId(),item.getModuleName(),"");
                map.put("type",item.getModuleType().toString());
                leafTree.setAttributes(map);
                ZTreeComposite moduleTreeComposite = (ZTreeComposite) zTreeComponent;
                moduleTreeComposite.add(leafTree);
                map.clear();
                bulidModuleTree(leafTree.getId(),moduleType,leafTree);
            });
        }
        return zTreeComponent;
    }

    @Override
    public Object bulidModuleTree(Long pid, Byte moduleType, List<Long> moduleIds, Object leaf) {
        AbstractZTreeComponent zTreeComponent = (AbstractZTreeComponent) leaf;
        // 根据PID获取 资源菜单数据
        List<SysModule> firstChildren = this.moduleReository.findByModulePid(pid);
        if (firstChildren != null && !firstChildren.isEmpty()) {
            Map<String,String> map = new HashMap<>();
            firstChildren.stream().forEach(item -> {
                AbstractZTreeComponent leafTree = new ZTreeComposite(item.getId(),item.getModuleName(),"");
               // leafTree.setOpen(true);
                leafTree.setPid(item.getModulePid());
                map.put("type",item.getModuleType().toString());
                leafTree.setAttributes(map);
                if (moduleIds != null && moduleIds.contains(BigInteger.valueOf(item.getId()))){
                    leafTree.setChecked(true);
                }
                ZTreeComposite moduleTreeComposite = (ZTreeComposite) zTreeComponent;
                moduleTreeComposite.add(leafTree);
                map.clear();
                bulidModuleTree(leafTree.getId(),moduleType,moduleIds,leafTree);
            });
        }
        return zTreeComponent;
    }
}
