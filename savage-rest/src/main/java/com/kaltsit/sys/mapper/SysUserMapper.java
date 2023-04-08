package com.kaltsit.sys.mapper;

import com.kaltsit.base.SavegeBaseMapper;
import com.kaltsit.sys.entity.SysUserEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 用户Mapper层
 */
@Mapper
public interface SysUserMapper extends SavegeBaseMapper<SysUserEntity> {

    List<SysUserEntity> getList();

}
