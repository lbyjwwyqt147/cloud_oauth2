package com.example.oauth.server.manager.designmodel.template.tree;

import com.example.oauth.server.common.vo.tree.AbstractEasyuiTreeComponent;
import com.example.oauth.server.domain.module.entity.SysModule;
import com.example.oauth.server.repository.module.ModuleReository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/***
 *  实现了模板方法 定义一个模板
 */
public abstract class AbstractTree {

    @Autowired
    protected ModuleReository moduleReository;

    /**
     *  构建资源菜单树形结构的 模板方法
     * @param pid  资源PID
     * @param moduleType  '模块类型 1:目录  2：菜单   3：功能按钮';
     * @param leaf  叶子节点
     * @return
     */
    public abstract Object bulidModuleTree(Long pid,Byte moduleType,Object leaf);

    /**
     *  构建资源菜单树形结构的 模板方法
     * @param pid  资源PID
     * @param moduleType  '模块类型 1:目录  2：菜单   3：功能按钮';
     * @param  moduleIds   资源id
     * @param leaf  叶子节点
     * @return
     */
    public abstract Object bulidModuleTree(Long pid, Byte moduleType, List<Long> moduleIds, Object leaf);


    /**
     * 递归查找子节点
     * @param leaf
     * @param moduleList
     * @return
     */
    public abstract Object findTreeChildren(Object leaf, List<SysModule> moduleList);

    /**
     * 递归查找子节点
     * @param leaf
     * @param moduleList
     * @param moduleIds
     * @return
     */
    public abstract Object findTreeChildren(Object leaf, List<SysModule> moduleList,List<Long> moduleIds);
}
