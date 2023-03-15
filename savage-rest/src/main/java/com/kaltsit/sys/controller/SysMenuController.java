package com.kaltsit.sys.controller;

import com.kaltsit.annotation.SysLog;
import com.kaltsit.commons.SysLogType;
import com.kaltsit.sys.entity.SysMenuEntity;
import com.kaltsit.sys.service.impl.SysMenuServiceImpl;
import com.kaltsit.utils.JsonResult;
import com.kaltsit.utils.MapUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/menu")
public class SysMenuController {
    @Resource
    private SysMenuServiceImpl menuService;

    private static final String THIS_NAME = "菜单";

    @SysLog(THIS_NAME + " - 查询")
    @GetMapping("/list")
    public JsonResult<List<SysMenuEntity>> getMenuList(@RequestParam Map<String, Object> params){
        MapUtils map = MapUtils.getInstance(params);
        SysMenuEntity menuTree = menuService.getMenuTree(map.getString("id"), map.getString("status"));
        return JsonResult.ok(menuTree.getChildren());
    }

    @SysLog(value = THIS_NAME + " - 删除", type = SysLogType.DELETE)
    @PostMapping("/delete/{id}")
    public JsonResult<Void> deleteByMenuId(@PathVariable String id){
        menuService.deleteByMenuId(id);
        return JsonResult.ok();
    }

    @SysLog(value = THIS_NAME + " - 详情", type = SysLogType.DELETE)
    @GetMapping("/info/{id}")
    public JsonResult<SysMenuEntity> getMenuInfo(@PathVariable String id){
        return JsonResult.ok(menuService.getById(id));
    }

    @SysLog(value = THIS_NAME + " - 保存", type = SysLogType.DELETE)
    @PostMapping("/add")
    public JsonResult<Void> saveMenu(@RequestBody SysMenuEntity sysMenuEntity){
        menuService.saveOrUpdate(sysMenuEntity);
        return JsonResult.ok();
    }

    @SysLog(value = THIS_NAME + " - 更新", type = SysLogType.DELETE)
    @PostMapping("/update")
    public JsonResult<Void> updateMenu(@RequestBody SysMenuEntity sysMenuEntity){
        menuService.saveOrUpdate(sysMenuEntity);
        return JsonResult.ok();
    }

}
