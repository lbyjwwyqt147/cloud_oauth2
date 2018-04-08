package com.cloud.oauth2.repository.role;

import com.cloud.oauth2.role.entity.SysRoleDO;
import org.springframework.data.jpa.repository.JpaRepository;

/***
 * 角色
 */
public interface SysRoleRepository extends JpaRepository<SysRoleDO,Long> {

}
