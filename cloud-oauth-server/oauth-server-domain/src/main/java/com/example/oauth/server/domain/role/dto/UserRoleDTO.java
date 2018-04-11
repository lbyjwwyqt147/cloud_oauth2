package com.example.oauth.server.domain.role.dto;

import com.example.oauth.server.domain.base.AbstractDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/***
 * 人员角色 DTO
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserRoleDTO extends AbstractDTO {

    private Long userId;
    private Long roleId;

    private List<Long> roleIds;

}
