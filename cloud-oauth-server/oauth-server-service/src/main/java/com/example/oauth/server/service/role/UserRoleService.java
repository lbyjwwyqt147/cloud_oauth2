package com.example.oauth.server.service.role;

import com.example.oauth.server.common.restful.RestfulVo;
import com.example.oauth.server.domain.account.entity.SysAccount;
import com.example.oauth.server.domain.account.vo.AccountVO;
import com.example.oauth.server.domain.base.PageVo;
import com.example.oauth.server.domain.role.dto.UserRoleDTO;
import com.example.oauth.server.domain.role.query.UserRoleQuery;
import com.example.oauth.server.domain.role.vo.UserRoleVO;
import org.springframework.data.domain.Pageable;

import java.util.List;

/***
 * 人员角色 service
 */
public interface UserRoleService {

    /**
     * 批量保存(根据人员保存多个角色)
     * @param userRoleDTO
     * @return
     */
    boolean batchUserRoleSave(UserRoleDTO userRoleDTO);

    /**
     * 批量保存(根据角色保存多个人员)
     * @param userRoleDTO
     * @return
     */
    boolean batchRoleUserSave(UserRoleDTO userRoleDTO);

    /**
     *  更具角色ID 获取角色已经拥有的人员信息 列表
     * @param userRoleQuery
     * @return
     */
    RestfulVo findPageByRoleId(UserRoleQuery userRoleQuery);

    /**
     * 根据角色ID 获取角色未分配的人员信息列表
     * @param userRoleQuery
     * @return
     */
    RestfulVo findPageByRoleIdEliminate(UserRoleQuery userRoleQuery);

    /**
     *  删除角色绑定的人员
     * @param roleId  角色ID
     * @param userIds 人员ID 集合
     * @return
     */
    boolean deleteByRoleIdAndUserIdIn(Long roleId,List<Long> userIds);

    /**
     * 根据登录名获取用户信息和用户角色信息
     * @param username
     * @return
     */
    UserRoleVO findUserAndRole(String username);

}
