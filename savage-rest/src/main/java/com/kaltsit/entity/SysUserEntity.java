package com.kaltsit.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.kaltsit.abstracts.AbstractSysUser;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName(value = "user")
@NoArgsConstructor
@AllArgsConstructor
public class SysUserEntity extends AbstractSysUser {

    @ApiModelProperty(notes = "密码")
    private String password;

    @ApiModelProperty(notes = "盐值")
    private String salt;

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

    /**
     * 在set方法上加@JsonProperty,在get方法上加@JsonIgnore
     * 即可在返回json时忽略属性，在使用实体类的时候不会受到影响
     */
    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @JsonProperty
    public void setPassword(String password) {
        this.password = password;
    }

    @JsonIgnore
    public String getSalt() {
        return salt;
    }

    @JsonProperty
    public void setSalt(String salt) {
        this.salt = salt;
    }
}
