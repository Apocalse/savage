package com.kaltsit.ascept.annotation;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.kaltsit.ascept.DictSerializer;
import com.kaltsit.ascept.NullDictSerializer;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@JacksonAnnotationsInside
@JsonSerialize(using = DictSerializer.class, nullsUsing = NullDictSerializer.class)
public @interface Dict2 {

    String code();

    /**
     * 1-单个翻译  2-多个翻译
     */
    String type() default "1";

}

