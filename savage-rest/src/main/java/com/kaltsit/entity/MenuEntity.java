package com.kaltsit.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.kaltsit.abstracts.AbstractMenu;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("menu")
@NoArgsConstructor
@AllArgsConstructor
public class MenuEntity extends AbstractMenu {

    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(notes = "创建时间")
    private Date createDate;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(notes = "更新时间")
    private Date updateDate;

    @TableLogic
    @ApiModelProperty(notes = "删除标记，0-未删除，1-已删除", hidden = true)
    private int delFlag;

    @TableField(exist = false)
    private List<MenuEntity> children = new ArrayList<>();
}
