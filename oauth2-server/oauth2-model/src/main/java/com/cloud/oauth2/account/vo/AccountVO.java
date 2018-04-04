package com.cloud.oauth2.account.vo;

import com.cloud.oauth2.base.AbstractVO;
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
