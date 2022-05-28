package com.kaltsit.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.kaltsit.entity.UserEntity;
import com.kaltsit.exception.SavageException;
import com.kaltsit.service.impl.UserServiceImpl;
import com.kaltsit.utils.JsonResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserServiceImpl userService;

    @PostMapping("/login")
    public JsonResult<Map<String, Object>> login(@RequestBody UserEntity user) {
        Map<String, Object> map = new HashMap<>();
        if (StringUtils.isEmpty(user.getUsername())) {
            return JsonResult.error("请输入账号或密码");
        }
        LambdaQueryWrapper<UserEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserEntity::getUsername, user.getUsername());
        queryWrapper.eq(UserEntity::getPassword, user.getPassword());
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

    @PostMapping("/register")
    public JsonResult<String> register(@RequestBody UserEntity user){
        userService.save(user);
        return JsonResult.ok();
    }

    @GetMapping("/hello")
    public JsonResult<List<UserEntity>> test(){
        return JsonResult.ok().put("hello");
    }

}
