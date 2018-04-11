package com.example.oauth.server.domain.role.dto;

import com.example.oauth.server.domain.base.AbstractDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/***
 * 角色资源 DTO
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class RoleModuleDTO extends AbstractDTO {
    private Long roleId;
    private Long moduleId;

    private List<Long> moduleIds;

}
