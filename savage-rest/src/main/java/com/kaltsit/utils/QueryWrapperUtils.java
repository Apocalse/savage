package com.kaltsit.utils;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import org.apache.commons.lang3.StringUtils;

public class QueryWrapperUtils {

    /**
     * 模糊查询
     *
     * @param queryWrapper
     * @param searchKey    查询的关键字，多关键字之间使用空格隔开
     * @param fieldNames
     * @return
     */
    public static <T> LambdaQueryWrapper<T> like(LambdaQueryWrapper<T> queryWrapper, String searchKey, SFunction<T, ?>... fieldNames) {
        if (fieldNames == null || fieldNames.length == 0 || StringUtils.isBlank(searchKey)) {
            return queryWrapper;
        }
        /**
         * 多个条件之间使用空格隔开
         */
        String[] searchKeyArr = searchKey.split(" ");
        for (String key : searchKeyArr) {
            if (StringUtils.isBlank(key)) {
                continue;
            }
            queryWrapper.and(wrapper -> {
                for (SFunction<T, ?> fieldName : fieldNames) {
                    if (StringUtils.isBlank(key)) {
                        continue;
                    }
                    wrapper.or().like(fieldName, key);
                }
            });
        }
        return queryWrapper;
    }

    /**
     * 模糊查询封装
     *
     * @param ew         必须传入
     * @param searchKey  多条件之间使用空格隔开
     * @param columNames 实体对应的属性
     * @return
     */
    public static <T> QueryWrapper<T> wrapperLike(QueryWrapper<T> ew, String searchKey, String... columNames) {
        if (columNames == null || columNames.length == 0) {
            return ew;
        }
        /**
         * 按照属性模糊查询
         */
        if (StringUtils.isNotBlank(searchKey)) {
            /**
             * 多个条件之间使用空格隔开
             */
            String[] searchKeyArr = searchKey.split(" ");
            for (String key : searchKeyArr) {
                if (StringUtils.isBlank(key)) {
                    continue;
                }
                ew.and(wrapper -> {
                    for (String column : columNames) {
                        wrapper.or().like(StringUtils.isNotBlank(key), column, key);
                    }
                });
            }
        }
        return ew;
    }
}
