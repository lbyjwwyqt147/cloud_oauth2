package com.example.oauth.server.domain.module.vo;

import com.example.oauth.server.domain.base.AbstractVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 资源模块 VO
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysModuleVO extends AbstractVO {
    private Long moduleCode;
    private String moduleName;
    private Byte moduleType;
    private Long modulePid;
    private Integer sequenceNumber;
    private String menuIcon;
    private String menuUrl;
    private String authorizedSigns;
}
