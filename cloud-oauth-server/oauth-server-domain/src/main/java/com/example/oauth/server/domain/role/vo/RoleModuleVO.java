package com.example.oauth.server.domain.role.vo;

import com.example.oauth.server.domain.base.AbstractVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/***
 * 角色 资源 VO
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class RoleModuleVO extends AbstractVO {
    private Long roleId;
    private Long moduleId;
}
