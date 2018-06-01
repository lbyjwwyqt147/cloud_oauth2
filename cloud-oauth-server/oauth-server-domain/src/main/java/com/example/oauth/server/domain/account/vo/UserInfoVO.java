package com.example.oauth.server.domain.account.vo;

import com.example.oauth.server.domain.base.AbstractVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.Instant;

/***
 * 用户信息 vo
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserInfoVO extends AbstractVO {

    private String userAccount;
    private String userPwd;
    private String userEmail;
    private Integer bindingPhone;
    //账号ID
    private Long accountId;
    private String userName;
    private Byte userSex;
    //上次密码重置时间
    private  Instant lastPasswordResetDate;



}
