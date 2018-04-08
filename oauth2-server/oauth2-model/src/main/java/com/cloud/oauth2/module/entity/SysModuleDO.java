package com.cloud.oauth2.module.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.envers.Audited;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

/***
 * 资源菜单 DO
 */
@Entity
@Audited
@EntityListeners(AuditingEntityListener.class)
@EqualsAndHashCode(callSuper = false)
@Data
public class SysModuleDO implements Serializable {

  /**
   *
   */
  private static final long serialVersionUID = 1335036782772445711L;
  @Id
  @GeneratedValue
  private Long id;
  private Long moduleCode;
  private String moduleName;
  private Byte moduleType;
  private Long modulePid;
  private Integer sequenceNumber;
  private String menuIcon;
  private String menuUrl;
  private String authorizedSigns;
  private Byte status;
  @CreatedDate
  private Instant createTime = Instant.now();
  @CreatedBy
  private Long createId;
  @LastModifiedDate
  private Instant updateTime = Instant.now();
  @LastModifiedBy
  private long updateId;

}
