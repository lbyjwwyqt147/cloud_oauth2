package com.example.oauth.server.common.vo.tree;

import java.util.LinkedList;

/***
 *  用于访问和管理成员子构件的方法，如add()、remove()和getChild()等方法
 */
public class Composite extends AbstractComponent {

    public Composite(Long id, String name, String icon) {
        super(id, name, icon);
    }

    private LinkedList<AbstractComponent> children = new LinkedList<AbstractComponent>();

    /**
     * 添加
     * @param c
     */
    public void add(AbstractComponent c) {
        children.add(c);
    }

    /**
     * 移除
     * @param c
     */
    public void remove(AbstractComponent c) {
        children.remove(c);
    }

    /**
     * 获取
     * @param i
     * @return
     */
    public AbstractComponent getChild(Integer i) {
        return (AbstractComponent)children.get(i);
    }

    public void operation() {
        //容器构件具体业务方法的实现
        //递归调用成员构件的业务方法
        for(Object obj : children) {
            ((AbstractComponent)obj).operation();
        }
    }

    public LinkedList<AbstractComponent> getChildren() {
        return children;
    }

    public void setChildren(LinkedList<AbstractComponent> children) {
        this.children = children;
    }
}
