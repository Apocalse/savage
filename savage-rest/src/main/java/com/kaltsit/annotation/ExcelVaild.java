package com.kaltsit.annotation;

import com.kaltsit.commons.ExcelValidEnum;

import java.lang.annotation.*;

@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelVaild {

    ExcelValidEnum value() default ExcelValidEnum.NULL;

    String dictType() default ""; //配置字典数据
}

