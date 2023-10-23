package com.kaltsit.config;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.kaltsit.exception.SavageException;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.savage.utils.JsonResult;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 自定义异常处理
 */
@ControllerAdvice
public class GlobalExceptionHandle {
    @ResponseBody
    @ExceptionHandler(value = RuntimeException.class)
    public JsonResult<Void> exceptionHandler(RuntimeException e){
        e.printStackTrace();
        return JsonResult.error(e.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(value = SavageException.class)
    public JsonResult<Void> savageExceptionHandler(SavageException e){
        e.printStackTrace();
        return JsonResult.error(e.getCode(), e.getMessage());
    }

    // 捕捉shiro的异常
    @ResponseBody
    @ExceptionHandler(ShiroException.class)
    public JsonResult<Void> handle401(ShiroException e) {
        e.printStackTrace();
        return JsonResult.error(401, e.getMessage());
    }

    // 捕捉shiro的异常
    @ResponseBody
    @ExceptionHandler(UnauthenticatedException.class)
    public JsonResult<Void> handle401(UnauthenticatedException e) {
        e.printStackTrace();
        return JsonResult.error(401, e.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(value = TokenExpiredException.class)
    public JsonResult<Void> handler(TokenExpiredException e) {
        e.printStackTrace();
        return JsonResult.error(HttpStatus.BAD_REQUEST.value(), "token已经过期，请重新登录");
    }

}
