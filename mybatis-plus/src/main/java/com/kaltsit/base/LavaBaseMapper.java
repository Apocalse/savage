package com.kaltsit.base;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kaltsit.entity.UserEntity;

public interface LavaBaseMapper <T> extends BaseMapper<T> {
    int deleteWithDelToken(UserEntity user);
}
