package com.kaltsit.sys.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.kaltsit.commons.SavageEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName(value = "sys_user")
@NoArgsConstructor
@AllArgsConstructor
public class SysUserEntity extends SavageEntity implements Serializable {

    private static final long serialVersionUID = -5145159610224767847L;

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

    @ApiModelProperty(notes = "密码")
    private String password;

    @ApiModelProperty(notes = "盐值")
    private String salt;

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
