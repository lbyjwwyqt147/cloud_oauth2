package com.cloud.oauth2.role.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.envers.Audited;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.Instant;

/***
 * 角色资源 DO
 */
@Entity
@Audited
@EntityListeners(AuditingEntityListener.class)
@EqualsAndHashCode(callSuper = false)
@Data
public class SysRoleModuleDO implements Serializable {

  /**
   *
   */
  private static final long serialVersionUID = 5362940001123254996L;
  @Id
  @GeneratedValue
  private Long id;
  private Long roleId;
  private Long moduleId;
  @CreatedDate
  private Instant createTime = Instant.now();
  @CreatedBy
  private Long createId;


}
