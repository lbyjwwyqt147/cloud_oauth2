package com.example.oauth.server.web.module;

import com.alibaba.fastjson.JSON;
import com.example.oauth.server.common.restful.RestfulVo;
import com.example.oauth.server.common.restful.ResultUtil;
import com.example.oauth.server.common.util.UserUtils;
import com.example.oauth.server.common.vo.tree.*;
import com.example.oauth.server.common.vo.user.UserDetail;
import com.example.oauth.server.domain.module.dto.SysModuleDTO;
import com.example.oauth.server.domain.module.vo.AbstractModuleTree;
import com.example.oauth.server.service.module.ModuleService;
import com.example.oauth.server.web.base.AbstractController;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

/***
 * 资源菜单　controller
 */
@RestController
@CrossOrigin
public class ModuleController extends AbstractController {

    @Autowired
    private ModuleService moduleService;
    @Autowired
    private UserUtils userUtils;

    /**
     * 保存数据
     * @param moduleDTO
     * @return
     */
    @PostMapping("module/post")
    public RestfulVo save(SysModuleDTO moduleDTO){
        boolean success = this.moduleService.saveModule(moduleDTO);
        return ResultUtil.restfulInfo(success);
    }

    /**
     * 根据ID 删除数据
     * @param id
     * @return
     */
    @DeleteMapping("module/del/single")
    public RestfulVo singleDeleteById(Long id){
        boolean success = this.moduleService.singleDeleteById(id);
        return ResultUtil.restfulInfo(success);
    }

    /**
     *  符合 treeGrid 结构的数据 （不分页）
     * @return
     */
    @GetMapping("module/tree/grid")
    public RestfulVo listTreeGrid(){
        List<AbstractModuleTree> treeList = this.moduleService.listTreeGrid();
        String treeJson = JSON.toJSONString(treeList);
        return ResultUtil.success(treeJson);
    }


    /**
     *  符合 tree 结构的数据(排除 按钮类型的数据)
     * @param  pid  pid
     * @return
     */
    @GetMapping("module/tree/combotree/{pid}")
    public RestfulVo moduleCombotree(@PathVariable Long pid){
        LinkedList<AbstractEasyuiTreeComponent> combotree =  new LinkedList<>();
        List<AbstractEasyuiTreeComponent> treeList = this.moduleService.moduleTree(pid);
        AbstractEasyuiTreeComponent treeComponent =  new EasyuiTreeComposite();
        treeComponent.setState("1");
        treeComponent.setId(0L);
        treeComponent.setText("菜单资源");
        ((EasyuiTreeComposite) treeComponent).setChildren(new LinkedList<>(treeList));
        combotree.add(treeComponent);
        String treeJson = JSON.toJSONString(combotree);
        return ResultUtil.success(treeJson);
    }

    /**
     *  获取符合 ztree 结构的数据(根据角色获取分配的资源 分配的资源设为选中状态)
     * @param roleId  角色ID
     * @return
     */
    @GetMapping("module/tree/role/{roleId}")
    public RestfulVo roleModuleTree(@PathVariable Long roleId){
        List<AbstractZTreeComponent> treeList = this.moduleService.roleModuleTree(0L,roleId);
        String treeJson = JSON.toJSONString(treeList);
        return ResultUtil.success(treeJson);
    }

    /**
     * 获取登录人拥有的资源菜单
     * @param request
     * @return
     */
    @GetMapping("module/tree/user")
    public RestfulVo userModuleTree(HttpServletRequest request){
        UserDetail userDetail = userUtils.getUser(this.getUserToken(request));
        if(userDetail != null){
            Long userId = userDetail.getAccountId();
            List<AbstractModuleTree> treeList = this.moduleService.userModuleTree(userId);
            String treeJson = JSON.toJSONString(treeList);
            return ResultUtil.success(treeJson);
        }
        return ResultUtil.error("1","无效的token，无法获取用户信息");

    }


}
