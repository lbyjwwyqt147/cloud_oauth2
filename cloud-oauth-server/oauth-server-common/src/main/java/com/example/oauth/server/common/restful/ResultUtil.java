package com.example.oauth.server.common.restful;

import com.example.oauth.server.common.exception.ErrorCodeEnum;

/***
 *  对返回给前端的数据进行格式封装处理
 */
public class ResultUtil {

    /**
     * 返回信息 传入返回具体出参
     * @param  success   boolean
     * @param data
     * @return
     */
    public static RestfulVo restful(boolean success,Object data){
       if (success){
           return success(data);
       }
       return fail(data);
    }

    /**
     * 返回信息 不返回具体出参
     * @return
     */
    public static RestfulVo restful(boolean success){
        if (success){
            return success();
        }
        return fail();
    }

    /**
     * 返回成功，传入返回体具体出參
     * @param data
     * @return
     */
    public static RestfulVo success(Object data){
        RestfulVo result = new RestfulVo();
        result.setStatus(ErrorCodeEnum.SUCCESS.getCode());
        result.setMessage(ErrorCodeEnum.SUCCESS.getMessage());
        result.setData(data);
        return result;
    }

    /**
     * 返回成功，传入返回体具体出參(包含扩展数据)
     * @param data
     * @param extend 扩展数据
     * @return
     */
    public static RestfulVo success(Object data,Object extend){
        RestfulVo result = new RestfulVo();
        result.setStatus(ErrorCodeEnum.SUCCESS.getCode());
        result.setMessage(ErrorCodeEnum.SUCCESS.getMessage());
        result.setData(data);
        result.setExtend(extend);
        return result;
    }

    /**
     * 返回成功，不需要返回具体参数
     * @return
     */
    public static RestfulVo success(){
        RestfulVo result = new RestfulVo();
        result.setStatus(ErrorCodeEnum.SUCCESS.getCode());
        result.setMessage(ErrorCodeEnum.SUCCESS.getMessage());
        return result;
    }

    /**
     * 返回失败，传入返回体具体出參
     * @param data
     * @return
     */
    public static RestfulVo fail(Object data){
        RestfulVo result = new RestfulVo();
        result.setStatus(ErrorCodeEnum.FAIL.getCode());
        result.setMessage(ErrorCodeEnum.FAIL.getMessage());
        result.setData(data);
        return result;
    }

    /**
     * 返回失败，传入返回体具体出參(包含扩展数据)
     * @param data
     * @param extend
     * @return
     */
    public static RestfulVo fail(Object data,Object extend){
        RestfulVo result = new RestfulVo();
        result.setStatus(ErrorCodeEnum.FAIL.getCode());
        result.setMessage(ErrorCodeEnum.FAIL.getMessage());
        result.setData(data);
        result.setExtend(extend);
        return result;
    }

    /**
     * 返回失败，不需要返回具体参数
     * @return
     */
    public static RestfulVo fail(){
        RestfulVo result = new RestfulVo();
        result.setStatus(ErrorCodeEnum.FAIL.getCode());
        result.setMessage(ErrorCodeEnum.FAIL.getMessage());
        return result;
    }

    /**
     * 自定义返回信息
     * @param status  状态码
     * @param  message 消息
     * @param data 数据
     * @return
     */
    public static RestfulVo resultView(String status,String message,Object data){
        RestfulVo result = new RestfulVo();
        result.setStatus(status);
        result.setMessage(message);
        result.setData(data);
        return result;
    }

    /**
     * 自定义错误信息
     * @param code
     * @param message
     * @return
     */
    public static RestfulVo error(String code,String message){
        RestfulVo result = new RestfulVo();
        result.setStatus(code);
        result.setMessage(message);
        return result;
    }

    /**
     * 返回异常信息，在已知的范围内
     * @return
     */
    public static RestfulVo error(){
        RestfulVo result = new RestfulVo();
        result.setStatus(ErrorCodeEnum.ERROR.getCode());
        result.setMessage(ErrorCodeEnum.ERROR.getMessage());
        return result;
    }

}
