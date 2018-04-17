package com.example.oauth.server.service.module;

import com.example.oauth.server.common.vo.tree.AbstractEasyuiTreeComponent;
import com.example.oauth.server.domain.module.dto.SysModuleDTO;
import com.example.oauth.server.domain.module.vo.AbstractModuleTree;

import java.util.List;

/***
 *  资源菜单　service
 */
public interface ModuleService {

    /**
     * 保存数据
     * @param moduleDTO
     * @return
     */
    boolean saveModule(SysModuleDTO moduleDTO);

    /**
     * 获取符合treeGrid 结构的数据(不分页)
     * @return
     */
    List<AbstractModuleTree> listTreeGrid();

    /**
     *  获取符合tree 结构的数据
     * @param pid
     * @return
     */
    List<AbstractEasyuiTreeComponent> moduleTree(Long pid);

}
