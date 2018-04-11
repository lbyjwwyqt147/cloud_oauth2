package com.example.oauth.server.service.role;

import com.example.oauth.server.domain.role.dto.UserRoleDTO;

/***
 * 人员角色 service
 */
public interface UserRoleService {

    /**
     * 批量保存
     * @param userRoleDTO
     * @return
     */
    boolean batchSave(UserRoleDTO userRoleDTO);

}
