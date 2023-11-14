package com.kaltsit.service.sys.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kaltsit.service.sys.ISysLogService;
import com.kaltsit.entity.sys.SysLogEntity;
import com.kaltsit.mapper.sys.SysLogMapper;
import com.kaltsit.utils.MapUtils;
import com.kaltsit.utils.PageUtils;
import com.kaltsit.utils.QueryWrapperUtils;
import org.springframework.stereotype.Service;

@Service
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLogEntity> implements ISysLogService {

    public PageUtils<SysLogEntity> queryPage(MapUtils params){
        QueryWrapper<SysLogEntity> qw = QueryWrapperUtils.createQueryWrapper(new SysLogEntity(), params);
        Page<SysLogEntity> page = new Page<>(params.getInteger("page", 1), params.getInteger("size", 10));
        IPage<SysLogEntity> result = this.page(page, qw);
        return new PageUtils<>(result);
    }

}
