package com.kaltsit.abstracts;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public abstract class SavageEntity {

    @TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(notes = "id")
    private String id;

    @ApiModelProperty(notes = "备注")
    private String remark;

    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(notes = "创建时间")
    private Date createDate;

    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(notes = "创建人")
    private String createUserId;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(notes = "更新时间")
    private Date updateDate;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(notes = "更新时间")
    private String updateUserId;

    @TableLogic
    @ApiModelProperty(notes = "删除标记，0-未删除，1-已删除", hidden = true)
    private int delFlag;

}
