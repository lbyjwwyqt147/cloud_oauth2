package com.example.oauth.server.domain.role.query;

import com.example.oauth.server.domain.base.AbstractQuery;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RoleQuery extends AbstractQuery {

    private String roleCode;
    private String roleName;

}
