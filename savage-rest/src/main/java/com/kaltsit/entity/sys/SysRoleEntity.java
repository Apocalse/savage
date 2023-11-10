package com.kaltsit.entity.sys;

import com.baomidou.mybatisplus.annotation.TableName;
import com.kaltsit.abstracts.SavageEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@TableName("sys_role")
@EqualsAndHashCode(callSuper = true)
public class SysRoleEntity extends SavageEntity implements Serializable {

    private static final long serialVersionUID = 9125092817202707098L;

    @ApiModelProperty(notes = "角色代码")
    private String roleCode;

    @ApiModelProperty(notes = "角色名称")
    private String roleName;

}
