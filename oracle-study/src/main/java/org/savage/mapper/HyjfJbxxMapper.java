package org.savage.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.savage.constant.DataSourceConstant;
import org.savage.entity.HyjfJbxxEntity;

import java.util.Map;
import java.util.List;

@Mapper
@DS(DataSourceConstant.DS_XSM_BIZ)
public interface HyjfJbxxMapper extends BaseMapper<HyjfJbxxEntity> {

    List<Map<String, Object>> getList();

}
