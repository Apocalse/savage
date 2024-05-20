package com.kaltsit.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kaltsit.entity.HyjfJbxxEntity;
import org.apache.ibatis.annotations.Mapper;
import com.kaltsit.constant.DataSourceConstant;

import java.util.Map;
import java.util.List;

@Mapper
@DS(DataSourceConstant.DS_XSM_BIZ)
public interface HyjfJbxxMapper extends BaseMapper<HyjfJbxxEntity> {

    List<Map<String, Object>> getList();

}
