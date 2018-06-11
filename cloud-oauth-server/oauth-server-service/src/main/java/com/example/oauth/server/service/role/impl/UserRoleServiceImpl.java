package com.example.oauth.server.service.role.impl;

import com.example.oauth.server.common.restful.RestfulVo;
import com.example.oauth.server.common.restful.ResultUtil;
import com.example.oauth.server.common.util.DozerBeanMapperUtil;
import com.example.oauth.server.domain.account.entity.SysAccount;
import com.example.oauth.server.domain.account.entity.UserInfo;
import com.example.oauth.server.domain.account.vo.UserInfoVO;
import com.example.oauth.server.domain.role.dto.UserRoleDTO;
import com.example.oauth.server.domain.role.entity.SysRole;
import com.example.oauth.server.domain.role.entity.SysUserRole;
import com.example.oauth.server.domain.role.query.UserRoleQuery;
import com.example.oauth.server.domain.role.vo.RoleVO;
import com.example.oauth.server.domain.role.vo.UserRoleVO;
import com.example.oauth.server.repository.account.SysAccountRepository;
import com.example.oauth.server.repository.account.UserInfoRepository;
import com.example.oauth.server.repository.account.UserInfoResultSets;
import com.example.oauth.server.repository.role.RoleRepository;
import com.example.oauth.server.repository.role.UserRoleRepository;
import com.example.oauth.server.service.role.UserRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

/***
 * 人员角色 serviceImpl
 */
@Slf4j
@Service
public class UserRoleServiceImpl implements UserRoleService {
    @Autowired
    private UserRoleRepository userRoleRepository;
    @Autowired
    private SysAccountRepository accountRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserInfoRepository userInfoRepository;

    @Transactional
    @Override
    public boolean batchUserRoleSave(UserRoleDTO userRoleDTO) {
        boolean success = false;
        String[] roleIds = userRoleDTO.getRoleIds();
        boolean roleIdsEmpty = roleIds != null && roleIds.length > 0;
        if (roleIdsEmpty){
            List<SysUserRole> userRoleList = new LinkedList<>();
            for (String roleId : roleIds) {
                SysUserRole userRole = new SysUserRole();
                userRole.setRoleId(Long.valueOf(roleId));
               // userRole.setCreateId(1L);
                //userRole.setCreateTime(Instant.now());
                userRole.setUserId(userRole.getUserId());
                userRoleList.add(userRole);
            }
            success = this.userRoleRepository.saveAll(userRoleList) != null;
        }
        return success;
    }

    @Transactional
    @Override
    public boolean batchRoleUserSave(UserRoleDTO userRoleDTO) {
        boolean success = false;
        String[]  userIds = userRoleDTO.getUserIds();
        boolean userIdsEmpty = userIds != null && userIds.length > 0;
        if (userIdsEmpty){
            List<SysUserRole> userRoleList = new LinkedList<>();
            for(String userId : userIds){
                SysUserRole userRole = new SysUserRole();
                userRole.setUserId(Long.valueOf(userId));
                //userRole.setCreateId(1L);
               // userRole.setCreateTime(Instant.now());
                userRole.setRoleId(userRoleDTO.getRoleId());
                userRoleList.add(userRole);
            }
            success = this.userRoleRepository.saveAll(userRoleList) != null;
        }
        return success;
    }

    @Override
    public RestfulVo findPageByRoleId(UserRoleQuery userRoleQuery) {
        return bulidAccountPage((byte)0,userRoleQuery);
    }

    @Override
    public RestfulVo findPageByRoleIdEliminate(UserRoleQuery userRoleQuery) {
        return bulidAccountPage((byte)1,userRoleQuery);
    }

    @Transactional
    @Override
    public boolean deleteByRoleIdAndUserIdIn(Long roleId, List<Long> userIds) {
        Long resultNumber = this.userRoleRepository.deleteByRoleIdAndUserIdIn(roleId,userIds);
        boolean success = resultNumber > 0;
        return success;
    }

    @Override
    public UserRoleVO findUserAndRole(String username) {
        UserRoleVO userRoleVO =  new UserRoleVO();
        SysAccount account  = this.accountRepository.findByUserAccount(username);
        if(account != null){
            //UserInfo userInfo = account.getUserInfo();
            UserInfo userInfo = this.userInfoRepository.findByAccountId(account.getId());
            UserInfoVO userInfoVO = DozerBeanMapperUtil.copyProperties(userInfo,UserInfoVO.class);
            userInfoVO.setBindingPhone(account.getBindingPhone());
            userInfoVO.setUserAccount(account.getUserAccount());
            userInfoVO.setUserEmail(account.getUserEmail());
            userInfoVO.setUserPwd(account.getUserPwd());
            userInfoVO.setStatus(account.getStatus());
            userInfoVO.setAccountId(account.getId());
            userRoleVO.setUserInfo(userInfoVO);
            userRoleVO.setUserId(account.getId());
            List<SysRole> roles = this.roleRepository.findByUserId(account.getId());
            userRoleVO.setRoles(DozerBeanMapperUtil.copyProperties(roles,RoleVO.class));
        }
        return userRoleVO;
    }

    /**
     * 构建 分页数据
     * @param sign
     * @param userRoleQuery
     * @return
     */
    private RestfulVo bulidAccountPage(Byte sign,UserRoleQuery userRoleQuery){
        Long roleId = userRoleQuery.getRoleId();
        //生成Pageable变量
        Pageable pageable = PageRequest.of(userRoleQuery.getP().getPageNum()-1,userRoleQuery.getP().getPageSize());
        Page<UserInfoResultSets> accountPage = sign == 1 ? this.accountRepository.findPageByRoleIdEliminate(roleId,pageable) : this.accountRepository.findPageByRoleId(roleId,pageable);
        Long totalElements =  accountPage.getTotalElements();
        List<UserInfoVO> accountVOList = null;
        if(totalElements > 0){
            accountVOList  = DozerBeanMapperUtil.copyProperties(accountPage.getContent(),UserInfoVO.class);
        }
        RestfulVo restfulVo = ResultUtil.success(accountVOList);
        restfulVo.setTotalElements(totalElements);
        return restfulVo;
    }
}
