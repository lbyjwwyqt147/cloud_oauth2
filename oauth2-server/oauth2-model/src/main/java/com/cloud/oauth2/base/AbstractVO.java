package com.cloud.oauth2.base;

import lombok.Data;
import org.hibernate.envers.Audited;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.Instant;

@MappedSuperclass
@Audited
@EntityListeners(AuditingEntityListener.class)
@Data
public class AbstractVO implements Serializable {
    private static final long serialVersionUID = -38807433084019658L;
    private Long id; //id
    private Instant createTime;//创建时间
    private Long createId; //创建人ID
    private String createUserName;  //创建人名称
    private Instant updateTime;  //最后更新时间
    private Long updateId;       //最后更新人id
    private String updateUserName; //最后更新人名称
    private Byte status;  //状态
    private String statusText; //状态转换文本值


}
