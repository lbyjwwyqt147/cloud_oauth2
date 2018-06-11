package com.example.oauth.server.service.role;

import com.example.oauth.server.common.restful.RestfulVo;
import com.example.oauth.server.domain.base.PageVo;
import com.example.oauth.server.domain.role.dto.RoleDTO;
import com.example.oauth.server.domain.role.entity.SysRole;
import com.example.oauth.server.domain.role.query.RoleQuery;
import com.example.oauth.server.domain.role.vo.RoleVO;
import org.springframework.data.domain.Page;

import java.util.List;

/***
 *  角色 service
 */
public interface RoleService {

    /**
     * 保存数据
     * @param roleDTO
     * @return
     */
    boolean save(RoleDTO roleDTO);


    /**
     * 分页查询
     * @param roleQuery
     * @return
     */
    RestfulVo findListGridPage(RoleQuery roleQuery);

    /**
     * 根据userId 获取用户拥有的角色
     * @param userId
     * @return
     */
    List<SysRole> findByUserId(Long userId);

    /**
     * 获取所有分配的角色
     * @return
     */
    List<SysRole> findByRoleModule();

    /**
     * 根据ID 删除数据
     * @param id
     * @return
     */
    boolean singleDeleteById(Long id);
}
