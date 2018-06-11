package com.example.oauth.server.domain.account.vo;

import com.example.oauth.server.domain.base.AbstractVO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.Instant;

/***
 * 账户 VO
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AccountVO extends AbstractVO {

    private String userAccount;
    @JsonIgnore
    private String userPwd;
    private String userEmail;
    private Integer bindingPhone;
    //上次密码重置时间
    private Instant lastPasswordResetDate;
}
