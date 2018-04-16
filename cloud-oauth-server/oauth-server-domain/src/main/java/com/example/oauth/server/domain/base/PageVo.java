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
        private Integer pages;    // 总页码
        private Integer perpage;  //每页纪录数量
        private Long  total;      //总纪录条数
        private String sort;      //排序方式
        private String field;     //排序字段
    }

    public PageVo(Integer pageNumber,Integer pageSize,Long totalElements) {
        this.meta.page = pageNumber;
        this.meta.perpage = pageSize;
        this.meta.total = totalElements;
    }

    List<T> data;
    Meta meta = new Meta();
}
