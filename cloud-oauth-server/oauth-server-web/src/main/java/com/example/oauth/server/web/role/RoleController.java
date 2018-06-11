package com.example.oauth.server.web.role;

import com.example.oauth.server.common.redis.RedisUtil;
import com.example.oauth.server.common.restful.RestfulVo;
import com.example.oauth.server.common.restful.ResultUtil;
import com.example.oauth.server.domain.base.PageVo;
import com.example.oauth.server.domain.role.dto.RoleDTO;
import com.example.oauth.server.domain.role.query.RoleQuery;
import com.example.oauth.server.domain.role.vo.RoleVO;
import com.example.oauth.server.service.role.RoleService;
import com.example.oauth.server.web.base.AbstractController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/***
 * 角色 controller
 */
@RestController
public class RoleController extends AbstractController {

    @Autowired
    private RoleService roleService;

    /**
     * 新增数据
     * @param roleDTO
     * @return
     */
    @PostMapping("role/post")
    public RestfulVo saveRole(RoleDTO roleDTO){
        boolean success = this.roleService.save(roleDTO);
        return ResultUtil.restfulInfo(success);
    }

    /**
     * 根据ID 删除数据
     * @param id
     * @return
     */
    @DeleteMapping("role/del/single")
    public RestfulVo singleDeleteById(Long id){
        boolean success = this.roleService.singleDeleteById(id);
        return ResultUtil.restfulInfo(success);
    }

    /**
     * 分页数据
     * @param roleQuery
     * @return
     */
    @GetMapping("grid/role")
    public RestfulVo listGridPage(RoleQuery roleQuery){
       return this.roleService.findListGridPage(roleQuery);
    }
}
