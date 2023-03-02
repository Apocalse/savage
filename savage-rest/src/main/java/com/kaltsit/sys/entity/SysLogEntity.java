package com.kaltsit.sys.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@TableName("sys_log")
@Data
@ApiModel
public class SysLogEntity implements Serializable {
    @TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(notes = "ID")
    private String id;

    @ApiModelProperty(notes = "日志类型")
    private Integer type;

    @ApiModelProperty(notes = "用户名")
    private String username;

    @ApiModelProperty(notes = "用户操作")
    private String operation;

    @ApiModelProperty(notes = "请求方法")
    private String method;

    @ApiModelProperty(notes = "请求参数")
    private String params;

    @ApiModelProperty(notes = "执行时长(毫秒)")
    private Long time;

    @ApiModelProperty(notes = "IP地址")
    private String ip;

    @ApiModelProperty(notes = "请求URI")
    private String requestUri;

    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(notes = "创建时间")
    private Date createDate;

    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(notes = "用户ID")
    private String createUserId;

    @TableLogic
    @ApiModelProperty(notes = "删除标记，0-未删除，1-已删除", hidden = true)
    private int delFlag;
}
