package com.kaltsit.entity.sys;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginEntity implements Serializable {

    private static final long serialVersionUID = -5145159610224767848L;

    @ApiModelProperty(notes = "用户名（账号）")
    private String username;

    @ApiModelProperty(notes = "手机号")
    private String mobile;

    @ApiModelProperty(notes = "密码")
    private String password;

    @ApiModelProperty(notes = "验证码序号")
    private String uuid;

    @ApiModelProperty(notes = "验证码")
    private String captcha;

}
