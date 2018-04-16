package com.example.oauth.server.domain.base;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class AbstractQuery implements Serializable {

    private static final long serialVersionUID = -33481165179982953L;

    private Long id;   // id
    private Pagination p; // 分页信息
    private Sort s;           //排序信息

    @Data
    @EqualsAndHashCode(callSuper = false)
    public static class Pagination{
       private Integer pageNum;  //当前页面
       private Integer pageSize; //每页显示记录条数
    }

    @Data
    @EqualsAndHashCode(callSuper = false)
    public static class Sort{
        private String field;  //排序字段名称
        private String sort; //排序字段方式
    }


}
