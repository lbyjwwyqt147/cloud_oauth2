package com.example.oauth.server.domain.role.dto;

import com.example.oauth.server.domain.base.AbstractDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/***
 * 角色 DTO
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class RoleDTO extends AbstractDTO {
    private String roleCode;
    private String roleName;
    private String roleDescription;
}
