package com.example.oauth.server.repository.role;

import com.example.oauth.server.domain.role.entity.SysUserRole;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

/***
 * 人员角色 Repository
 */
public interface UserRoleRepository  extends JpaRepository<SysUserRole,Long> {

    /**
     * 根据userId 获取所拥有的角色
     * @param userId
     * @return
     */
    List<SysUserRole> findByUserId(Long userId);

    /**
     *  根据 角色ID  获取角色人员
     * @param roleId
     * @return
     */
    List<SysUserRole> findByRoleId(Long roleId);

    /**
     *  删除角色绑定的人员
     * @param roleId  角色ID
     * @param userIds 人员ID 集合
     * @return
     */
    Long deleteByRoleIdAndUserIdIn(Long roleId,List<Long> userIds);

    /**
     * 根据 roleId 删除数据
     * @param roleId
     * @return
     */
    Long deleteByRoleId(Long roleId);

}
