package com.kaltsit.sys.service.impl;

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
        QueryWrapper<SysLogEntity> qw = QueryWrapperUtils.wrapperLike(new QueryWrapper<>(), searchKey, "operation");
        if(StringUtils.isNotEmpty(userId)){
            qw.eq("create_user_id", userId);
        }
        if(StringUtils.isNotEmpty(startDate)){
            qw.ge("create_date", startDate);
        }
        if(StringUtils.isNotEmpty(endDate)){
            qw.le("create_date", endDate);
        }
        if(type != null){
            qw.eq("type", type);
        }
        qw.orderByDesc("create_date");
        Page<SysLogEntity> hashMapPage = new Page<>(Integer.parseInt((String) params.get("page")), Integer.parseInt((String) params.get("limit")));
        IPage<SysLogEntity> page = this.page(hashMapPage, qw);
        return new PageUtils<>(page);
    }

}
