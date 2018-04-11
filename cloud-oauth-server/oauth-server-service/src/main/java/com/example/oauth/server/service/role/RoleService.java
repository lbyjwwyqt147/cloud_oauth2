package com.example.oauth.server.service.role;

import com.example.oauth.server.domain.role.dto.RoleDTO;

/***
 *  角色 service
 */
public interface RoleService {

    /***
     * 保存数据
     * @param roleDTO
     * @return
     */
    boolean save(RoleDTO roleDTO);

}
