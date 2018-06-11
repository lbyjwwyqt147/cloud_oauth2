package com.example.oauth.server.domain.module.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
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
//@Audited   //这个注解会在数据库中创建一个历史记录表 保存历史数据
@EntityListeners(AuditingEntityListener.class)   //需要在启动类加上@EnableJpaAuditing注解 才会生效
@EqualsAndHashCode(callSuper = false)
@Data
public class SysModule implements Serializable {

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
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @CreatedDate
    private Instant createTime;
    @CreatedBy
    private Long createId;
    @LastModifiedDate
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Instant updateTime;
    @LastModifiedBy
    private long updateId;
}
