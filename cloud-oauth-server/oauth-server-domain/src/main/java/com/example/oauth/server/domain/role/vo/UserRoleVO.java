package com.example.oauth.server.domain.role.vo;

import com.example.oauth.server.domain.account.vo.AccountVO;
import com.example.oauth.server.domain.base.AbstractVO;
import com.example.oauth.server.domain.role.entity.SysRole;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/***
 * 人员角色 VO
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserRoleVO extends AbstractVO {
    private Long userId;
    private Long roleId;
    private List<RoleVO> roles = new LinkedList<>();
    private AccountVO account =  new AccountVO();

}
