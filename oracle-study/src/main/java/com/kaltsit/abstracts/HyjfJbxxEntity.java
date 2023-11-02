package com.kaltsit.abstracts;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@TableName("HYJF_JBXX")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(description="婚姻纠纷")
public class HyjfJbxxEntity implements Serializable {

	private static final long serialVersionUID = -8736095356474036222L;

	@TableId(type = IdType.ASSIGN_ID)
	@ApiModelProperty(value = "ID")
	private java.lang.String id;

	@ApiModelProperty(value = "操作人")
	private java.lang.String czr;

}
