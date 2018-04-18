package com.example.oauth.server.domain.module.vo;

import java.util.LinkedList;

/**
 *
 *   用于访问和管理成员子构件的方法，如add()、remove()和getChild()等方法
 *   树枝构件角色Composite：在组合中表示分支节点对象——有子节点，实现抽象构件角色声明的接口；存储子部件。
 *
 */
public class ModuleTreeComposite extends AbstractModuleTree {

    private LinkedList<AbstractModuleTree> children = new LinkedList<AbstractModuleTree>();

    /**
     * 添加
     * @param c
     */
    public void add(AbstractModuleTree c) {
        children.add(c);
    }

    /**
     * 移除
     * @param c
     */
    public void remove(AbstractModuleTree c) {
        children.remove(c);
    }

    /**
     * 获取
     * @param i
     * @return
     */
    public AbstractModuleTree getChild(Integer i) {
        return (AbstractModuleTree)children.get(i);
    }

    public void operation() {
        //容器构件具体业务方法的实现
        //递归调用成员构件的业务方法
        for(Object obj : children) {
            ((AbstractModuleTree)obj).operation();
        }
    }

    public LinkedList<AbstractModuleTree> getChildren() {
        return children;
    }

    public void setChildren(LinkedList<AbstractModuleTree> children) {
        this.children = children;
    }

}
