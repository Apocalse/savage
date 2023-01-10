package com.kaltsit.controller;

import com.kaltsit.annotation.SysLog;
import com.kaltsit.commons.SysLogType;
import com.kaltsit.entity.SysMenuEntity;
import com.kaltsit.service.impl.SysMenuServiceImpl;
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

    @SysLog("查询" + THIS_NAME)
    @GetMapping("/list")
    public JsonResult<List<SysMenuEntity>> getMenuList(@RequestParam Map<String, Object> params){
        MapUtils map = MapUtils.getInstance(params);
        return JsonResult.ok().put(menuService.getMenuTree(map.getString("id")).getChildren());
    }

    @SysLog(value = "删除" + THIS_NAME, type = SysLogType.DELETE)
    @PostMapping("/delete/{id}")
    public JsonResult<Void> deleteByMenuId(@PathVariable String id){
        menuService.deleteByMenuId(id);
        return JsonResult.ok();
    }

    @GetMapping("/info/{id}")
    public JsonResult<List<SysMenuEntity>> getMenuInfo(@PathVariable String id){
        return JsonResult.ok().put(menuService.getById(id));
    }

}
