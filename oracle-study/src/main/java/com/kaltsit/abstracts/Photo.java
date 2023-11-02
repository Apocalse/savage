package com.kaltsit.abstracts;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class Photo implements Serializable {

    private static final long serialVersionUID = -2514420782642531545L;

    @ApiModelProperty("照片序号")
    private String num;

    @ApiModelProperty("照片名")
    private String name;

    @ApiModelProperty(value = "照片")
    private byte[] zp;

}


