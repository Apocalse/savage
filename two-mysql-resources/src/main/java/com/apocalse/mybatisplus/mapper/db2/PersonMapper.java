package com.apocalse.mybatisplus.mapper.db2;

import com.apocalse.mybatisplus.entity.db2.PersonEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PersonMapper extends BaseMapper<PersonEntity> {
    List<PersonEntity> getList();
}
