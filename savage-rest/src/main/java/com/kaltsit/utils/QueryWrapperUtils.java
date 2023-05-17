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
    @SafeVarargs
    public static <T> LambdaQueryWrapper<T> like(LambdaQueryWrapper<T> queryWrapper, String searchKey, SFunction<T, ?>... fieldNames) {
        if (fieldNames == null || fieldNames.length == 0 || StringUtils.isBlank(searchKey)) {
            return queryWrapper;
        }
        //多个条件之间使用空格隔开
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
     * @param queryWrapper         必须传入
     * @param searchKey  多条件之间使用空格隔开
     * @param columnNames 实体对应的属性
     * @return
     */
    public static <T> QueryWrapper<T> wrapperLike(QueryWrapper<T> queryWrapper, String searchKey, String... columnNames) {
        if (columnNames == null || columnNames.length == 0) {
            return queryWrapper;
        }
        // 按照属性模糊查询
        if (StringUtils.isNotBlank(searchKey)) {
            // 多个条件之间使用空格隔开
            String[] searchKeyArr = searchKey.split(" ");
            for (String key : searchKeyArr) {
                if (StringUtils.isBlank(key)) {
                    continue;
                }
                queryWrapper.and(wrapper -> {
                    for (String column : columnNames) {
                        wrapper.or().like(StringUtils.isNotBlank(key), column, key);
                    }
                });
            }
        }
        return queryWrapper;
    }

    /**
     * 根据传入字段排序
     * @param queryWrapper
     * @param column
     * @param order
     * @param <T>
     */
    public static <T> void order(QueryWrapper<T> queryWrapper, String column, String order) {
        // 字段排序
        if("ascending".equals(order)){
            queryWrapper.orderByAsc(humpToUnderline(column));
        }else if("descending".equals(order)){
            queryWrapper.orderByDesc(humpToUnderline(column));
        }else{
            queryWrapper.orderByDesc("create_date");
        }
    }

    /**
     * 默认按照时间倒排
     * @param ew
     * @param <T>
     */
    public static <T> void order(QueryWrapper<T> ew) {
        // 字段排序
        ew.orderByDesc("create_date");
    }

    /**
     * 字符串驼峰转下划线
     * @param name
     * @return
     */
    public static String humpToUnderline(String name) {
        StringBuilder result = new StringBuilder();
        if (name != null && name.length() > 0) {
            // 将第一个字符处理成大写
            result.append(name.substring(0, 1).toUpperCase());
            // 循环处理其余字符
            for (int i = 1; i < name.length(); i++) {
                String s = name.substring(i, i + 1);
                // 在大写字母前添加下划线
                if (s.equals(s.toUpperCase()) && !Character.isDigit(s.charAt(0))) {
                    result.append("_");
                }
                // 其他字符直接转成大写
                result.append(s.toUpperCase());
            }
        }
        return result.toString();
    }


}
