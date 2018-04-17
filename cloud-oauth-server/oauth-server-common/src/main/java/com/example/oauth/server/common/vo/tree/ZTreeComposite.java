package com.example.oauth.server.common.vo.tree;

import java.util.LinkedList;

/***
 *  用于访问和管理成员子构件的方法，如add()、remove()和getChild()等方法
 */
public class ZTreeComposite extends AbstractZTreeComponent {

    public ZTreeComposite(Long id, String name, String icon) {
        super(id, name, icon);
    }

    private LinkedList<AbstractZTreeComponent> children = new LinkedList<AbstractZTreeComponent>();

    /**
     * 添加
     * @param c
     */
    public void add(AbstractZTreeComponent c) {
        children.add(c);
    }

    /**
     * 移除
     * @param c
     */
    public void remove(AbstractZTreeComponent c) {
        children.remove(c);
    }

    /**
     * 获取
     * @param i
     * @return
     */
    public AbstractZTreeComponent getChild(Integer i) {
        return (AbstractZTreeComponent)children.get(i);
    }

    public void operation() {
        //容器构件具体业务方法的实现
        //递归调用成员构件的业务方法
        for(Object obj : children) {
            ((AbstractZTreeComponent)obj).operation();
        }
    }

    public LinkedList<AbstractZTreeComponent> getChildren() {
        return children;
    }

    public void setChildren(LinkedList<AbstractZTreeComponent> children) {
        this.children = children;
    }
}
