package com.kaltsit.controller;

import com.kaltsit.exception.SavageException;
import com.kaltsit.utils.JsonResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * 自定义异常处理
 */
@ControllerAdvice
public class MyControllerAdvice {

    @ResponseBody
    @ExceptionHandler(value = RuntimeException.class)
    public JsonResult<Void> exceptionHandler(RuntimeException runtimeException){
        return JsonResult.error(runtimeException.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(value = SavageException.class)
    public JsonResult<Void> savageExceptionHandler(SavageException savageException){
        return JsonResult.error(savageException.getCode(), savageException.getMsg());
    }


}