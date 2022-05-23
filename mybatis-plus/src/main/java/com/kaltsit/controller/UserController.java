package com.kaltsit.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.kaltsit.entity.UserEntity;
import com.kaltsit.service.UserService;
import com.kaltsit.utils.JsonResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {
    @Resource
    private UserService userService;

    @PostMapping("/login")
    public JsonResult<List<UserEntity>> login(@RequestBody UserEntity form) {
        Map<String, Object> map = new HashMap<>();
        if (StringUtils.isEmpty(form.getUsername())) {
            return null;
        }
        LambdaQueryWrapper<UserEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserEntity::getUsername, form.getUsername());
        queryWrapper.eq(UserEntity::getPassword, form.getPassword());
        UserEntity one = userService.getOne(queryWrapper);
        if(one != null){
            map.put("token", "123456");
            map.put("username", one.getUsername());
            map.put("userId", one.getId());
            return JsonResult.ok().put(map);
        }else{
            return JsonResult.error("账号或密码错误");
        }

    }

    @GetMapping("/hello")
    public JsonResult<List<UserEntity>> test(){
        return JsonResult.ok().put("hello");
    }

}
