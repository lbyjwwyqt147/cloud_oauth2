package com.example.oauth.server.repository.module;

import com.example.oauth.server.domain.module.entity.SysModule;
import org.springframework.data.jpa.repository.JpaRepository;

/***
 * 资源模块　Reository
 */
public interface ModuleReository extends JpaRepository<SysModule,Long> {

}
