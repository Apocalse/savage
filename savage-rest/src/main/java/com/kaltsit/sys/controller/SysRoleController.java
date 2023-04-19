package com.kaltsit.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kaltsit.sys.entity.SysLogEntity;
import com.kaltsit.sys.entity.SysRoleEntity;
import com.kaltsit.sys.service.impl.SysRoleServiceImpl;
import com.kaltsit.utils.JsonResult;
import com.kaltsit.utils.MapUtils;
import com.kaltsit.utils.PageUtils;
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

    @Resource
    private SysRoleServiceImpl sysRoleService;

    @GetMapping("/pageList")
    public JsonResult<PageUtils<SysRoleEntity>> pageList(@RequestParam Map<String, Object> map){
        MapUtils params = MapUtils.getInstance(map);
        Page<SysRoleEntity> hashMapPage = new Page<>(params.getInteger("page", 1), params.getInteger("size"), 10);
        IPage<SysRoleEntity> page = sysRoleService.page(hashMapPage, new QueryWrapper<>());
        return JsonResult.ok(new PageUtils<>(page));
    }
}
