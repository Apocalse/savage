package com.kaltsit.base;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kaltsit.entity.UserEntity;

/**
 * 删除时添加删除token(id)，可用于数据库unique去重
 * @param <T>
 */
public interface LavaBaseMapper <T> extends BaseMapper<T> {
    int deleteWithDelToken(UserEntity user);
}
