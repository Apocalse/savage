package com.apocalse.mybatisplus.mapper.db1;

import com.apocalse.mybatisplus.entity.db1.UserEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<UserEntity> {
    List<UserEntity> getList();
}
