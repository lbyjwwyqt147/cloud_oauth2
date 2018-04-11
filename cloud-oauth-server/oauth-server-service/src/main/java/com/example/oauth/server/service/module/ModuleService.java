package com.example.oauth.server.service.module;

import com.example.oauth.server.domain.module.dto.SysModuleDTO;

/***
 *  资源菜单　service
 */
public interface ModuleService {

    /**
     * 保存数据
     * @param moduleDTO
     * @return
     */
    boolean saveModule(SysModuleDTO moduleDTO);

}
