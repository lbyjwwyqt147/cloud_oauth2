package com.example.oauth.server.web.account;

import com.example.oauth.server.common.restful.RestfulVo;
import com.example.oauth.server.common.restful.ResultUtil;
import com.example.oauth.server.domain.account.dto.AccountDTO;
import com.example.oauth.server.service.acount.SysAccountService;
import com.example.oauth.server.web.base.AbstractController;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/***
 *
 * @FileName: LoginController
 * @Company:
 * @author    ljy
 * @Date      2018年05月11日
 * @version   1.0.0
 * @remark:   注册、登录、找回密码 Controller
 * @explain
 *
 */
@RestController
public class LoginController extends AbstractController {

    @Autowired
    private SysAccountService accountService;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private HttpServletRequest request;

    /**
     * 注册账号
     * @param accountDTO
     * @return
     */
    @PostMapping("login/account")
    public RestfulVo registerAccount(AccountDTO accountDTO){
        //进行加密
       /* BCryptPasswordEncoder encoder =new BCryptPasswordEncoder();
        String password = encoder.encode(accountDTO.getUserPwd());
        accountDTO.setUserPwd(password);*/
        boolean success = this.accountService.saveAccount(accountDTO);
        return ResultUtil.restfulInfo(success);
    }

    /**
     * 登录
     * @param userAccount  登录账号
     * @param userPwd   登录密码
     * @param verifyCode  验证码
     * @return
     */
    @PostMapping("login/enter")
    public RestfulVo login(String userAccount, String userPwd,String verifyCode ){
        System.out.println("进入了 controller 中的登录方法 ............. ");
        if(StringUtils.isBlank(verifyCode) || !verifyCode.trim().equals("1234")){
            return ResultUtil.fail("验证码错误.");
        }
        //登录 身份认证
        // 这句代码会自动执行咱们自定义的 "MyUserDetailService.java" 身份认证类
        //1: 将用户名和密码封装成UsernamePasswordAuthenticationToken  new UsernamePasswordAuthenticationToken(userAccount, userPwd)
        //2: 将UsernamePasswordAuthenticationToken传给AuthenticationManager进行身份认证   authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userAccount, userPwd));
        //3: 认证完毕，返回一个认证后的身份： Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userAccount, userPwd));
        //4: 认证后，存储到SecurityContext里   SecurityContext securityContext = SecurityContextHolder.getContext();securityContext.setAuthentication(authentication);

        //UsernamePasswordAuthenticationToken继承AbstractAuthenticationToken实现Authentication
        //当在页面中输入用户名和密码之后首先会进入到UsernamePasswordAuthenticationToken验证(Authentication)，注意用户名和登录名都是页面传来的值
        //然后生成的Authentication会被交由AuthenticationManager来进行管理
        //而AuthenticationManager管理一系列的AuthenticationProvider，
        //而每一个Provider都会通UserDetailsService和UserDetail来返回一个
        //以UsernamePasswordAuthenticationToken实现的带用户名和密码以及权限的Authentication
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userAccount, userPwd));
            //将身份 存储到SecurityContext里
            SecurityContext securityContext = SecurityContextHolder.getContext();
            securityContext.setAuthentication(authentication);
            request.getSession().setAttribute("SPRING_SECURITY_CONTEXT", securityContext); // 这个非常重要，否则验证后将无法登陆
            return ResultUtil.success("登录成功.");
        }catch (AuthenticationException e){
            e.printStackTrace();
            return ResultUtil.fail("用户或者密码错误.");
        }
    }

    /**
     * 找回密码
     * @param userAccount
     * @param userPwd
     * @return
     */
    @GetMapping("login/retrieve/pwd")
    public RestfulVo retrievePassword(String userAccount, String userPwd){
        System.out.println("进入找回密码方法........................");
        return ResultUtil.success("找回密码成功.");
    }



}
