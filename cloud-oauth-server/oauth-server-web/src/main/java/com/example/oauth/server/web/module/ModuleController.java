package com.example.oauth.server.web.module;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.oauth.server.common.restful.RestfulVo;
import com.example.oauth.server.common.restful.ResultUtil;
import com.example.oauth.server.common.vo.tree.AbstractComponent;
import com.example.oauth.server.common.vo.tree.Composite;
import com.example.oauth.server.common.vo.tree.Leaf;
import com.example.oauth.server.domain.module.dto.SysModuleDTO;
import com.example.oauth.server.domain.module.vo.AbstractModuleTree;
import com.example.oauth.server.service.module.ModuleService;
import com.example.oauth.server.web.base.AbstractController;
import com.fasterxml.jackson.annotation.JsonFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

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
        AbstractComponent root = new Composite(1L,"root","icon");
        //给root 节点添加2个叶子节点
        ((Composite) root).add(new Leaf(2L,"A","A"));
        ((Composite) root).add(new Leaf(3L,"b","b"));

        //生成根节点  test
        AbstractComponent test = new Composite(10L,"test","icon");
        //给root 节点添加2个叶子节点
        ((Composite) test).add(new Leaf(20L,"A0","A"));
        ((Composite) test).add(new Leaf(30L,"b0","b"));

        ((Composite) root).add(test);

        root.operation();

        System.out.println(JSON.toJSON(root));

        return ResultUtil.success(root);
    }

    @GetMapping("module/tree1")
    public String moduleTree1(){

        //生成根节点  root
        AbstractComponent root = new Composite(1L,"root","");
        //给root 节点添加2个叶子节点
        ((Composite) root).add(new Leaf(2L,"A","A"));
        ((Composite) root).add(new Leaf(3L,"b","b"));

        //生成根节点  test
        AbstractComponent test = new Composite(10L,"test","");
        //给root 节点添加2个叶子节点
        ((Composite) test).add(new Leaf(20L,"A0",""));
        ((Composite) test).add(new Leaf(30L,"b0",""));

        ((Composite) root).add(test);

        root.operation();

        System.out.println(JSON.toJSON(root));
        ArrayList<AbstractComponent> list = new ArrayList<>();
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
}
