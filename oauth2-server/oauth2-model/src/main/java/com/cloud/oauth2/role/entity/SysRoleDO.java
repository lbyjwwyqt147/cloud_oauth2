package com.cloud.oauth2.role.entity;

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
 * 角色 DO
 */
@Entity
@Audited
@EntityListeners(AuditingEntityListener.class)
@EqualsAndHashCode(callSuper = false)
@Data
public class SysRoleDO implements Serializable{

  /**
   *
   */
  private static final long serialVersionUID = 6552743698756200105L;

  @Id
  @GeneratedValue
  private Long id;
  private String roleCode;
  private String roleName;
  private String roleDescription;
  @CreatedDate
  private Instant createTime = Instant.now();
  @CreatedBy
  private Long createId;
  @LastModifiedDate
  private Instant updateTime = Instant.now();
  @LastModifiedBy
  private Long updateId;
  private Byte status;


}
