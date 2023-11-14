package com.kaltsit.mapper.sys;

import com.kaltsit.config.mybatis.base.SavegeBaseMapper;
import com.kaltsit.entity.sys.SysUserEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 用户Mapper层
 */
@Mapper
public interface SysUserMapper extends SavegeBaseMapper<SysUserEntity> {

    List<SysUserEntity> getList();

}
