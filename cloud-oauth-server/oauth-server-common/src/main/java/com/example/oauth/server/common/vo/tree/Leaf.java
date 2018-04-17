package com.example.oauth.server.common.vo.tree;

/***
 *  叶子构件结构
 */
public class Leaf extends AbstractComponent {

    public Leaf(Long id, String name, String icon) {
        super(id, name, icon);
    }

    @Override
    public void operation() {
       //叶子构建的具体方法实现
        System.out.println(this.getId());
    }
}
