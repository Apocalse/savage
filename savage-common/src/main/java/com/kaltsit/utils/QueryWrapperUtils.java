package com.kaltsit.utils;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
public class QueryWrapperUtils {

    private final static String SEARCH_KEY = "searchKey";
    // 模糊查询字段，逗号分隔
    private final static String SEARCH_COLUMNS = "searchColumns";
    private final static String SPLIT = "_";
    private final static String SPLIT_SEARCH = " ";
    private final static String SPLIT_COLUMN = ",";
    private final static String BEGIN = "_begin";
    private final static String END = "_end";
    private final static String LIKE = "_like";
    private final static String LIKE_LEFT = "_like_left";
    private final static String LIKE_RIGHT = "_like_right";
    private final static String BELONG_DEPART_IDS = "belongDepartIds";
    private final static String ORDER_COLUMN = "orderColumn";
    private final static String ORDER_RULE = "orderRule";
    private final static String ASC = "ascending";
    private final static String DESC = "descending";


    /**
     * @param searchObj 实体类
     * @param params    查询条件
     * @return queryWrapper
     */
    public static <T> QueryWrapper<T> createQueryWrapper(T searchObj, MapUtils params) {
        long start = System.currentTimeMillis();
        QueryWrapper<T> queryWrapper = installMybatisPlus(searchObj, params);
        log.debug("---查询条件构造器初始化完成,耗时:" + (System.currentTimeMillis() - start) + "毫秒----");
        return queryWrapper;
    }

