package com.example.oauth.server.web.role;

import com.example.oauth.server.common.restful.RestfulVo;
import com.example.oauth.server.common.restful.ResultUtil;
import com.example.oauth.server.domain.account.vo.AccountVO;
import com.example.oauth.server.domain.base.PageVo;
import com.example.oauth.server.domain.role.dto.UserRoleDTO;
import com.example.oauth.server.domain.role.query.UserRoleQuery;
import com.example.oauth.server.service.role.UserRoleService;
import com.example.oauth.server.web.base.AbstractController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/***
 * 人员角色  controller
 */
@RestController
@CrossOrigin
public class UserRoleController extends AbstractController {

    @Autowired
    private UserRoleService userRoleService;

    /**
     * 保存数据(一个用户保存多个角色)
     * @param userRoleDTO
     * @return
     */
    @PostMapping("userRole/batch/post")
    public RestfulVo batchUserRoleSave(UserRoleDTO userRoleDTO){
        boolean success  = this.userRoleService.batchUserRoleSave(userRoleDTO);
        return ResultUtil.restfulInfo(success);
    }

    /**
     * 保存数据（一个角色保存多个用户）
     * @param userRoleDTO
     * @return
     */
    @PostMapping("roleUser/batch/post")
    public RestfulVo batchRoleUserSave(UserRoleDTO userRoleDTO){
        boolean success  = this.userRoleService.batchRoleUserSave(userRoleDTO);
        return ResultUtil.restfulInfo(success);
    }

    /**
     *  删除角色分配的人员
     * @param roleId  角色ID
     * @param userIds 人员ID 集合
     * @return
     */
    @DeleteMapping("userRole/del")
    public RestfulVo deleteByRoleIdAndUserIdIn(Long roleId, Long[]  userIds){
        boolean success = this.userRoleService.deleteByRoleIdAndUserIdIn(roleId,Arrays.asList(userIds));
        return ResultUtil.restfulInfo(success);
    }

    /**
     *  更具角色ID 获取角色已经拥有的人员信息 列表
     * @param userRoleQuery
     * @return
     */
    @GetMapping("grid/role/user/have")
    public RestfulVo findUserByRoleId(UserRoleQuery userRoleQuery){
        return this.userRoleService.findPageByRoleId(userRoleQuery);
    }

    /**
     * 根据角色ID 获取角色未分配的人员信息列表
     * @param userRoleQuery
     * @return
     */
    @GetMapping("grid/role/user/not")
    public RestfulVo findPageByRoleIdEliminate(UserRoleQuery userRoleQuery){
        return this.userRoleService.findPageByRoleIdEliminate(userRoleQuery);
    }



}
