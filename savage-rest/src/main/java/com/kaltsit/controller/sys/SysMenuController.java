package com.kaltsit.controller.sys;

import com.kaltsit.annotation.SysLog;
import com.kaltsit.commons.SysLogType;
import com.kaltsit.entity.sys.SysMenuEntity;
import com.kaltsit.service.sys.impl.SysMenuServiceImpl;
import com.kaltsit.utils.JsonResult;
import com.kaltsit.utils.MapUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Api(tags = "菜单API")
@RestController
@RequestMapping("/menu")
public class SysMenuController {

    private static final String THIS_NAME = "菜单";

    @Resource
    private SysMenuServiceImpl menuService;

    @GetMapping("/list")
    @SysLog(THIS_NAME + " - 查询")
    @ApiOperation(value = "菜单树", notes = "查询" + THIS_NAME + "树", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "rootId", value = "根目录id", paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "status", value = "状态", paramType = "query", dataType = "string")
    })
    public JsonResult<List<SysMenuEntity>> getMenuList(@RequestParam Map<String, Object> params){
        MapUtils map = MapUtils.getInstance(params);
        SysMenuEntity menuTree = menuService.getMenuTree(map.getString("rootId"), map.getString("status"));
        return JsonResult.ok(menuTree.getChildren());
    }

    @PostMapping("/delete/{id}")
    @SysLog(value = THIS_NAME + " - 删除", type = SysLogType.DELETE)
    @ApiOperation(value = "删除菜单", notes = "删除" + THIS_NAME, produces = MediaType.APPLICATION_JSON_VALUE)
    public JsonResult<Void> deleteByMenuId(@PathVariable String id){
        menuService.deleteByMenuId(id);
        return JsonResult.ok();
    }

    @GetMapping("/info/{id}")
    @SysLog(value = THIS_NAME + " - 详情", type = SysLogType.DELETE)
    @ApiOperation(value = "菜单详情", notes = THIS_NAME + "详情", produces = MediaType.APPLICATION_JSON_VALUE)
    public JsonResult<SysMenuEntity> getMenuInfo(@PathVariable String id){
        return JsonResult.ok(menuService.getById(id));
    }

    @PostMapping("/saveOrUpdate")
    @SysLog(value = THIS_NAME + " - 保存", type = SysLogType.DELETE)
    @ApiOperation(value = "新增", notes = "新增" + THIS_NAME, produces = MediaType.APPLICATION_JSON_VALUE)
    public JsonResult<Void> saveMenu(@RequestBody SysMenuEntity sysMenuEntity){
        menuService.saveOrUpdate(sysMenuEntity);
        return JsonResult.ok();
    }

}
