package com.kaltsit.entity.sys;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.kaltsit.ascept.Dict;
import com.kaltsit.commons.SavageEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("sys_dict")
public class SysDictEntity extends SavageEntity implements Serializable {

    private static final long serialVersionUID = -6141894753726125905L;

    @ApiModelProperty(notes = "字典编码")
    private String dictCode;

    @ApiModelProperty(notes = "字典名称")
    private String dictName;

    @TableField(exist = false)
    private List<SysDictItemEntity> itemList;

}
