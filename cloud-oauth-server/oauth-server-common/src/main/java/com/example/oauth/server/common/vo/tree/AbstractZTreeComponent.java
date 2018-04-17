package com.example.oauth.server.common.vo.tree;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/***
 *  一种安全的组合模式
 *  声明一个接口用于访问和管理AbstractZTreeComponent的子部件
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public  abstract class AbstractZTreeComponent implements Serializable {


    private static final long serialVersionUID = 4689319284999357647L;

    //true 表示节点的输入框被勾选
    //false 表示节点的输入框未勾选
    private  boolean checked = false;

    //true 表示此节点的 checkbox / radio 被禁用。
    //false 表示此节点的 checkbox / radio 可以使用。
    private boolean chkDisabled = false;

    //标准 javascript 语法， 例如：alert("test"); 等
    private String click;

    //true 表示节点的输入框 强行设置为半勾选
    //false 表示节点的输入框 根据 zTree 的规则自动计算半勾选状态
    private  boolean halfCheck = false;

    //图标图片的 url 可以是相对路径也可以是绝对路径
    private String icon;

    //节点折叠时展示的图片url
    private String iconClose;
    //节点展开时展示的图片url
    private String iconOpen;

    //true 表示被隐藏
    //false 表示被显示
    private  boolean isHidden;

    //true 表示是父节点
    //false 表示不是父节点
    private boolean isParent;


    // true 表示此节点不显示 checkbox / radio，不影响勾选的关联关系，不影响父节点的半选状态。
    //false 表示节点具有正常的勾选功能
    private boolean nocheck = false;

    //true 表示节点为 展开 状态
    //false 表示节点为 折叠 状态
    private boolean open;

    //同超链接 target 属性: "_blank", "_self" 或 其他指定窗口名称
    //省略此属性，则默认为 "_blank"
    private String target;

    //同超链接 href 属性
    private String url;

    //节点id
    private Long id;

    //节点显示的名称字符串
    private String name;

    public AbstractZTreeComponent(Long id, String name, String icon){
        this.icon = icon;
        this.id =  id;
        this.name = name;
    }

    /**
     * 业务方法
     */
    public abstract void operation();
}
