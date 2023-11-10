package com.kaltsit.utils;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

@Slf4j
public class QueryWrapperUtils {

    /**
     *
     * @param searchObj 实体类
     * @param params 查询条件
     * @param searchColumns 模糊检索字段
     * @return queryWrapper
     */
    public static <T> QueryWrapper<T> createQueryWrapper(T searchObj, MapUtils params, String... searchColumns) {
        long start = System.currentTimeMillis();
        QueryWrapper<T> queryWrapper = installMybatisPlus(params);
        // 多字段模糊查询筛选条件
        if (StringUtils.isNotEmpty(params.getString("searchKey"))) {
            queryWrapper = wrapperLike(queryWrapper, params.getString("searchKey"), searchColumns);
        }
        log.debug("---查询条件构造器初始化完成,耗时:" + (System.currentTimeMillis() - start) + "毫秒----");
        return queryWrapper;
    }

    /**
     * 组装Mybatis Plus 查询条件
     */
    public static <T> QueryWrapper<T> installMybatisPlus(MapUtils params) {
        QueryWrapper<T> queryWrapper = new QueryWrapper<>();
        // 条件区间组装 简单排序 权限查询
        for (String str : params.keySet()) {
            if (str.endsWith("_time_start")) { //区间
                queryWrapper.ge(humpToUnderline(str.split("_time_start")[0]), params.getString(str));
            } else if (str.endsWith("_time_end")) {
                queryWrapper.le(humpToUnderline(str.split("_time_end")[0]), params.getString(str));
            } else if (str.equals("_int_start")) {
                queryWrapper.ge(humpToUnderline(str.split("_int_start")[0]), params.getInteger(str));
            } else if (str.endsWith("_int_end")) {
                queryWrapper.le(humpToUnderline(str.split("_int_end")[0]), params.getInteger(str));
            } else if (str.equals("_double_start")) {
                queryWrapper.ge(humpToUnderline(str.split("_double_start")[0]), params.getDouble(str));
            } else if (str.endsWith("_double_end")) {
                queryWrapper.le(humpToUnderline(str.split("_double_end")[0]), params.getDouble(str));
            } else if (str.equals("user_depart_ids")) { // 部门处理

            } else if (str.equals("column_order")) { // 排序
                order(queryWrapper, params.getString("column_order"));
            } else {
                if (!str.equals("page") && !str.equals("size") && !str.equals("searchKey")) {
                    queryWrapper.eq(humpToUnderline(str), params.getString(str));
                }
            }
        }
        return queryWrapper;
    }

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
     * @param queryWrapper 必须传入
     * @param searchKey    多条件之间使用空格隔开
     * @param columnNames  实体对应的属性
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
     *
     * @param queryWrapper
     * @param columnOrder
     * @param <T>
     */
    public static <T> void order(QueryWrapper<T> queryWrapper, String columnOrder) {
        if (StringUtils.isNotEmpty(columnOrder)) {
            String order = columnOrder.split("-")[1];
            String column = humpToUnderline(columnOrder.split("-")[0]);
            // 字段排序
            if ("ascending".equals(order)) {
                queryWrapper.orderByAsc(column);
            } else if ("descending".equals(order)) {
                queryWrapper.orderByDesc(column);
            } else {
                queryWrapper.orderByDesc("create_date");
            }
        }
    }

    /**
     * 默认按照时间倒排
     *
     * @param queryWrapper
     * @param <T>
     */
    public static <T> void order(QueryWrapper<T> queryWrapper) {
        // 字段排序
        queryWrapper.orderByDesc("create_date");
    }

    /**
     * 字符串驼峰转下划线
     *
     * @param name
     * @return
     */
    public static String humpToUnderline(String name) {
        StringBuilder result = new StringBuilder();
        if (name != null && !name.isEmpty()) {
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