    /**
     * 组装Mybatis Plus 查询条件
     */
    public static <T> QueryWrapper<T> installMybatisPlus(Object searchObj, MapUtils params) {
        QueryWrapper<T> queryWrapper = new QueryWrapper<>();
        // 获取字段
        PropertyDescriptor[] origDescriptors = PropertyUtils.getPropertyDescriptors(searchObj);
        String name, column; // 属性名、数据库字段名
        Map<String, String> fieldColumnMap = new HashMap<>();
        for (int i = 0; i < origDescriptors.length; i++) {
            name = origDescriptors[i].getName();
//            type = origDescriptors[i].getPropertyType().toString();
            try {
                if (judgedIsUselessField(name) || !PropertyUtils.isReadable(searchObj, name)) {
                    continue;
                }
//                Object value = PropertyUtils.getSimpleProperty(searchObj, name);
                column = getTableFieldName(searchObj.getClass(), name);
                if (column == null) {
                    //column为null只有一种情况 那就是 添加了注解@TableField(exist = false) 后续都不用处理了
                    continue;
                }
                fieldColumnMap.put(name, humpToUnderline(column));
            } catch (Exception e) {
                log.error(ExceptionUtils.getStackTrace(e));
            }
        }

        // 条件区间组装 简单排序 权限查询
        for (String key : params.keySet()) {
            if(StringUtils.isEmpty(params.getString(key)) || judgedIsUnExistField(params.getString(key))){
                continue;
            }
            if (fieldColumnMap.containsKey(key.split(SPLIT)[0])) { // 数据库字段
                // 精确查询
                if (fieldColumnMap.containsKey(key)) {
                    queryWrapper.eq(fieldColumnMap.get(key), params.getString(key));
                }
                // 模糊查询
                if (fieldColumnMap.containsKey(key.split(LIKE)[0])) {
                    if (key.contains(LIKE_LEFT)) {
                        queryWrapper.likeLeft(fieldColumnMap.get(key.split(LIKE_LEFT)[0]), params.getString(key));
                    } else if (key.contains(LIKE_RIGHT)) {
                        queryWrapper.likeRight(fieldColumnMap.get(key.split(LIKE_RIGHT)[0]), params.getString(key));
                    } else {
                        queryWrapper.like(fieldColumnMap.get(key.split(LIKE)[0]), params.getString(key));
                    }
                }
                // 区间 BEGIN
                if (fieldColumnMap.containsKey(key.split(BEGIN)[0])) {
                    queryWrapper.ge(fieldColumnMap.get(key.split(BEGIN)[0]), params.getString(key));
                    continue;
                }
                // 区间 END
                if (fieldColumnMap.containsKey(key.split(END)[0])) {
                    queryWrapper.le(fieldColumnMap.get(key.split(END)[0]), params.getString(key));
                }
            } else { // 自定义规则
                if (key.equals(SEARCH_KEY)) { // 多字段模糊查询筛选条件
                    String searchColumns = params.getString(SEARCH_COLUMNS);
                    if (StringUtils.isNotEmpty(searchColumns)) {
                        String[] split = searchColumns.split(SPLIT_COLUMN);
                        List<String> columns = new ArrayList<>();
                        for (String temp : split){
                            if (fieldColumnMap.containsKey(temp)) {
                                columns.add(temp);
                            }
                        }
                        wrapperLike(queryWrapper, params.getString(SEARCH_KEY), String.join(",", columns));
                    }
                    continue;
                }
                if (key.equals(BELONG_DEPART_IDS)) { // 所属部门处理
                    continue;
                }
            }
        }
        // 自定义排序
        if(params.containsKey(ORDER_COLUMN) && params.containsKey(ORDER_RULE)){ // 自定义排序
            String orderColumn = params.getString(ORDER_COLUMN);
            String orderRule = params.getString(ORDER_RULE);
            if(fieldColumnMap.containsKey(orderColumn)) {
                order(queryWrapper, orderColumn, orderRule);
            }
        }else{ // 默认排序
            if(fieldColumnMap.containsKey("createDate")) {
                order(queryWrapper, "createDate", DESC);
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
     * @param queryWrapper  必须传入
     * @param searchKey     多条件之间使用空格隔开
     * @param searchColumns 实体对应的属性
     */
    public static <T> void wrapperLike(QueryWrapper<T> queryWrapper, String searchKey, String searchColumns) {
        if (StringUtils.isEmpty(searchKey) || StringUtils.isEmpty(searchColumns)) {
            return;
        }
        String[] columnNames = searchColumns.split(",");
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
                        wrapper.or().like(StringUtils.isNotBlank(key), humpToUnderline(column), key);
                    }
                });
            }
        }
    }

    /**
     * 根据传入字段排序
     *
     * @param queryWrapper 查询
     * @param column 字段
     * @param order 排序规则
     * @param <T>
     */
    public static <T> void order(QueryWrapper<T> queryWrapper, String column, String order) {
        // 字段排序
        if (ASC.equals(order)) {
            queryWrapper.orderByAsc(humpToUnderline(column));
        } else if (DESC.equals(order)) {
            queryWrapper.orderByDesc(humpToUnderline(column));
        }
    }

    /**
     * 默认按照时间倒排
     */
    public static <T> void order(QueryWrapper<T> queryWrapper) {
        // 字段排序
        order(queryWrapper, "createDate", DESC);
    }

    private static boolean judgedIsUselessField(String name) {
        return "class".equals(name) || "ids".equals(name)
                || "page".equals(name) || "size".equals(name)
                || "sort".equals(name) || "order".equals(name)
                || "rows".equals(name);
    }

    private static boolean judgedIsUnExistField(String name) {
        return "page".equals(name) || "size".equals(name)
                || ORDER_COLUMN.equals(name) || ORDER_RULE.equals(name);
    }

    private static String getTableFieldName(Class<?> clazz, String name) {
        try {
            //如果字段加注解了@TableField(exist = false),不走DB查询
            Field field = null;
            try {
                field = clazz.getDeclaredField(name);
            } catch (NoSuchFieldException e) {
                //e.printStackTrace();
            }
            //如果为空，则去父类查找字段
            if (field == null) {
                List<Field> allFields = getClassFields(clazz);
                List<Field> searchFields = allFields.stream().filter(a -> a.getName().equals(name)).collect(Collectors.toList());
                if (!searchFields.isEmpty()) {
                    field = searchFields.get(0);
                }
            }
            if (field != null) {
                TableField tableField = field.getAnnotation(TableField.class);
                if (tableField != null) {
                    if (!tableField.exist()) {
                        //如果设置了TableField false 这个字段不需要处理
                        return null;
                    } else {
                        String column = tableField.value();
                        //如果设置了TableField value 这个字段是实体字段
                        if (!"".equals(column)) {
                            return column;
                        }
                    }
                }
            }
        } catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(e));
        }
        return name;
    }

    /**
     * 获取class的 包括父类的
     *
     * @param clazz
     * @return
     */
    private static List<Field> getClassFields(Class<?> clazz) {
        List<Field> list = new ArrayList<>();
        Field[] fields;
        do {
            fields = clazz.getDeclaredFields();
            list.addAll(Arrays.asList(fields));
            clazz = clazz.getSuperclass();
        } while (clazz != Object.class && clazz != null);
        return list;
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
