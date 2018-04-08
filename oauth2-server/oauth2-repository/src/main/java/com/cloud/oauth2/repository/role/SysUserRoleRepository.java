package com.cloud.oauth2.repository.role;

import com.cloud.oauth2.role.entity.SysUserRoleDO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/***
 * 用户角色
 */
public interface SysUserRoleRepository extends JpaRepository<SysUserRoleDO,Long> {

    /**
     * 根据 用户ID 获取用户所属角色
     * @param userId  用户id
     * @return
     */
   // List<SysUserRole> findByUserId(Long userId);
}
