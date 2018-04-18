package com.example.oauth.server.manager.designmodel.template.tree;

import com.example.oauth.server.repository.module.ModuleReository;
import org.springframework.beans.factory.annotation.Autowired;

/***
 *  实现了模板方法 定义一个模板
 */
public abstract class AbstractTree {

    @Autowired
    protected ModuleReository moduleReository;

    /**
     *  构建资源菜单树形结构的 模板方法
     * @param pid
     * @param leaf
     * @return
     */
    public abstract Object bulidModuleTree(Long pid,Object leaf);


}
