package com.kaltsit.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@TableName("menu")
@NoArgsConstructor
@AllArgsConstructor
public class MenuEntity {

    @TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(notes = "菜单ID")
    private String id;

    @ApiModelProperty(notes = "父菜单ID，一级菜单为0")
    private String parentId;

    @ApiModelProperty(notes = "菜单名称")
    private String name;

    @ApiModelProperty(notes = "菜单URL")
    private String url;

    @ApiModelProperty(notes = "vue文件路径")
    private String path;

    @ApiModelProperty(notes = "0：目录 1：一级菜单 2：二级菜单 3：按钮")
    private int type;

    @ApiModelProperty(notes = "菜单图标")
    private String icon;

    @ApiModelProperty(notes = "排序")
    private int orderNum;

    @ApiModelProperty(notes = "是否隐藏（隐藏后将不会在页面上显示）")
    private int status;

    @ApiModelProperty(notes = "备注")
    private String remarks;

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
