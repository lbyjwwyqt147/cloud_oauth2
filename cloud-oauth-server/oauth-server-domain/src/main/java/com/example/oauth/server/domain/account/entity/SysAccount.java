package com.example.oauth.server.domain.account.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

/***
 *  账号 DO
 */
@Entity
//@Audited
@EntityListeners(AuditingEntityListener.class)
@EqualsAndHashCode(callSuper = false)
@Data
public class SysAccount implements Serializable{

    /**
     *
     */
    private static final long serialVersionUID = -6270972740177423520L;

    @Id
    @GeneratedValue
    private Long id;
    private String userAccount;
    private String userPwd;
    private Instant createTime;
    private Long createId;
    @LastModifiedDate
    private Instant updateTime;
    private Long updateId;
    private Byte status;
    private String userEmail;
    private Integer bindingPhone;
    //上次密码重置时间
    private  Instant lastPasswordResetDate;


   /* @OneToOne(fetch = FetchType.LAZY, cascade = { CascadeType.ALL },mappedBy="account")
    private UserInfo userInfo;*/


}
