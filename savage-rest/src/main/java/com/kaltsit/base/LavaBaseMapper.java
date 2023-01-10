package com.kaltsit.base;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * 删除时添加删除token(id)，可用于数据库unique去重
 * @param <T>
 */
public interface LavaBaseMapper <T> extends BaseMapper<T> {

    void deleteWithDelToken(T t);

    default void deleteListWithFillDelToken(List<T> list) {
        for (T t : list) {
            deleteWithDelToken(t);
        }
    }

}
