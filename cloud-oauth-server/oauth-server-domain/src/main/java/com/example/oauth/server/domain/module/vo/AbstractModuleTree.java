package com.example.oauth.server.domain.module.vo;

import com.example.oauth.server.domain.base.AbstractVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 资源模块 tree 结构 VO
 * 抽象构件角色Component：它为组合中的对象声明接口，也可以为共有接口实现缺省行为。
 */
@Data
@EqualsAndHashCode(callSuper = false)
public abstract class AbstractModuleTree extends AbstractVO {

    private Long moduleCode;
    private String moduleName;
    private Byte moduleType;
    private String moduleTypeText;
    private Long modulePid;
    private Integer sequenceNumber;
    private String menuIcon;
    private String menuUrl;
    private String authorizedSigns;
    // open  展开
    // closed 折叠
    private String state;

    /**
     * 业务方法
     */
    public abstract  void operation();

}
