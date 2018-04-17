package com.example.oauth.server.domain.module.vo;

import com.example.oauth.server.domain.base.AbstractVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 资源模块 tree 结构 VO
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

    /**
     * 业务方法
     */
    public abstract  void operation();

}
