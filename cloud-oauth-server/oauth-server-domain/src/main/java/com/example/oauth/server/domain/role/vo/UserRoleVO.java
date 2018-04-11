package com.example.oauth.server.domain.role.vo;

import com.example.oauth.server.domain.base.AbstractVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/***
 * 人员角色 VO
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserRoleVO extends AbstractVO {
    private Long userId;
    private Long roleId;
}
