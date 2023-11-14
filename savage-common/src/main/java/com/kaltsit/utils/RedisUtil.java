package com.kaltsit.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class RedisUtil {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    private static RedisTemplate<String,Object> staticRedisTemplate;

    @PostConstruct
    public void init() {
        staticRedisTemplate = this.redisTemplate;
    }

    /**
     * 给一个指定的 key 值附加过期时间
     */
    public static boolean expire(String key, long time) {
        return Boolean.TRUE.equals(staticRedisTemplate.expire(key, time, TimeUnit.SECONDS));
    }

    /**
     * 根据key 获取过期时间
     */
    public static Long getTime(String key) {
        return staticRedisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    /**
     * 根据key 获取过期时间
     */
    public static boolean hasKey(String key) {
        return Boolean.TRUE.equals(staticRedisTemplate.hasKey(key));
    }

    /**
     * 移除指定key 的过期时间
     */
    public static boolean persist(String key) {
        return Boolean.TRUE.equals(staticRedisTemplate.boundValueOps(key).persist());
    }

    //- - - - - - - - - - - - - - - - - - - - -  String类型 - - - - - - - - - - - - - - - - - - - -

    /**
     * 根据key获取值
     *
     * @param key 键
     * @return 值
     */
    public static Object get(String key) {
        return key == null ? null : staticRedisTemplate.opsForValue().get(key);
    }

    /**
     * 将值放入缓存
     *
     * @param key   键
     * @param value 值
     */
    public static void set(String key, String value) {
        staticRedisTemplate.opsForValue().set(key, value);
    }

    /**
     * 将值放入缓存并设置时间
     *
     * @param key   键
     * @param value 值
     * @param time  时间(秒) -1为无期限
     */
    public static void set(String key, String value, long time) {
        if (time > 0) {
            staticRedisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
        } else {
            staticRedisTemplate.opsForValue().set(key, value);
        }
    }

    /**
     * 批量添加 key (重复的键会覆盖)
     */
    public static void batchSet(Map<String, String> keyAndValue) {
        staticRedisTemplate.opsForValue().multiSet(keyAndValue);
    }

    /**
     * 批量添加 key-value 只有在键不存在时,才添加
     * map 中只要有一个key存在,则全部不添加
     */
    public static void batchSetIfAbsent(Map<String, String> keyAndValue) {
        staticRedisTemplate.opsForValue().multiSetIfAbsent(keyAndValue);
    }

    /**
     * 对一个 key-value 的值进行加减操作,
     * 如果该 key 不存在 将创建一个key 并赋值该 number
     * 如果 key 存在,但 value 不是长整型 ,将报错
     */
    public static Long increment(String key, long number) {
        return staticRedisTemplate.opsForValue().increment(key, number);
    }

    /**
     * 对一个 key-value 的值进行加减操作,
     * 如果该 key 不存在 将创建一个key 并赋值该 number
     * 如果 key 存在,但 value 不是 纯数字 ,将报错
     */
    public static Double increment(String key, double number) {
        return staticRedisTemplate.opsForValue().increment(key, number);
    }

    //- - - - - - - - - - - - - - - - - - - - -  set类型 - - - - - - - - - - - - - - - - - - - -

    /**
     * 将数据放入set缓存
     */
    public static void sSet(String key, String value) {
        staticRedisTemplate.opsForSet().add(key, value);
    }

    /**
     * 获取变量中的值
     *
     * @param key 键
     */
    public static Set<Object> members(String key) {
        return staticRedisTemplate.opsForSet().members(key);
    }

    /**
     * 随机获取变量中指定个数的元素
     *
     * @param key   键
     * @param count 值
     */
    public static void randomMembers(String key, long count) {
        staticRedisTemplate.opsForSet().randomMembers(key, count);
    }

    /**
     * 随机获取变量中的元素
     *
     * @param key 键
     */
    public static Object randomMember(String key) {
        return staticRedisTemplate.opsForSet().randomMember(key);
    }

    /**
     * 弹出变量中的元素
     *
     * @param key 键
     */
    public static Object pop(String key) {
        return staticRedisTemplate.opsForSet().pop("setValue");
    }

    /**
     * 获取变量中值的长度
     *
     * @param key 键
     */
    public static long size(String key) {
        try {
            return staticRedisTemplate.opsForSet().size(key);
        }catch (Exception e){
            log.error(ExceptionUtils.getStackTrace(e));
            return 0;
        }
    }

    /**
     * 根据value从一个set中查询,是否存在
     *
     * @param key   键
     * @param value 值
     * @return true 存在 false不存在
     */
    public static boolean sHasKey(String key, Object value) {
        try {
            return staticRedisTemplate.opsForSet().isMember(key, value);
        }catch (Exception e){
            log.error(ExceptionUtils.getStackTrace(e));
            return false;
        }
    }

    /**
     * 检查给定的元素是否在变量中。
     *
     * @param key 键
     * @param obj 元素对象
     */
    public static boolean isMember(String key, Object obj) {
        try {
            return staticRedisTemplate.opsForSet().isMember(key, obj);
        }catch (Exception e){
            log.error(ExceptionUtils.getStackTrace(e));
            return false;
        }

    }

    /**
     * 转移变量的元素值到目的变量。
     *
     * @param key     键
     * @param value   元素对象
     * @param destKey 元素对象
     */
    public static boolean move(String key, String value, String destKey) {
        return Boolean.TRUE.equals(staticRedisTemplate.opsForSet().move(key, value, destKey));
    }

    /**
     * 批量移除set缓存中元素
     *
     * @param key    键
     * @param values 值
     */
    public static void remove(String key, Object... values) {
        staticRedisTemplate.opsForSet().remove(key, values);
    }

    /**
     * 通过给定的key求2个set变量的差值
     *
     * @param key     键
     * @param destKey 键
     */
    public static Set<Set> difference(String key, String destKey) {
        return Collections.singleton(staticRedisTemplate.opsForSet().difference(key, destKey));
    }

    //- - - - - - - - - - - - - - - - - - - - -  hash类型 - - - - - - - - - - - - - - - - - - - -

    /**
     * 加入缓存
     *
     * @param key 键
     * @param map 键
     */
    public static void add(String key, Map<String, Object> map) {
        staticRedisTemplate.opsForHash().putAll(key, map);
    }

    /**
     * 获取 key 下的 所有  hashkey 和 value
     *
     * @param key 键
     */
    public static Map<Object, Object> getHashEntries(String key) {
        return staticRedisTemplate.opsForHash().entries(key);
    }

    /**
     * 验证指定 key 下 有没有指定的 hashkey
     */
    public static boolean hashKey(String key, String hashKey) {
        return staticRedisTemplate.opsForHash().hasKey(key, hashKey);
    }

    /**
     * 获取指定key的值string
     *
     * @param key  键
     * @param key2 键
     */
    public static String getMapString(String key, String key2) {
        return Objects.requireNonNull(staticRedisTemplate.opsForHash().get("map1", "key1")).toString();
    }

    /**
     * 获取指定的值Int
     *
     * @param key  键
     * @param key2 键
     */
    public static Integer getMapInt(String key, String key2) {
        return (Integer) staticRedisTemplate.opsForHash().get("map1", "key1");
    }

    /**
     * 弹出元素并删除
     *
     * @param key 键
     */
    public static String popValue(String key) {
        return staticRedisTemplate.opsForSet().pop(key).toString();
    }

    /**
     * 删除指定 hash 的 HashKey
     * @return 删除成功的 数量
     */
    public static Long delete(String key, String... hashKeys) {
        return staticRedisTemplate.opsForHash().delete(key, hashKeys);
    }

    /**
     * 给指定 hash 的 hashkey 做增减操作
     */
    public static Long increment(String key, String hashKey, long number) {
        return staticRedisTemplate.opsForHash().increment(key, hashKey, number);
    }

    /**
     * 给指定 hash 的 hashkey 做增减操作
     */
    public static Double increment(String key, String hashKey, Double number) {
        return staticRedisTemplate.opsForHash().increment(key, hashKey, number);
    }

    /**
     * 获取 key 下的 所有 hashkey 字段
     */
    public static Set<Object> hashKeys(String key) {
        return staticRedisTemplate.opsForHash().keys(key);
    }

    /**
     * 获取指定 hash 下面的 键值对 数量
     */
    public static Long hashSize(String key) {
        return staticRedisTemplate.opsForHash().size(key);
    }

    //- - - - - - - - - - - - - - - - - - - - -  list类型 - - - - - - - - - - - - - - - - - - - -

    /**
     * 在变量左边添加元素值
     */
    public static void leftPush(String key, Object value) {
        staticRedisTemplate.opsForList().leftPush(key, value);
    }

    /**
     * 获取集合指定位置的值。
     */
    public static Object index(String key, long index) {
        return staticRedisTemplate.opsForList().index("list", 1);
    }

    /**
     * 获取指定区间的值。
     */
    public static List<Object> range(String key, long start, long end) {
        return staticRedisTemplate.opsForList().range(key, start, end);
    }

    /**
     * 把最后一个参数值放到指定集合的第一个出现中间参数的前面，
     * 如果中间参数值存在的话。
     */
    public static void leftPush(String key, String pivot, String value) {
        staticRedisTemplate.opsForList().leftPush(key, pivot, value);
    }

    /**
     * 向左边批量添加参数元素。
     *
     */
    public static void leftPushAll(String key, String... values) {
//        staticRedisTemplate.opsForList().leftPushAll(key,"w","x","y");
        staticRedisTemplate.opsForList().leftPushAll(key, values);
    }

    /**
     * 向集合最右边添加元素。
     */
    public static void leftPushAll(String key, String value) {
        staticRedisTemplate.opsForList().rightPush(key, value);
    }

    /**
     * 向左边批量添加参数元素。
     */
    public static void rightPushAll(String key, String... values) {
        //staticRedisTemplate.opsForList().leftPushAll(key,"w","x","y");
        staticRedisTemplate.opsForList().rightPushAll(key, values);
    }

    /**
     * 向已存在的集合中添加元素。
     */
    public static void rightPushIfPresent(String key, Object value) {
        staticRedisTemplate.opsForList().rightPushIfPresent(key, value);
    }

    /**
     * 向已存在的集合中添加元素。
     */
    public static long listLength(String key) {
        try {
            return staticRedisTemplate.opsForList().size(key);
        }catch (Exception e){
            log.error(ExceptionUtils.getStackTrace(e));
            return 0;
        }
    }

    /**
     * 移除集合中的左边第一个元素。
     */
    public static void leftPop(String key) {
        staticRedisTemplate.opsForList().leftPop(key);
    }

    /**
     * 移除集合中左边的元素在等待的时间里，如果超过等待的时间仍没有元素则退出。
     */
    public static void leftPop(String key, long timeout, TimeUnit unit) {
        staticRedisTemplate.opsForList().leftPop(key, timeout, unit);
    }

    /**
     * 移除集合中右边的元素。
     */
    public static void rightPop(String key) {
        staticRedisTemplate.opsForList().rightPop(key);
    }

    /**
     * 移除集合中右边的元素在等待的时间里，如果超过等待的时间仍没有元素则退出。
     */
    public static void rightPop(String key, long timeout, TimeUnit unit) {
        staticRedisTemplate.opsForList().rightPop(key, timeout, unit);
    }
}
