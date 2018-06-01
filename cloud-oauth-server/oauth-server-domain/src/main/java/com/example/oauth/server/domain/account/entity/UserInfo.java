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


  /*  @OneToOne(fetch = FetchType.LAZY,targetEntity = SysAccount.class,cascade = { CascadeType.ALL })
    @JoinColumn(name="account_id",insertable = false, updatable = false, nullable=true)
    private SysAccount account;*/

}
