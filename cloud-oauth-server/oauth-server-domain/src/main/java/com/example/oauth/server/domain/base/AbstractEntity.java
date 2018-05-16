package com.example.oauth.server.domain.base;

import lombok.Data;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.Instant;
@MappedSuperclass
//@Audited
@EntityListeners(AuditingEntityListener.class)
@Data
public abstract class AbstractEntity implements Serializable {

    private static final long serialVersionUID = 4905424275393486699L;

    private Instant createTime;
    private Long createId;
    @LastModifiedDate
    private Instant updateTime;
    private Long updateId;
}
