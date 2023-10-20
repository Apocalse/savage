package org.savage.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.apache.ibatis.annotations.Mapper;
import org.savage.constant.DataSourceConstant;
import org.savage.entity.Photo;

import java.util.List;

@Mapper
@DS(DataSourceConstant.DS_XSM_135)
public interface MyMapper {
    List<Photo> getPhotos(String csny, String xb, Integer count);

}
