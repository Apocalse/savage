package com.kaltsit.controller.sys;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.kaltsit.annotation.SysLog;
import com.kaltsit.commons.SysLogType;
import com.kaltsit.service.sys.impl.SysUserServiceImpl;
import com.kaltsit.entity.sys.SysUserEntity;
import com.kaltsit.exception.SavageException;
import com.kaltsit.utils.CookieUtils;
import com.kaltsit.utils.JWTUtil;
import com.kaltsit.utils.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.ServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = "用户API")
@RestController
@RequestMapping("/user")
public class SysUserController {

    private static final String THIS_NAME = "用户";

    @Resource
    private SysUserServiceImpl userService;

    @PostMapping("/login")
    @SysLog(value = THIS_NAME + "登录", type = SysLogType.LOGIN)
    @ApiOperation(value = "登录", notes = "登录" + THIS_NAME, produces = MediaType.APPLICATION_JSON_VALUE)
    public JsonResult<Map<String, Object>> login(@RequestBody SysUserEntity user) {
        Map<String, Object> map = new HashMap<>();
        LambdaQueryWrapper<SysUserEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUserEntity::getUsername, user.getUsername());
        SysUserEntity one = userService.getOne(queryWrapper);
        if (one != null) {
            // 得到 hash 后的密码
            String encodedPassword = new SimpleHash("md5", user.getPassword(), one.getSalt(), 2).toString();
            if (encodedPassword.equals(one.getPassword())) {
                String token = JWTUtil.createToken(one);
                map.put("token", token);
                map.put("username", one.getUsername());
                map.put("userId", one.getId());
                return JsonResult.ok(map);
            } else {
                throw new SavageException("登陆失败，请检查账号或密码是否正确");
            }
        }else {
            throw new SavageException("登陆失败，请检查账号或密码是否正确");
        }

    }

    @PostMapping("/register")
    @SysLog(value = THIS_NAME + "注册", type = SysLogType.ADD)
    @ApiOperation(value = "注册", notes = "注册" + THIS_NAME, produces = MediaType.APPLICATION_JSON_VALUE)
    public JsonResult<String> register(@RequestBody SysUserEntity user) {
        boolean isExist = userService.isExist(user.getUsername());
        if (isExist) {
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

    @PostMapping("/loginOut")
    @SysLog(value = THIS_NAME + "登出", type = SysLogType.LOGIN)
    @ApiOperation(value = "注销", notes = "注销" + THIS_NAME, produces = MediaType.APPLICATION_JSON_VALUE)
    public JsonResult<String> logout(ServletResponse response) {
        CookieUtils.delCookieByName(response, "token");
        CookieUtils.delCookieByName(response, "username");
        CookieUtils.delCookieByName(response, "userId");
        return JsonResult.ok();
    }

    @GetMapping("/isExit")
    @SysLog(value = "判断" + THIS_NAME + "是否存在")
    @ApiOperation(value = "判断", notes = "判断" + THIS_NAME + "是否存在", produces = MediaType.APPLICATION_JSON_VALUE)
    public JsonResult<Boolean> isExit(String username) {
        return JsonResult.ok(userService.isExist(username));
    }

    @GetMapping("/list")
    @SysLog(value = "查询" + THIS_NAME + "列表")
    @ApiOperation(value = "查询", notes = "查询" + THIS_NAME, produces = MediaType.APPLICATION_JSON_VALUE)
    public JsonResult<List<SysUserEntity>> getUserList(){
        return JsonResult.ok(userService.list());
    }

}
