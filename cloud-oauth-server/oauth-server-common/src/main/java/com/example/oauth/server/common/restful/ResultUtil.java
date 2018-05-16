package com.example.oauth.server.common.restful;

import com.alibaba.fastjson.JSON;
import com.example.oauth.server.common.exception.ErrorCodeEnum;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

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
    public static RestfulVo restfulInfo(boolean success,Object data){
       if (success){
           return success(data);
       }
       return fail(data);
    }

    /**
     * 返回信息 不返回具体出参
     * @return
     */
    public static RestfulVo restfulInfo(boolean success){
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
        RestfulVo result = new RestfulVo(ErrorCodeEnum.SUCCESS,data);
        return result;
    }

    /**
     * 返回成功，传入返回体具体出參(包含扩展数据)
     * @param data
     * @param extend 扩展数据
     * @return
     */
    public static RestfulVo success(Object data,Object extend){
        RestfulVo result = new RestfulVo(ErrorCodeEnum.SUCCESS,data,extend);
        return result;
    }

    /**
     * 返回成功，不需要返回具体参数
     * @return
     */
    public static RestfulVo success(){
        RestfulVo result = new RestfulVo(ErrorCodeEnum.SUCCESS);
        return result;
    }

    /**
     * 返回失败，传入返回体具体出參
     * @param data
     * @return
     */
    public static RestfulVo fail(Object data){
        RestfulVo result = new RestfulVo(ErrorCodeEnum.FAIL,data);
        return result;
    }

    /**
     * 返回失败，传入返回体具体出參(包含扩展数据)
     * @param data
     * @param extend
     * @return
     */
    public static RestfulVo fail(Object data,Object extend){
        RestfulVo result = new RestfulVo(ErrorCodeEnum.FAIL,data,extend);
        return result;
    }

    /**
     * 返回失败，不需要返回具体参数
     * @return
     */
    public static RestfulVo fail(){
        RestfulVo result = new RestfulVo(ErrorCodeEnum.FAIL);
        return result;
    }

    /**
     * 自定义返回信息
     * @param status  状态码
     * @param  message 消息
     * @param data 数据
     * @return
     */
    public static RestfulVo resultInfo(String status,String message,Object data){
        RestfulVo result = new RestfulVo();
        result.setStatus(status);
        result.setMessage(message);
        result.setData(data);
        return result;
    }

    /**
     * 自定义返回信息
     * @param errorCodeEnum
     * @param data 数据
     * @return
     */
    public static RestfulVo resultInfo(ErrorCodeEnum errorCodeEnum,Object data){
        RestfulVo result = new RestfulVo(errorCodeEnum,data);
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
        RestfulVo result = new RestfulVo(ErrorCodeEnum.ERROR);
        return result;
    }

    /**
     * 将json输出到前端(参数非json格式)
     * @param response
     * @param obj  任意类型
     */
    public static void writeJavaScript(HttpServletResponse response, Object obj){
        response.setContentType("application/json;charset=UTF-8");
        response.setHeader("Cache-Control","no-store, max-age=0, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        /* 设置浏览器跨域访问 */
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST,GET,OPTIONS,DELETE,PUT");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with,Authorization");
        response.setHeader("Access-Control-Allow-Credentials","true");
        try {
            PrintWriter out = response.getWriter();
            out.write(JSON.toJSONString(obj));
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将数据输出给前端
     * @param response
     * @param errorCodeEnum
     * @param obj
     */
    public static void writeJavaScript(HttpServletResponse response,ErrorCodeEnum errorCodeEnum, Object obj){
        //自定义的信息方便自己查看
        Map<String,Object> map = new HashMap<>();
        map.put("message",obj);
        RestfulVo restfulVo = ResultUtil.resultInfo(ErrorCodeEnum.LOGIN,map);
        writeJavaScript(response,restfulVo);
    }

}
