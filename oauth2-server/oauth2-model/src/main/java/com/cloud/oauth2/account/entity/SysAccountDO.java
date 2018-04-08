package com.cloud.oauth2.account.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.envers.Audited;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.Instant;

/***
 *  账号 DO
 */
@Entity
@Audited
@EntityListeners(AuditingEntityListener.class)
@EqualsAndHashCode(callSuper = false)
@Data
public class SysAccountDO implements Serializable{

  /**
   *
   */
  private static final long serialVersionUID = -6270972740177423520L;

  @Id
  @GeneratedValue
  private Long id;
  private String userAccount;
  private String userPwd;
  @CreatedDate
  private Instant createTime = Instant.now();
  private Long createId;
  @LastModifiedDate
  private Instant updateTime = Instant.now();
  private Long updateId;
  private Byte status;
  private String userEmail;
  private Integer bindingPhone;

}
