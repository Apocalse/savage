package com.kaltsit.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kaltsit.annotation.SysLog;
import com.kaltsit.sys.entity.SysLogEntity;
import com.kaltsit.sys.entity.SysRoleEntity;
import com.kaltsit.sys.service.impl.SysRoleServiceImpl;
import com.kaltsit.utils.JsonResult;
import com.kaltsit.utils.MapUtils;
import com.kaltsit.utils.PageUtils;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/role")
public class SysRoleController {

    private final static String THIS_NAME = "角色";

    @Resource
    private SysRoleServiceImpl sysRoleService;

    @GetMapping("/pageList")
    @SysLog(THIS_NAME + " - 分页查询")
    @ApiOperation(value = "列表", notes = "分页查询" + THIS_NAME, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = "size", value = "每页条数", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = "roleName", value = "角色名", paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "roleCode", value = "角色代码", paramType = "query", dataType = "string")
    })
    public JsonResult<PageUtils<SysRoleEntity>> pageList(@RequestParam Map<String, Object> params){
        PageUtils<SysRoleEntity> pageList = sysRoleService.queryPage(MapUtils.getInstance(params));
        return JsonResult.ok(pageList);
    }
}
