package com.example.oauth.server.service.role;

import com.example.oauth.server.domain.role.dto.RoleModuleDTO;
import com.example.oauth.server.domain.role.vo.RoleModuleVO;

import java.util.List;

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

    /**
     *  删除 角色 分配的 资源
     * @param roleId  角色ID
     * @param moduleList  资源ID
     * @return
     */
    boolean deleteByRoleIdAndModuleIdIn(Long roleId, List<Long> moduleList);


}
