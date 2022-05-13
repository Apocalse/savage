package com.kaltsit.controller;

import com.kaltsit.entity.UserEntity;
import com.kaltsit.service.UserService;
import com.kaltsit.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/test")
    public JsonResult<List<UserEntity>> test() {
        return JsonResult.ok().put(userService.list());
    }

}
