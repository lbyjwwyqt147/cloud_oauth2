package com.example.oauth.server.service.role.impl;

import com.example.oauth.server.common.paging.Criteria;
import com.example.oauth.server.common.paging.Restrictions;
import com.example.oauth.server.common.util.DozerBeanMapperUtil;
import com.example.oauth.server.domain.base.PageVo;
import com.example.oauth.server.domain.role.dto.RoleDTO;
import com.example.oauth.server.domain.role.entity.SysRole;
import com.example.oauth.server.domain.role.query.RoleQuery;
import com.example.oauth.server.domain.role.vo.RoleVO;
import com.example.oauth.server.repository.role.RoleRepository;
import com.example.oauth.server.service.role.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

/***
 * 角色 serviceImpl
 */
@Slf4j
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Transactional
    @Override
    public boolean save(RoleDTO roleDTO) {
        SysRole role = DozerBeanMapperUtil.copyProperties(roleDTO,SysRole.class);
        role.setStatus((byte)1);
        role.setAuthorizedSigns("ROLE_"+roleDTO.getAuthorizedSigns().toUpperCase());
        role.setCreateTime(Instant.now());
        this.roleRepository.save(role);
        Long resultId = role.getId();
        boolean success = resultId != null && resultId > 0;
        return success;
    }

    @Override
    public PageVo<RoleVO> findListPage(RoleQuery roleQuery){
        Criteria<SysRole> criteria = new Criteria<>();
        criteria.add(Restrictions.eq("roleCode", roleQuery.getRoleCode()));
        criteria.add(Restrictions.eq("roleName", roleQuery.getRoleName()));
        //生成Sort变量
        Sort sort =  new Sort(Sort.Direction.DESC, "id");
        //生成Pageable变量
        Pageable pageable = PageRequest.of(roleQuery.getP().getPageNum()-1,roleQuery.getP().getPageSize(),sort);
        Page<SysRole> listPage = this.roleRepository.findAll(criteria,pageable);
        Long totalElements =  listPage.getTotalElements();
        PageVo<RoleVO> pageVo = new PageVo<>(roleQuery.getP().getPageNum(),pageable.getPageSize(),totalElements);
        if (totalElements > 0){
            List<RoleVO> roleVOList  = DozerBeanMapperUtil.copyProperties(listPage.getContent(),RoleVO.class);
            pageVo.setData(roleVOList);
        }
        return pageVo;
    }

    @Override
    public List<SysRole> findByUserId(Long userId) {
        return this.roleRepository.findByUserId(userId);
    }

    @Override
    public List<SysRole> findByRoleModule() {
        return this.roleRepository.findByRoleModule();
    }

    /**
     *  将目标对象值 拷贝到 SysRole
     * @param source
     * @return
     */
    private SysRole copyProperties(Object source){
        Long start1 = System.currentTimeMillis();
        SysRole role = new SysRole();
        BeanUtils.copyProperties(source,role);
        Long n = System.currentTimeMillis() - start1;
        System.out.println("BeanUtils 对象拷贝花费："+n +"毫秒");


        Long start4 = System.currentTimeMillis();
        SysRole role1 = DozerBeanMapperUtil.copyProperties(source,SysRole.class);
        Long n4 = System.currentTimeMillis() - start4;
        System.out.println( role1.getRoleCode() +" = DozerBeanMapperUtil 反射 对象拷贝花费："+n4 +"毫秒");



        return role;
    }

}
