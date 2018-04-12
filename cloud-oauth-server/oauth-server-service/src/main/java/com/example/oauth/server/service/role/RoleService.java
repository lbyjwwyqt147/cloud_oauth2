package com.example.oauth.server.service.role;

import com.example.oauth.server.domain.base.PageVo;
import com.example.oauth.server.domain.role.dto.RoleDTO;
import com.example.oauth.server.domain.role.query.RoleQuery;
import com.example.oauth.server.domain.role.vo.RoleVO;
import org.springframework.data.domain.Page;

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
    PageVo<RoleVO> findListPage(RoleQuery roleQuery);

}
