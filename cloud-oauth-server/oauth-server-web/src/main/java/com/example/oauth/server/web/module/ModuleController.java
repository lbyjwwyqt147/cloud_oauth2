package com.example.oauth.server.web.module;

import com.alibaba.fastjson.JSON;
import com.example.oauth.server.common.restful.RestfulVo;
import com.example.oauth.server.common.restful.ResultUtil;
import com.example.oauth.server.common.vo.tree.AbstractEasyuiTreeComponent;
import com.example.oauth.server.common.vo.tree.AbstractZTreeComponent;
import com.example.oauth.server.common.vo.tree.ZTreeComposite;
import com.example.oauth.server.common.vo.tree.ZTreeLeaf;
import com.example.oauth.server.domain.module.dto.SysModuleDTO;
import com.example.oauth.server.domain.module.vo.AbstractModuleTree;
import com.example.oauth.server.service.module.ModuleService;
import com.example.oauth.server.web.base.AbstractController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/***
 * 资源菜单　controller
 */
@RestController
@CrossOrigin
public class ModuleController extends AbstractController {

    @Autowired
    private ModuleService moduleService;

    /**
     * 保存数据
     * @param moduleDTO
     * @return
     */
    @PostMapping("module")
    public RestfulVo save(SysModuleDTO moduleDTO){
        boolean success = this.moduleService.saveModule(moduleDTO);
        return ResultUtil.restful(success);
    }

    /**
     * 资源菜单树(全部)
     * @return
     */
    @GetMapping("module/tree")
    public RestfulVo moduleTree(){

        //生成根节点  root
        AbstractZTreeComponent root = new ZTreeComposite(1L,"root","icon");
        //给root 节点添加2个叶子节点
        ((ZTreeComposite) root).add(new ZTreeLeaf(2L,"A","A"));
        ((ZTreeComposite) root).add(new ZTreeLeaf(3L,"b","b"));

        //生成根节点  test
        AbstractZTreeComponent test = new ZTreeComposite(10L,"test","icon");
        //给root 节点添加2个叶子节点
        ((ZTreeComposite) test).add(new ZTreeLeaf(20L,"A0","A"));
        ((ZTreeComposite) test).add(new ZTreeLeaf(30L,"b0","b"));

        ((ZTreeComposite) root).add(test);

        root.operation();

        System.out.println(JSON.toJSON(root));

        return ResultUtil.success(root);
    }

    @GetMapping("module/tree1")
    public String moduleTree1(){

        //生成根节点  root
        AbstractZTreeComponent root = new ZTreeComposite(1L,"root","");
        //给root 节点添加2个叶子节点
        ((ZTreeComposite) root).add(new ZTreeLeaf(2L,"A","A"));
        ((ZTreeComposite) root).add(new ZTreeLeaf(3L,"b","b"));

        //生成根节点  test
        AbstractZTreeComponent test = new ZTreeComposite(10L,"test","");
        //给root 节点添加2个叶子节点
        ((ZTreeComposite) test).add(new ZTreeLeaf(20L,"A0",""));
        ((ZTreeComposite) test).add(new ZTreeLeaf(30L,"b0",""));

        ((ZTreeComposite) root).add(test);

        root.operation();

        System.out.println(JSON.toJSON(root));
        ArrayList<AbstractZTreeComponent> list = new ArrayList<>();
        list.add(root);
        return JSON.toJSON(list).toString();
    }


    /**
     *  符合 treeGrid 结构的数据 （不分页）
     * @return
     */
    @GetMapping("module/tree/grid")
    public String listTreeGrid(){
        List<AbstractModuleTree> treeList = this.moduleService.listTreeGrid();
        String treeJson = JSON.toJSONString(treeList);
        return treeJson;
    }

    @GetMapping("module/tree/grid1")
    public RestfulVo listTreeGrid1(){
        List<AbstractModuleTree> treeList = this.moduleService.listTreeGrid();
        return ResultUtil.success(treeList);
    }

    /**
     *  符合 tree 结构的数据(排除 按钮类型的数据)
     * @return
     */
    @GetMapping("module/tree/{pid}")
    public String moduleTree(@PathVariable Long pid){
        List<AbstractEasyuiTreeComponent> treeList = this.moduleService.moduleTree(pid);
        String treeJson = JSON.toJSONString(treeList);
        return treeJson;
    }

    /**
     *  获取符合 ztree 结构的数据(根据角色获取分配的资源 分配的资源设为选中状态)
     * @param roleId  角色ID
     * @return
     */
    @GetMapping("module/role/tree/{roleId}")
    public String roleModuleTree(@PathVariable Long roleId){
        List<AbstractZTreeComponent> treeList = this.moduleService.roleModuleTree(0L,roleId);
        String treeJson = JSON.toJSONString(treeList);
        return treeJson;
    }

    /**
     * 获取登录人的资源菜单
     * @param token
     * @return
     */
    @GetMapping("module/user/module/tree")
    public String userModuleTree(String token){
        Long userId = 68L;
        List<AbstractModuleTree> treeList = this.moduleService.userModuleTree(userId);
        String treeJson = JSON.toJSONString(treeList);
        return treeJson;
    }


}
