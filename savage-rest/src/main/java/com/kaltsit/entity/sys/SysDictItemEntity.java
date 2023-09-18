package com.kaltsit.entity.sys;

import com.baomidou.mybatisplus.annotation.TableName;
import com.kaltsit.commons.SavageEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("sys_dict_item")
public class SysDictItemEntity extends SavageEntity implements Serializable {

    private static final long serialVersionUID = -6141894753726125905L;

    @ApiModelProperty(notes = "字典表id")
    private String diceId;

    @ApiModelProperty(notes = "字典值编码")
    private String itemCode;

    @ApiModelProperty(notes = "字典值名称")
    private String itemName;

    @ApiModelProperty(notes = "状态")
    private String status;

    @ApiModelProperty(notes = "排序")
    private String sortOrder;


}
