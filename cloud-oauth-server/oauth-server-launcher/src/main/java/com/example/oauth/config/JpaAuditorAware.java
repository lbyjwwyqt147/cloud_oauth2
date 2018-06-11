package com.example.oauth.config;

import com.example.oauth.security.jwt.JWTUserDetails;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

/**
 *  设置默认用户
 */
@Configuration
public class JpaAuditorAware  implements AuditorAware<Long> {

    @Override
    public Optional<Long> getCurrentAuditor() {
        Optional<Long> opt = Optional.empty();
        SecurityContext ctx = SecurityContextHolder.getContext();
        if (ctx == null) {
            return opt;
        }
        if (ctx.getAuthentication() == null) {
            return opt;
        }
        if (ctx.getAuthentication().getPrincipal() == null) {
            return opt;
        }
        //获取当前登录人id
        JWTUserDetails userDetails = (JWTUserDetails) SecurityContextHolder.getContext().getAuthentication() .getPrincipal();
        if (userDetails != null) {
            Optional<Long> returnopt = Optional.of(userDetails.getUserId());
            return returnopt;
        } else {
            return opt;
        }
    }

}
