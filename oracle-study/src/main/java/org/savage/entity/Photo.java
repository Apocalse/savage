package org.savage.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class Photo implements Serializable {

    private static final long serialVersionUID = -2514420782642531545L;

    @ApiModelProperty(value = "照片")
    private byte[] zp;

    @ApiModelProperty("照片类型")
    private String zplx;

}


