package com.example.oauth.server.domain.account.entity;

import com.example.oauth.server.domain.base.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

/**
 *　用户信息
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class UserInfo extends AbstractEntity {


    @Id
    @GeneratedValue
    private Long id;
    //账号关联ID
    private Long accountId;
    private String userName;
    private Byte userSex;

    @OneToOne(fetch = FetchType.LAZY, cascade = { CascadeType.ALL },mappedBy="userInfo")
    private SysAccount account;

}
