package com.example.oauth.server.repository.role;

import com.example.oauth.server.domain.role.entity.SysRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/***
 * 角色 Repository
 */
public interface RoleRepository extends JpaRepository<SysRole,Long>,JpaSpecificationExecutor<SysRole> {


}
