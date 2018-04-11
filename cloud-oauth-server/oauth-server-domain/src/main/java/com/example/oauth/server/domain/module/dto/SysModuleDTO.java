package com.example.oauth.server.domain.module.dto;

import com.example.oauth.server.domain.base.AbstractDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 资源模块 DTO
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysModuleDTO extends AbstractDTO {
    private Long moduleCode;
    private String moduleName;
    private Byte moduleType;
    private Long modulePid;
    private Integer sequenceNumber;
    private String menuIcon;
    private String menuUrl;
    private String authorizedSigns;

}
