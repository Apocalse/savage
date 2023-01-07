package com.apocalse.mybatisplus.entity.db1;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@TableName("user")
public class UserEntity {
    @TableId(type = IdType.ASSIGN_ID) //主键
    private String id;

    private String name;

    private Integer age;

    @TableField(fill = FieldFill.INSERT)
    private Date createDate;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateDate;

    @TableLogic
    private int delFlag;
}
