package com.kaltsit.annotation;

import com.kaltsit.commons.SysLogType;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLog {

    String value() default "";

    /**
     * 日志类型
     * <p>
     * 参考 {@link SysLogType}
     */
    String type() default SysLogType.SELECT;

    /**
     * 需要排除的字段名称、该字段不会持久化到数据库参数中
     *
     * @return
     */
    String[] excludeFields() default {};

}
