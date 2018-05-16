package com.example.oauth.server.domain.role.vo;

import com.example.oauth.server.domain.base.AbstractVO;
import com.example.oauth.server.domain.module.vo.SysModuleVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.LinkedList;
import java.util.List;

/***
 * 角色 资源 VO
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class RoleModuleVO extends AbstractVO {
    private Long roleId;
    private Long moduleId;
}
