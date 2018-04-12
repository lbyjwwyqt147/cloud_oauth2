package com.example.oauth.server.web.role;

import com.example.oauth.server.common.restful.RestfulVo;
import com.example.oauth.server.common.restful.ResultUtil;
import com.example.oauth.server.domain.role.dto.UserRoleDTO;
import com.example.oauth.server.service.role.UserRoleService;
import com.example.oauth.server.web.base.AbstractController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/***
 * 人员角色  controller
 */
@RestController
@CrossOrigin
public class UserRoleController extends AbstractController {

    @Autowired
    private UserRoleService userRoleService;

    /**
     * 保存数据
     * @param userRoleDTO
     * @return
     */
    @PostMapping("userRole")
    public RestfulVo save(UserRoleDTO userRoleDTO){
        boolean success  = this.userRoleService.batchSave(userRoleDTO);
        return ResultUtil.restful(success);
    }
}
