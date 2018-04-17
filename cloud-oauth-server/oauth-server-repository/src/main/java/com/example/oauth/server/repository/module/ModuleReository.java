package com.example.oauth.server.repository.module;

import com.example.oauth.server.domain.module.entity.SysModule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/***
 * 资源模块　Reository
 */
public interface ModuleReository extends JpaRepository<SysModule,Long> {

    /**
     * 根据PID 获取数据
     * @param pid
     * @return
     */
    List<SysModule> findByModulePid(Long pid);

}
