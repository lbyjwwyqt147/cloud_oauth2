package com.example.oauth.server.common.vo.user;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;

@EqualsAndHashCode(callSuper = false)
@Data
public class UserDetail implements Serializable {

    private static final long serialVersionUID = 7921971562167237551L;

    private Long id;
    private String userAccount;    //帐号
    private String userName;       //名称
    private String userPwd;
    private Byte status;           //状态
    private String userEmail;      //邮箱
    private Integer bindingPhone;  //绑定手机
    private List<Module> modules;  //拥有的资源

}
