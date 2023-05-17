package com.kaltsit.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kaltsit.sys.service.ISysLogService;
import com.kaltsit.sys.entity.SysLogEntity;
import com.kaltsit.sys.mapper.SysLogMapper;
import com.kaltsit.utils.MapUtils;
import com.kaltsit.utils.PageUtils;
import com.kaltsit.utils.QueryWrapperUtils;
import org.springframework.stereotype.Service;

@Service
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLogEntity> implements ISysLogService {

    public PageUtils<SysLogEntity> queryPage(MapUtils params){
        String searchKey = params.getString("searchKey");
        String userId = params.getString("userId");
        Integer type = params.getInteger("type");
        String startDate = params.getString("startDate");
        String endDate = params.getString("endDate");
        String order = params.getString("order");
        String column = params.getString("column");
        QueryWrapper<SysLogEntity> qw = new QueryWrapper<>();
        LambdaQueryWrapper<SysLogEntity> lqw = QueryWrapperUtils.like(qw.lambda(), searchKey, SysLogEntity::getOperation);
        if(StringUtils.isNotEmpty(userId)){
            lqw.eq(SysLogEntity::getCreateUserId, userId);
        }
        if(StringUtils.isNotEmpty(startDate)){
            lqw.ge(SysLogEntity::getCreateDate, startDate);
        }
        if(StringUtils.isNotEmpty(endDate)){
            lqw.le(SysLogEntity::getCreateDate, endDate);
        }
        if(type != null){
            lqw.eq(SysLogEntity::getType, type);
        }
        QueryWrapperUtils.order(qw, column, order);
        Page<SysLogEntity> page = new Page<>(params.getInteger("page", 1), params.getInteger("size", 10));
        IPage<SysLogEntity> result = this.page(page, qw);
        return new PageUtils<>(result);
    }

}
