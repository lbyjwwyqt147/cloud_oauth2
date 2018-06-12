package com.example.oauth.server.repository.module;

import com.example.oauth.server.domain.module.entity.SysModule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/***
 * 资源模块　Reository
 */
public interface ModuleReository extends JpaRepository<SysModule,Long> {

    /**
     * 根据PID 获取全部资源数据
     * @param pid
     * @return
     */
    List<SysModule> findByModulePid(Long pid);

    /**
     * 根据PID 获取类型（module_type）为 目录、菜单 资源数据
     * @param modulePid  资源PID
     * @param moduleType    类型  1:目录  2：菜单   3：功能按钮
     * @return
     */
    List<SysModule> findByModulePidAndModuleTypeNot(Long modulePid,Byte moduleType);

    /**
     * 根据PID 获取类型（module_type）为 目录、菜单 资源数据
     * @param modulePid  资源PID
     * @param moduleType    类型  1:目录  2：菜单   3：功能按钮
     * @return
     */
    List<SysModule> findByModulePidAndModuleType(Long modulePid,Byte moduleType);

    /**
     * 根据userid 获取所属的资源菜单
     * @param userId userid
     * @return
     */
    @Query(value = "select m.* from sys_module as m inner join sys_role_module as rm on m.id = rm.module_id inner join sys_user_role ur on rm.role_id = ur.role_id where ur.user_id = :userId",nativeQuery = true)
    List<SysModule> findByUserModule(@Param("userId") Long userId);

    /**
     * 根据userid 获取所属的资源菜单
     * @param userId userid
     * @return
     */
    @Query(value = "select m.* from sys_module as m inner join sys_role_module as rm on m.id = rm.module_id inner join sys_user_role ur on rm.role_id = ur.role_id where ur.user_id = :userId and m.module_type != :moduleType",nativeQuery = true)
    List<SysModule> findByUserModuleNot(@Param("userId") Long userId,@Param("moduleType") Byte moduleType);

    /**
     * 根据userid 获取所属的资源菜单
     * @param userId userid
     * @return
     */
    @Query(value = "select m.* from sys_module as m inner join sys_role_module as rm on m.id = rm.module_id inner join sys_user_role ur on rm.role_id = ur.role_id where ur.user_id = :userId and m.module_type = :moduleType",nativeQuery = true)
    List<SysModule> findByUserModuleAAndModuleType(@Param("userId") Long userId,@Param("moduleType") Byte moduleType);

    /**
     * 查询角色资源(按钮)
     * @return
     */
    @Query(value = "select m.menu_url as menuUrl,rm.role_id as authorizedSigns from sys_module as m inner join sys_role_module as rm on m.id = rm.module_id where m.status = 1 and m.module_type = 3",nativeQuery = true)
    List<ModuleResultSets> findByRoleModule();

    /**
     * 根据资源类型获取资源列表(取反　!= )
     * @return
     */
    List<SysModule> findByModuleTypeNot(Byte type);

    /**
     * 根据 pid 删除数据
     * @param pid
     */
    void deleteByModulePid(Long pid);
}
