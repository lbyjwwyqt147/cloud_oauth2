package com.example.oauth.server.domain.base;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/***
 *  数据 分页 信息
 * @param <T>
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PageVo<T> {

    @Data
    @EqualsAndHashCode(callSuper = false)
   public class Meta {
        private Integer page;     //当前页码
        private Integer pages;    //每页纪录数量
        private Integer perpage;  //上一页
        private Long  total;      //总纪录条数
        private String sort;      //排序方式
        private String field;     //排序字段
    }

    List<T> data;
    Meta meta;
}
