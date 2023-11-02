package com.kaltsit.ascept.annotation;

import com.kaltsit.constant.CommonConstant;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLog {

    String value() default "";

    /**
     * 日志类型
     * <p>
     * 参考 {@link CommonConstant}
     */
    String type() default CommonConstant.SYSLOG_SELECT;

    /**
     * 需要排除的字段名称、该字段不会持久化到数据库参数中
     *
     * @return
     */
    String[] excludeFields() default {};

}
