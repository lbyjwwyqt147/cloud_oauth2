package com.example.oauth.server.domain.account.vo;

import com.example.oauth.server.domain.base.AbstractVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/***
 * 账户 VO
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AccountVO extends AbstractVO {

    private String userAccount;
    private String userPwd;
    private String userEmail;
    private Integer bindingPhone;
}
