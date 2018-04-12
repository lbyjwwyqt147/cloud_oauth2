package com.example.oauth.server.service.role.impl;

import com.example.oauth.server.common.paging.Criteria;
import com.example.oauth.server.common.paging.Restrictions;
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
        SysRole role = this.copyProperties(roleDTO);
        role.setStatus((byte)1);
        role.setCreateTime(Instant.now());
        role.setId(1L);
        this.roleRepository.save(role);
        return true;
    }

    @Override
    public PageVo<RoleVO> findListPage(RoleQuery roleQuery){
        PageVo<RoleVO> pageVo = new PageVo<>();
        Criteria<SysRole> criteria = new Criteria<>();
        criteria.add(Restrictions.eq("roleCode", roleQuery.getRoleCode()));
        criteria.add(Restrictions.eq("roleName", roleQuery.getRoleName()));
        //生成Sort变量
        Sort sort =  new Sort(Sort.Direction.DESC, "id");
        //生成Pageable变量
        Pageable pageable = PageRequest.of(roleQuery.getPage()-1,roleQuery.getPageSize(),sort);
        Page<SysRole> listPage = this.roleRepository.findAll(criteria,pageable);
        if (listPage.getTotalElements() > 0){
            List<RoleVO> roleVOList = new LinkedList<>();
            BeanUtils.copyProperties(listPage.getContent(),roleVOList);
            pageVo.setData(roleVOList);
            PageVo.Meta meta = new PageVo<>().new Meta();
            meta.setField("id");
            meta.setPage(pageable.getPageNumber());
            meta.setPages(pageable.getPageSize());
            meta.setTotal(listPage.getTotalElements());
            pageVo.setMeta(meta);

        }
        return pageVo;
    }

    /**
     *  将目标对象值 拷贝到 SysRole
     * @param source
     * @return
     */
    private SysRole copyProperties(Object source){
        SysRole role = new SysRole();
        BeanUtils.copyProperties(source,role);
        return role;
    }

}
