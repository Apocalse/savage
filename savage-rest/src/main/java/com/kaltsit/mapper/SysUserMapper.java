package com.kaltsit.mapper;

import com.kaltsit.base.LavaBaseMapper;
import com.kaltsit.entity.SysUserEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 用户Mapper层
 */
@Mapper
public interface SysUserMapper extends LavaBaseMapper<SysUserEntity> {

    List<SysUserEntity> getList();

}
