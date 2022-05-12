package com.apocalse.mybatisplus.entity.db2;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("person")
public class Person {
    @TableId(type = IdType.ASSIGN_ID) //主键
    private Long id;
    private String name;
    private Integer age;
}
