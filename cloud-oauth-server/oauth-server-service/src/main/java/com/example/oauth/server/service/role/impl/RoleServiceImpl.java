package com.example.oauth.server.service.role.impl;

import com.example.oauth.server.common.paging.Criteria;
import com.example.oauth.server.common.paging.Restrictions;
import com.example.oauth.server.common.restful.RestfulVo;
import com.example.oauth.server.common.restful.ResultUtil;
import com.example.oauth.server.common.util.DozerBeanMapperUtil;
import com.example.oauth.server.domain.role.dto.RoleDTO;
import com.example.oauth.server.domain.role.entity.SysRole;
import com.example.oauth.server.domain.role.query.RoleQuery;
import com.example.oauth.server.domain.role.vo.RoleVO;
import com.example.oauth.server.repository.role.RoleModuleRepository;
import com.example.oauth.server.repository.role.RoleRepository;
import com.example.oauth.server.repository.role.UserRoleRepository;
import com.example.oauth.server.service.role.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/***
 * 角色 serviceImpl
 */
@Slf4j
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRoleRepository userRoleRepository;
    @Autowired
    private RoleModuleRepository roleModuleRepository;

    @Transactional
    @Override
    public boolean save(RoleDTO roleDTO) {
        SysRole role = DozerBeanMapperUtil.copyProperties(roleDTO,SysRole.class);
        role.setAuthorizedSigns("ROLE_"+roleDTO.getAuthorizedSigns().toUpperCase());
        this.roleRepository.save(role);
        Long resultId = role.getId();
        boolean success = resultId != null && resultId > 0;
        return success;
    }


    @Transactional
    @Override
    public boolean singleDeleteById(Long id) {
        try {
            //删除 关联的角色资源数据
            this.roleModuleRepository.deleteByRoleId(id);
            //删除 关联的角色人员数据
            this.userRoleRepository.deleteByRoleId(id);
            //删除角色数据
            this.roleRepository.deleteById(id);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public RestfulVo findListGridPage(RoleQuery roleQuery) {
        Criteria<SysRole> criteria = new Criteria<>();
        criteria.add(Restrictions.eq("roleCode", roleQuery.getRoleCode()));
        criteria.add(Restrictions.eq("roleName", roleQuery.getRoleName()));
        //生成Sort变量
        Sort sort =  new Sort(Sort.Direction.DESC, "id");
        //生成Pageable变量
        Pageable pageable = PageRequest.of(roleQuery.getP().getPageNum()-1,roleQuery.getP().getPageSize(),sort);
        Page<SysRole> listPage = this.roleRepository.findAll(criteria,pageable);
        Long totalElements =  listPage.getTotalElements();
        List<RoleVO> roleVOList = null;
        if (totalElements > 0){
            roleVOList  = DozerBeanMapperUtil.copyProperties(listPage.getContent(),RoleVO.class);
        }
        RestfulVo restfulVo = ResultUtil.success(roleVOList);
        restfulVo.setTotalElements(totalElements);
        return restfulVo;
    }

    @Override
    public List<SysRole> findByUserId(Long userId) {
        return this.roleRepository.findByUserId(userId);
    }

    @Override
    public List<SysRole> findByRoleModule() {
        return this.roleRepository.findByRoleModule();
    }


}
