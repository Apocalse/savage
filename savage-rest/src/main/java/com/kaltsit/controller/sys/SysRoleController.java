package com.kaltsit.controller.sys;

import com.kaltsit.ascept.annotation.SysLog;
import com.kaltsit.entity.sys.SysRoleEntity;
import com.kaltsit.service.sys.impl.SysRoleServiceImpl;
import com.kaltsit.utils.PageUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import com.kaltsit.utils.JsonResult;
import com.kaltsit.utils.MapUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

@Api(tags = "角色API")
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

    @PostMapping("/save")
    @SysLog(THIS_NAME + " - 新增")
    @ApiOperation(value = "新增角色", produces = MediaType.APPLICATION_JSON_VALUE)
    public JsonResult<Void> saveSysRole(@RequestBody SysRoleEntity sysRoleEntity){
        sysRoleService.saveSysRole(sysRoleEntity);
        return JsonResult.ok();
    }

    @PostMapping("/update")
    @SysLog(THIS_NAME + " - 更新")
    @ApiOperation(value = "更新角色", produces = MediaType.APPLICATION_JSON_VALUE)
    public JsonResult<Void> updateSysRole(@RequestBody SysRoleEntity sysRoleEntity){
        sysRoleService.updateSysRole(sysRoleEntity);
        return JsonResult.ok();
    }

}
