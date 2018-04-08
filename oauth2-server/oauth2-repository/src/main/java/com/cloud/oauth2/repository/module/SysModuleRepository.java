package com.cloud.oauth2.repository.module;

import com.cloud.oauth2.module.entity.SysModuleDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/***
 *  资源模块
 */
public interface SysModuleRepository extends JpaRepository<SysModuleDO,Long> {

   /* List<SysModule> findByStatusAndModuleType(Byte status,Byte moduleType);

    @Query("select m from sys_module as m inner join  sys_role_module as rm on m.id = rm.module_id  inner join sys_user_role as ur on ur.role_id = rm.role_id  where ur.user_id = ?1")
    List<SysModule> findDistinctModuleList(Long userId);*/

}
