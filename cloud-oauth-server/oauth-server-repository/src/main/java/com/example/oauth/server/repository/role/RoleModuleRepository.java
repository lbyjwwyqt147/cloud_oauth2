package com.example.oauth.server.repository.role;

import com.example.oauth.server.domain.role.entity.SysRoleModule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


/***
 * 角色资源 Repository
 */
public interface RoleModuleRepository extends JpaRepository<SysRoleModule,Long> {

    /**
     *  删除 角色 分配的 资源
     * @param roleId  角色ID
     * @param moduleList  资源ID
     * @return
     */
      Long deleteByRoleIdAndModuleIdIn(Long roleId, List<Long> moduleList);

    /**
     * 删除角色资源
     * @param roleId 角色ID
     * @return
     */
      Long deleteByRoleId(Long roleId);

    /**
     *  根据角色ID 获取角色分配的 资源ID
     * @param roleId  角色ID
     * @return
     */
    @Query(value = "select module_id from sys_role_module where role_id = :roleId",nativeQuery = true)
    List<Long> findModuleIdByRoleId(@Param("roleId") Long roleId);

    /**
     * 根据 moduleId 删除数据
     * @param moduleId
     * @return
     */
    Long deleteByModuleId(Long moduleId);

}
