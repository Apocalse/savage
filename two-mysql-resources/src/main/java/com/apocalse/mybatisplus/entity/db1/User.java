package com.apocalse.mybatisplus.entity.db1;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("user")
public class User {
    @TableId(type = IdType.ASSIGN_ID) //主键
    private Long id;
    private String name;
    private Integer age;
}
