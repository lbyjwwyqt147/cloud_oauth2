package com.example.oauth.server.common.restful;

import com.alibaba.fastjson.annotation.JSONField;
import com.example.oauth.server.common.exception.ErrorCodeEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class RestfulVo  implements Serializable {

    private static final long serialVersionUID = -4450312255234324795L;

    private String status;  //状态码
    private String message; // 描述信息
    private Object data;   // 数据
    private Object extend; //扩展数据
    private Long totalElements; //分页数据的　总记录条数
    @JSONField(serialize=false)
    @JsonIgnore
    private ErrorCodeEnum  errorCodeEnum;

    public RestfulVo(){

    }

    public RestfulVo(ErrorCodeEnum  errorCodeEnum){
        this.status = errorCodeEnum.getCode();
        this.message = errorCodeEnum.getMessage();
    }

    public RestfulVo(ErrorCodeEnum  errorCodeEnum,Object data,Object extend){
        this.status = errorCodeEnum.getCode();
        this.message = errorCodeEnum.getMessage();
        this.data = data;
        this.extend = extend;
    }

    public RestfulVo(ErrorCodeEnum  errorCodeEnum,Object data){
        this.status = errorCodeEnum.getCode();
        this.message = errorCodeEnum.getMessage();
        this.data = data;
    }
}
