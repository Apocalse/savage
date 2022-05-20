package com.kaltsit.entity;

import com.baomidou.mybatisplus.annotation.*;
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
    private String id;

    private String username;

    private String password;

    private String userRealName;

    private Integer age;

    private String email;

    private String mobile;

    private String remarks;

    @TableField(fill = FieldFill.INSERT)
    private Date createDate;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateDate;

    @TableLogic
    private int delFlag;

}
