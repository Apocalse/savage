package com.kaltsit.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@TableName(value = "user")
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

    @TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(notes = "id")
    private String id;

    @ApiModelProperty(notes = "用户名（账号）")
    private String username;

    @ApiModelProperty(notes = "密码")
    private String password;

    @ApiModelProperty(notes = "盐值")
    private String salt;

    @ApiModelProperty(notes = "真实姓名")
    private String userRealName;

    @ApiModelProperty(notes = "年龄")
    private Integer age;

    @ApiModelProperty(notes = "邮箱")
    private String email;

    @ApiModelProperty(notes = "手机号")
    private String mobile;

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

    @ApiModelProperty(notes = "删除的token,删除后将自动填充为该实体的主键值", hidden = true)
    private String delToken;

}
