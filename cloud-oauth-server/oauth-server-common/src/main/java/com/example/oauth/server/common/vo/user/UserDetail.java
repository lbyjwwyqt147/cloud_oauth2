package com.example.oauth.server.common.vo.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.List;

@EqualsAndHashCode(callSuper = false)
@Data
public class UserDetail implements Serializable {

    private static final long serialVersionUID = 7921971562167237551L;

    private Long accountId;         //帐号ID
    private String userAccount;    //帐号
    @JsonIgnore
    private String userPwd;
    private Byte status;           //状态
    private String userEmail;      //邮箱
    private Integer bindingPhone;  //绑定手机
    private String token;          //用户token
    private Long userId;         //用户ID
    private String userName;       //用户名称
    private Instant lastPasswordResetDate;  //上次密码重置时间
    private Byte userSex;


}
