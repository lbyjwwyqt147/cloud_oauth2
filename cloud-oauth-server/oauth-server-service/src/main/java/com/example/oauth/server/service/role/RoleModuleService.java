package com.example.oauth.server.service.role;

import com.example.oauth.server.domain.role.dto.RoleModuleDTO;

/***
 * 角色资源 service
 */
public interface RoleModuleService {

    /**
     * 批量保存
     * @param roleModuleDTO
     * @return
     */
    boolean batchSave(RoleModuleDTO roleModuleDTO);


}
