package com.kaltsit.abstracts;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public abstract class AbstractSysMenu {
    @TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(notes = "菜单ID")
    private String id;

    @ApiModelProperty(notes = "父菜单ID，一级菜单为0")
    private String parentId;

    @ApiModelProperty(notes = "菜单名称")
    private String name;

    @ApiModelProperty(notes = "菜单名称-中文")
    private String nameZh;

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
}
