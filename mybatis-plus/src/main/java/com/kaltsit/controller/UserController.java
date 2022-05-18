package com.kaltsit.controller;

import com.kaltsit.entity.UserEntity;
import com.kaltsit.service.UserService;
import com.kaltsit.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @CrossOrigin
    @PostMapping("/login")
    public JsonResult<List<UserEntity>> test(@RequestParam Map<String, Object> params) {

        return JsonResult.ok().put(userService.list());
    }

}
