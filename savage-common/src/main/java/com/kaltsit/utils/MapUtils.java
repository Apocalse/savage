package com.kaltsit.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.LinkedHashMap;
import java.util.Map;

public class MapUtils extends LinkedHashMap<String, Object> {

    public MapUtils() {
    }

    public MapUtils(Map<String, Object> map) {
        this.putAll(map);
    }

    @Override
    public MapUtils put(String key, Object value) {
        super.put(key, value);
        return this;
    }

    private boolean isNull(String key) {
        return get(key) == null;
    }

    /**
     * 获取字符串
     *
     * @param key
     * @return
     */
    public String getString(String key) {
        return get(key) == null ? null : get(key).toString();
    }

    public String getString(String key, String defaultValue) {
        String val = getString(key);
        return StringUtils.isBlank(val) ? defaultValue : val;
    }

    public Integer getInteger(String key) {
        String value = getString(key);
        if (StringUtils.isNotBlank(value)) {
            return Integer.parseInt(value);
        }
        return null;
    }

    public Integer getInteger(String key, Integer defaultValue) {
        Integer val = getInteger(key);
        return val == null ? defaultValue : val;
    }

    /**
     * 获取map实例
     * 创建一个新的map
     *
     * @return
     */
    public static MapUtils getInstance() {
        return new MapUtils();
    }

    public static MapUtils getInstance(Map<String, Object> map) {
        return new MapUtils(map);
    }


}
