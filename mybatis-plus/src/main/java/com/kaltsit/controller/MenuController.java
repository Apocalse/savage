package com.kaltsit.controller;

import com.kaltsit.entity.MenuEntity;
import com.kaltsit.service.impl.MenuServiceImpl;
import com.kaltsit.utils.JsonResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {
    @Resource
    private MenuServiceImpl menuService;

    @GetMapping("/list")
    public JsonResult<List<MenuEntity>> getMenuList(@RequestParam String id){
        return JsonResult.ok().put(menuService.getMenuTree(id).getChildren());
    }

}
