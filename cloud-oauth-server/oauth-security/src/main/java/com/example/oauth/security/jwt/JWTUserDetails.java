package com.example.oauth.security.jwt;

import com.example.oauth.server.common.vo.user.UserDetail;
import com.example.oauth.server.domain.account.vo.UserInfoVO;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Instant;
import java.util.Collection;

/***
 *
 * @FileName: JWTUserDetails
 * @Company:
 * @author    ljy
 * @Date      2018年05月120日
 * @version   1.0.0
 * @remark:   jwt用户信息
 * @explain   Spring Security需要我们实现几个东西，第一个是UserDetails：这个接口中规定了用户的几个必须要有的方法，所以我们创建一个JwtUser类来实现这个接口。为什么不直接使用User类？因为这个UserDetails完全是为了安全服务的，它和我们的领域类可能有部分属性重叠，但很多的接口其实是安全定制的，所以最好新建一个类：
 *
 */
public class JWTUserDetails implements UserDetails {

    private Long userId;         //用户ID
    private String password;       //用户密码
    private final String username; //用户名
    private final Collection<? extends GrantedAuthority> authorities;  //用户角色权限
    private final Boolean isAccountNonExpired;       //账号是否过期
    private final Boolean isAccountNonLocked;        //账户是否锁定
    private final Boolean isCredentialsNonExpired;   //密码是否过期
    private  Boolean enabled;                   //是否激活
    private final Instant lastPasswordResetDate;        //上次密码重置时间
    private UserDetail userInfo;

    public JWTUserDetails(Long userId, String username, String password, Collection<? extends GrantedAuthority> authorities,Instant lastPasswordResetDate) {
        this(userId, username, password, true, true, true, true, authorities,lastPasswordResetDate);
    }

    public JWTUserDetails(UserDetail userInfo, Collection<? extends GrantedAuthority> authorities) {
        this.userInfo = userInfo;
        if (userInfo != null && StringUtils.isNotBlank(userInfo.getUserAccount())) {
            this.userId = userInfo.getAccountId();
            this.username = userInfo.getUserAccount();
            this.password = userInfo.getUserPwd();
            this.enabled = userInfo.getStatus() == 0 ? false : true;
            this.isAccountNonExpired = true;
            this.isAccountNonLocked = true;
            this.isCredentialsNonExpired = true;
            this.authorities = authorities;
            this.lastPasswordResetDate = userInfo.getLastPasswordResetDate();
        } else {
            throw new IllegalArgumentException("Cannot pass null or empty values to constructor");
        }
    }

    public JWTUserDetails(Long userId, String username, String password, boolean enabled, boolean isAccountNonExpired, boolean isCredentialsNonExpired, boolean isAccountNonLocked, Collection<? extends GrantedAuthority> authorities,Instant lastPasswordResetDate) {
        if (username != null && !"".equals(username) && password != null) {
            this.userId = userId;
            this.username = username;
            this.password = password;
            this.enabled = enabled;
            this.isAccountNonExpired = isAccountNonExpired;
            this.isAccountNonLocked = isAccountNonLocked;
            this.isCredentialsNonExpired = isCredentialsNonExpired;
            this.authorities = authorities;
            this.lastPasswordResetDate = lastPasswordResetDate;
        } else {
            throw new IllegalArgumentException("Cannot pass null or empty values to constructor");
        }
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return password;
    }


    @Override
    public String getUsername() {
        return username;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @JsonIgnore
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @JsonIgnore
    public Instant getLastPasswordResetDate() {
        return lastPasswordResetDate;
    }

    public UserDetail getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserDetail userInfo) {
        this.userInfo = userInfo;
    }
}
