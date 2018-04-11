package com.example.oauth.server.domain.role.vo;

import com.example.oauth.server.domain.base.AbstractVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/***
 *  角色 VO
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class RoleVO extends AbstractVO {
    private String roleCode;
    private String roleName;
    private String roleDescription;
}
