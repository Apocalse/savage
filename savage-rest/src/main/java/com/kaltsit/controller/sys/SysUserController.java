package com.kaltsit.controller.sys;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.kaltsit.ascept.annotation.SysLog;
import com.kaltsit.service.sys.impl.SysUserServiceImpl;
import com.kaltsit.entity.sys.SysUserEntity;
import com.kaltsit.exception.SavageException;
import com.kaltsit.utils.JWTUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import com.kaltsit.constant.CommonConstant;
import com.kaltsit.utils.CookieUtils;
import com.kaltsit.utils.JsonResult;
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
