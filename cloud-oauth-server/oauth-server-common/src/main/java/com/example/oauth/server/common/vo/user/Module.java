package com.example.oauth.server.common.vo.user;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = false)
@Data
public class Module implements Serializable {

    private static final long serialVersionUID = -6821793952585755897L;

    private Long id;
    private Long moduleCode;
    private String moduleName;
    private Byte moduleType;
    private Long modulePid;
    private String menuIcon;
    private String menuUrl;
    private String authorizedSigns;
    private Byte status;
}
