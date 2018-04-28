package com.example.oauth.server.repository.module;

import com.example.oauth.server.domain.module.entity.SysModule;
import org.springframework.data.jpa.repository.JpaRepository;

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

}
