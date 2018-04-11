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


}
