package com.example.oauth.server.domain.role.query;

import com.example.oauth.server.domain.base.AbstractQuery;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserRoleQuery extends AbstractQuery {

    private Long userId;
    private Long roleId;
}
