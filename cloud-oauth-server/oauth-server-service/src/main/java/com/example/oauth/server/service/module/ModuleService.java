package com.example.oauth.server.service.module;

import com.example.oauth.server.common.vo.tree.AbstractEasyuiTreeComponent;
import com.example.oauth.server.common.vo.tree.AbstractZTreeComponent;
import com.example.oauth.server.domain.module.dto.SysModuleDTO;
import com.example.oauth.server.domain.module.entity.SysModule;
import com.example.oauth.server.domain.module.vo.AbstractModuleTree;
import org.springframework.data.repository.query.Param;

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
     * 根据ID 删除数据
     * @param id
     * @return
     */
    boolean singleDeleteById(Long id);

    /**
     * 获取符合treeGrid 结构的数据(不分页)
     * @return
     */
    List<AbstractModuleTree> listTreeGrid();

    /**
     *  获取符合 easyui tree 结构的数据
     * @param pid 资源pid
     * @return
     */
    List<AbstractEasyuiTreeComponent> moduleTree(Long pid);

    /**
     * 根据 userId 获取用户资源菜单tree
     * @param userId
     * @return
     */
    List<AbstractModuleTree> userModuleTree(Long userId);

    /**
     * 根据userId 获取用户资源菜单
     * @param userId
     * @return
     */
    List<SysModule> findByUserModule(Long userId);

    /**
     *  获取符合 ztree 结构的数据(根据角色获取分配的资源 分配的资源设为选中状态)
     * @param pid 资源pid
     * @param roleId  角色ID
     * @return
     */
    List<AbstractZTreeComponent> roleModuleTree(Long pid,Long roleId);

    /**
     * 获取所有资源（排除目录类型资源）
     * @return
     */
    List<SysModule> findAllModuleExcludeDirectory();

    /**
     * 查询角色资源
     * @return
     */
    List<SysModule> findByRoleModule();
}
