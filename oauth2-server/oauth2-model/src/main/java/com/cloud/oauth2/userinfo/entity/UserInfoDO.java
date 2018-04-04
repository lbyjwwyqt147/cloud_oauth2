package com.cloud.oauth2.userinfo.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.envers.Audited;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.Instant;

/***
 * 用户信息 DO
 */
@Entity
@Audited
@EntityListeners(AuditingEntityListener.class)
@EqualsAndHashCode(callSuper = false)
@Data
public class UserInfoDO implements Serializable {

  /**
   *
   */
  private static final long serialVersionUID = -3463374005259705777L;
  @Id
  @GeneratedValue
  private Long id;
  private Long accountId;
  private String userName;
  private Byte userSex;
  @CreatedDate
  private Instant createTime = Instant.now();
  @CreatedBy
  private Long createId;
  @LastModifiedDate
  private Instant updateTime = Instant.now();
  @LastModifiedBy
  private Long updateId;



}
