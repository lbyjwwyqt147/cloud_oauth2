package com.example.oauth.server.web.role;

import com.example.oauth.server.common.restful.RestfulVo;
import com.example.oauth.server.common.restful.ResultUtil;
import com.example.oauth.server.domain.role.dto.RoleModuleDTO;
import com.example.oauth.server.service.role.RoleModuleService;
import com.example.oauth.server.web.base.AbstractController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/***
 * 角色资源 controller
 */
@RestController
@CrossOrigin
public class RoleModuleController extends AbstractController {

    @Autowired
    private RoleModuleService roleModuleService;

    /**
     * 保存数据
     * @param roleModuleDTO
     * @return
     */
    @PostMapping("roleModule/batch/post")
    public RestfulVo save(RoleModuleDTO roleModuleDTO){
       boolean success = this.roleModuleService.batchSave(roleModuleDTO);
       return ResultUtil.restfulInfo(success);
    }

    /**
     *  删除 角色 分配的 资源
     * @param roleId  角色ID
     * @param moduleList  资源ID
     * @return
     */
    @DeleteMapping("roleModule/del")
    public RestfulVo deleteByRoleIdAndModuleIdIn(Long roleId, List<Long> moduleList){
        boolean success = this.roleModuleService.deleteByRoleIdAndModuleIdIn(roleId,moduleList);
        return ResultUtil.restfulInfo(success);
    }
}
