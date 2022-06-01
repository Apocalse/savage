package com.kaltsit.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.kaltsit.entity.UserEntity;
import com.kaltsit.exception.SavageException;
import com.kaltsit.service.impl.UserServiceImpl;
import com.kaltsit.utils.JsonResult;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
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

        String username = user.getUsername();
        Subject subject = SecurityUtils.getSubject();
//        subject.getSession().setTimeout(10000);
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, user.getPassword());

        try {
            subject.login(usernamePasswordToken);
            LambdaQueryWrapper<UserEntity> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(UserEntity::getUsername, user.getUsername());
            UserEntity one = userService.getOne(queryWrapper);
            map.put("token", "123456");
            map.put("username", one.getUsername());
            map.put("userId", one.getId());
            return JsonResult.ok().put(map);
        } catch (Exception e) {
            throw new SavageException("登陆失败");
        }
    }

    @PostMapping("/register")
    public JsonResult<String> register(@RequestBody UserEntity user){
        boolean isExist = userService.isExist(user.getUsername());
        if(isExist){
            return JsonResult.error("账号已存在");
        }
        // 生成盐,默认长度 16 位
        String salt = new SecureRandomNumberGenerator().nextBytes().toString();
        // 设置 hash 算法迭代次数
        int times = 2;
        // 得到 hash 后的密码
        String encodedPassword = new SimpleHash("md5", user.getPassword(), salt, times).toString();
        user.setSalt(salt);
        user.setPassword(encodedPassword);
        userService.save(user);
        return JsonResult.ok();
    }

    @GetMapping("/hello")
    public JsonResult<List<UserEntity>> test(){
        return JsonResult.ok().put("hello");
    }

}
