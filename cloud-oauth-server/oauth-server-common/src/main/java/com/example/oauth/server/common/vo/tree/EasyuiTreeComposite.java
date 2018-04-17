package com.example.oauth.server.common.vo.tree;

import java.util.LinkedList;

/***
 *  用于访问和管理成员子构件的方法，如add()、remove()和getChild()等方法
 */
public class EasyuiTreeComposite extends AbstractEasyuiTreeComponent{

    private LinkedList<AbstractEasyuiTreeComponent> children = new LinkedList<>();

    /**
     * 添加
     * @param c
     */
    public void add(AbstractEasyuiTreeComponent c) {
        children.add(c);
    }

    /**
     * 移除
     * @param c
     */
    public void remove(AbstractEasyuiTreeComponent c) {
        children.remove(c);
    }

    /**
     * 获取
     * @param i
     * @return
     */
    public AbstractEasyuiTreeComponent getChild(Integer i) {
        return (AbstractEasyuiTreeComponent)children.get(i);
    }

    public void operation() {
        //容器构件具体业务方法的实现
        //递归调用成员构件的业务方法
        for(Object obj : children) {
            ((AbstractEasyuiTreeComponent)obj).operation();
        }
    }

    public LinkedList<AbstractEasyuiTreeComponent> getChildren() {
        return children;
    }

    public void setChildren(LinkedList<AbstractEasyuiTreeComponent> children) {
        this.children = children;
    }
}
