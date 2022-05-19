package com.kaltsit.controller;

import com.kaltsit.entity.UserEntity;
import com.kaltsit.service.UserService;
import com.kaltsit.utils.JsonResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class UserController {
    @Resource
    private UserService userService;

    @PostMapping("/login")
    public JsonResult<List<UserEntity>> test() {

        return JsonResult.ok().put(userService.list());
    }

}
