package com.kaltsit.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.kaltsit.abstracts.Photo;
import org.apache.ibatis.annotations.Mapper;
import com.kaltsit.constant.DataSourceConstant;

import java.util.List;

@Mapper
@DS(DataSourceConstant.DS_XSM_135)
public interface MyMapper {
    List<Photo> getPhotos(String csny, String xb, Integer count);

}
