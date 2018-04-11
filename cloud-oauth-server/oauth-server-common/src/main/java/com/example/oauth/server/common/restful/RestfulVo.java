package com.example.oauth.server.common.restful;

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
}
