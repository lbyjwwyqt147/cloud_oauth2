package com.example.oauth.server.domain.account.dto;

import com.example.oauth.server.domain.base.AbstractDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.Instant;

/***
 * 账户 DTO
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AccountDTO extends AbstractDTO {

    private String userAccount;   //账号
    private String userPwd;       //密码
    private String userEmail;     //邮箱
    private Integer bindingPhone; //绑定电话
    private String userName;      //用户名称
    //上次密码重置时间
    private Instant lastPasswordResetDate;

}
