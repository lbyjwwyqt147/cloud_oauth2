package com.example.oauth.server.domain.role.vo;

import com.example.oauth.server.domain.account.vo.UserInfoVO;
import com.example.oauth.server.domain.base.AbstractVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.LinkedList;
import java.util.List;

/***
 * 人员角色 VO
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserRoleVO extends AbstractVO {
    private Long userId;
    private Long roleId;
    private List<RoleVO> roles = new LinkedList<>();
    private UserInfoVO userInfo =  new UserInfoVO();

}
