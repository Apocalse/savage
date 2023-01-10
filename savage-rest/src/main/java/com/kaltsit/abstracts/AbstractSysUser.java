package com.kaltsit.abstracts;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public abstract class AbstractSysUser {
    @TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(notes = "id")
    private String id;

    @ApiModelProperty(notes = "用户名（账号）")
    private String username;

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
}
