package com.kaltsit.utils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.apache.http.HttpStatus;

import java.io.Serializable;

/**
 * 定义Json响应数据
 *
 * @param <T>
 */
@Data
public class JsonResult<T> implements Serializable {
    private Integer code;
    private String msg;
    private T data;

    /**
     * 成功
     * @return 结果
     */
    public static JsonResult ok() {
        JsonResult result = new JsonResult();
        result.setCode(200);
        return result;
    }

    /**
     * 失败
     * @param msg 失败信息
     * @return 结果
     */
    public static JsonResult error(String msg) {
        JsonResult result = new JsonResult();
        result.setCode(HttpStatus.SC_INTERNAL_SERVER_ERROR);
        msg = msg==null||"null".equals(msg) ? "未知异常，请联系管理员":msg;
        result.setMsg(msg);
        return result;
    }

    /**
     * 失败
     * @param code 状态码
     * @param msg  信息提示
     * @return 结果
     */
    public static JsonResult error(Integer code, String msg) {
        JsonResult result = new JsonResult();
        result.setCode(code);
        msg = msg==null||"null".equals(msg) ? "未知异常，请联系管理员":msg;
        result.setMsg(msg);
        return result;
    }

    /**
     * 失败
     * @return 结果
     */
    public static JsonResult error() {
        JsonResult result = new JsonResult();
        result.setCode(HttpStatus.SC_INTERNAL_SERVER_ERROR);
        result.setMsg("未知异常，请联系管理员");
        return result;
    }

    /**
     * 添加返回的数据
     * @param data 数据
     * @return 结果
     */
    public JsonResult<T> put(T data) {
        this.data = data;
        return this;
    }

    /**
     * 是否正常
     * @return 结果
     */
    @JsonIgnore
    public boolean isOk() {
        return this.code == 200;
    }

    @JsonIgnore
    public boolean isError() {
        return this.code != 200;
    }
}
