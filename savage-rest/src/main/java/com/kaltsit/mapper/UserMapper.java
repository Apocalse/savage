package com.kaltsit.mapper;

import com.kaltsit.base.LavaBaseMapper;
import com.kaltsit.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 用户Mapper层
 */
@Mapper
public interface UserMapper extends LavaBaseMapper<UserEntity> {

    List<UserEntity> getList();

}
