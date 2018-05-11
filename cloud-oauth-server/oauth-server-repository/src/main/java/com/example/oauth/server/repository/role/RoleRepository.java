package com.example.oauth.server.repository.role;

import com.example.oauth.server.domain.role.entity.SysRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/***
 * 角色 Repository
 */
public interface RoleRepository extends JpaRepository<SysRole,Long>,JpaSpecificationExecutor<SysRole> {

    /**
     * 根据userId 获取用户拥有的角色
     * @param userId
     * @return
     */
    @Query(value = "select r.* from sys_role as r inner join sys_user_role as ur on r.id = ur.role_id  where ur.user_id = ?userId",nativeQuery = true)
    List<SysRole> findByUserId(@Param("userId") Long userId);

}
