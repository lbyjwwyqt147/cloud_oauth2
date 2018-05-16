package com.example.oauth.server.web.account;

import com.example.oauth.server.common.restful.RestfulVo;
import com.example.oauth.server.common.restful.ResultUtil;
import com.example.oauth.server.domain.account.dto.AccountDTO;
import com.example.oauth.server.service.acount.SysAccountService;
import com.example.oauth.server.web.base.AbstractController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/***
 * 账户 controller
 */
@RestController
@CrossOrigin
public class SysAccountController extends AbstractController {

    @Autowired
    private SysAccountService accountService;

    /**
     * 新增信息
     * @param accountDTO
     * @return
     */
    @PostMapping("account")
    public RestfulVo saveAccount(AccountDTO accountDTO){
        boolean success = this.accountService.saveAccount(accountDTO);
        return ResultUtil.restfulInfo(success);
    }


}
