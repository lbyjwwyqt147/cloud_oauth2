package com.example.oauth.security.userdetails;

import com.example.oauth.server.domain.account.entity.SysAccount;
import com.example.oauth.server.domain.account.vo.AccountVO;
import com.example.oauth.server.domain.module.entity.SysModule;
import com.example.oauth.server.domain.role.entity.SysRole;
import com.example.oauth.server.domain.role.vo.RoleVO;
import com.example.oauth.server.domain.role.vo.UserRoleVO;
import com.example.oauth.server.service.acount.SysAccountService;
import com.example.oauth.server.service.module.ModuleService;
import com.example.oauth.server.service.role.RoleService;
import com.example.oauth.server.service.role.UserRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/***
 *
 * @FileName: MyUserDetailService
 * @Company:
 * @author    ljy
 * @Date      2018年05月11日
 * @version   1.0.0
 * @remark:   配置用户权限认证
 * @explain   当用户登录时会进入此类的loadUserByUsername方法对用户进行验证，验证成功后会被保存在当前回话的principal对象中
 *             系统获取当前登录对象信息方法 WebUserDetails webUserDetails = (WebUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
 *
 */
@Slf4j
@Component
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private ModuleService moduleService;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private RoleService roleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("登录用户：" + username);
        //用户用户信息和用户角色
        UserRoleVO userRole = this.userRoleService.findUserAndRole(username);
        if(userRole.getUserId() == null){
            throw new UsernameNotFoundException("用户：" + username + " 不存在");

        }
        //获取用户信息
        AccountVO account = userRole.getAccount();
        //获取用户角色
        List<RoleVO> roleList = userRole.getRoles();
        Set<GrantedAuthority> grantedAuths = new HashSet<GrantedAuthority>();
        if(roleList.size() > 0){
            roleList.stream().forEach(role ->{
                grantedAuths.add(new SimpleGrantedAuthority(role.getAuthorizedSigns()));
            });
        }
        User userDetail = new User(account.getUserAccount(),account.getUserPwd(),
                grantedAuths);



       /* //获取用户信息
        SysAccount account = this.accountService.findByAccount(username);
        if (account == null){
            throw new UsernameNotFoundException("用户：" + username + " 不存在");
        }
        //封装用户权限 到 SecurityContextHolder全局缓存中
        Collection<GrantedAuthority> grantedAuths = obtionGrantedAuthorities(account.getId());
        User userDetail = new User(account.getUserAccount(),account.getUserPwd(),
                grantedAuths);*/

        return userDetail;
    }


    /**
     * 根据用户ID 获取用户拥有的资源
     * @param userId
     * @return
     */
    private Set<GrantedAuthority> obtionGrantedAuthorities(Long userId){
        Set<GrantedAuthority> authSet = new HashSet<GrantedAuthority>();
        //获取用户资源
      /*  List<SysModule> moduleList = this.moduleService.findByUserModule(userId);
        if(moduleList != null && moduleList.size() > 0){
            moduleList.stream().forEach(item ->{
                authSet.add(new SimpleGrantedAuthority("ROLE_"+item.getAuthorizedSigns()));
            });
        }*/
        //获取用户拥有的角色
        List<SysRole> roleList = this.roleService.findByUserId(userId);
        if (roleList != null && !roleList.isEmpty()){
            roleList.stream().forEach(role ->{
                authSet.add(new SimpleGrantedAuthority(role.getAuthorizedSigns()));
            });
        }
        return authSet;
    }
}
