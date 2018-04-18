package com.example.oauth.server.service.role;

import com.example.oauth.server.domain.account.entity.SysAccount;
import com.example.oauth.server.domain.account.vo.AccountVO;
import com.example.oauth.server.domain.base.PageVo;
import com.example.oauth.server.domain.role.dto.UserRoleDTO;
import com.example.oauth.server.domain.role.query.UserRoleQuery;
import org.springframework.data.domain.Pageable;

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

    PageVo<AccountVO> findPageByRoleId(UserRoleQuery userRoleQuery);

    PageVo<AccountVO> findPageByRoleIdEliminate(UserRoleQuery userRoleQuery);

}
