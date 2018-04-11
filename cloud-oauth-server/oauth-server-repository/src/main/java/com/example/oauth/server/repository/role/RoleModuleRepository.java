package com.example.oauth.server.repository.role;

import com.example.oauth.server.domain.role.entity.SysRoleModule;
import org.springframework.data.jpa.repository.JpaRepository;


/***
 * 角色资源 Repository
 */
public interface RoleModuleRepository extends JpaRepository<SysRoleModule,Long> {



}
