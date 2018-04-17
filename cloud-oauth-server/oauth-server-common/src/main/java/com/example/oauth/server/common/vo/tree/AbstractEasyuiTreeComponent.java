package com.example.oauth.server.common.vo.tree;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/***
 *  一种安全的组合模式
 *  声明一个接口用于访问和管理AbstractEasyuiTreeComponent的子部件
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public abstract class AbstractEasyuiTreeComponent implements Serializable {

    private static final long serialVersionUID = 7634299960455347890L;
    private Long id;
    private String text;
    // open  展开
    // closed 折叠
    private String state;
    // ico 图标
    private  String iconCls;
    // true 选中
    // false 未选中
    private boolean checked;
    // 附加属性
    private Object attributes;

    /**
     * 业务方法
     */
    public abstract void operation();


}
