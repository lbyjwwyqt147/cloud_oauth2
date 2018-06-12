package com.example.oauth.server.web.account;

import com.example.oauth.server.common.restful.RestfulVo;
import com.example.oauth.server.common.restful.ResultUtil;
import com.example.oauth.server.domain.account.dto.AccountDTO;
import com.example.oauth.server.service.acount.SysAccountService;
import com.example.oauth.server.web.base.AbstractController;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

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
     * @param userAccount
     * @param userPwd
     * @return
     */
    @PostMapping("login/enter")
    public RestfulVo login(String userAccount, String userPwd){
        System.out.println("进入了 controller 中的登录方法 ............. ");
        return null;
    }

    /**
     * 找回密码
     * @param userAccount
     * @param userPwd
     * @return
     */
    @GetMapping("login/pwd")
    public RestfulVo retrievePassword(String userAccount, String userPwd){
        return null;
    }



}
