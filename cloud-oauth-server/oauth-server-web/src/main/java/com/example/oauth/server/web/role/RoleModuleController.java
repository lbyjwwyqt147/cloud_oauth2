package com.example.oauth.server.web.role;

import com.example.oauth.server.common.restful.RestfulVo;
import com.example.oauth.server.common.restful.ResultUtil;
import com.example.oauth.server.domain.role.dto.RoleModuleDTO;
import com.example.oauth.server.service.role.RoleModuleService;
import com.example.oauth.server.web.base.AbstractController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/***
 * 角色资源 controller
 */
@RestController
public class RoleModuleController extends AbstractController {

    @Autowired
    private RoleModuleService roleModuleService;

    /**
     * 保存数据
     * @param roleModuleDTO
     * @return
     */
    @PostMapping("roleModule")
    public RestfulVo save(RoleModuleDTO roleModuleDTO){
       boolean success = this.roleModuleService.batchSave(roleModuleDTO);
       return ResultUtil.restful(success);
    }
}
