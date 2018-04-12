package com.example.oauth.server.domain.base;

import lombok.Data;

import java.io.Serializable;

@Data
public class AbstractQuery implements Serializable {

    private static final long serialVersionUID = -33481165179982953L;

    private Long id;   // id
    private Integer page;   //当前页面
    private Integer pageSize; //每页显示记录条数
    private String sortDirection;  //排序方式 DESC   ASC
    private String sortField;   //排序字段



}
