package com.kaltsit.controller.sys;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.kaltsit.ascept.annotation.SysLog;
import com.kaltsit.constant.CommonConstant;
import com.kaltsit.constant.RedisConstant;
import com.kaltsit.entity.sys.LoginEntity;
import com.kaltsit.entity.sys.SysUserEntity;
import com.kaltsit.exception.SavageException;
import com.kaltsit.service.sys.impl.LoginServiceImpl;
import com.kaltsit.service.sys.impl.SysUserServiceImpl;
import com.kaltsit.utils.CookieUtils;
import com.kaltsit.utils.JWTUtil;
import com.kaltsit.utils.JsonResult;
import com.kaltsit.utils.RedisUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Api(tags = "登录相关API")
@RestController
public class LoginController {

    private static final String THIS_NAME = "用户";
    @Resource
    private LoginServiceImpl loginService;
    @Resource
    private SysUserServiceImpl userService;

    @PostMapping("/login")
    @SysLog(value = THIS_NAME + "登录", type = CommonConstant.SYSLOG_LOGIN)
    @ApiOperation(value = "登录", notes = "登录" + THIS_NAME, produces = MediaType.APPLICATION_JSON_VALUE)
    public JsonResult<Map<String, Object>> login(@RequestBody LoginEntity loginUser) {
        Map<String, Object> map = new HashMap<>();
        if (StringUtils.isNotEmpty(loginUser.getUuid())) { //验证码校验
            String captcha = (String) RedisUtil.get(RedisConstant.CAPTCHA + loginUser.getUuid());
            RedisUtil.delete(RedisConstant.CAPTCHA + loginUser.getUuid());
            if (StringUtils.isEmpty(captcha)) {
                throw new SavageException("验证码已过期");
            }
            if (!captcha.equals(loginUser.getCaptcha())) {
                throw new SavageException("验证码错误");
            }
        }
        LambdaQueryWrapper<SysUserEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUserEntity::getUsername, loginUser.getUsername());
        SysUserEntity one = userService.getOne(queryWrapper);
        if (one != null) {
            // 得到 hash 后的密码
            String encodedPassword = new SimpleHash("md5", loginUser.getPassword(), one.getSalt(), 2).toString();
            if (encodedPassword.equals(one.getPassword())) {
                String token = JWTUtil.createToken(one.getId(), one.getUsername());
                map.put("token", token);
                map.put("username", one.getUsername());
                map.put("userId", one.getId());
                return JsonResult.ok(map);
            } else {
                throw new SavageException("登陆失败，请检查账号或密码是否正确");
            }
        } else {
            throw new SavageException("登陆失败，请检查账号或密码是否正确");
        }
    }

    @PostMapping("/register")
    @SysLog(value = THIS_NAME + "注册", type = CommonConstant.SYSLOG_ADD)
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
    @SysLog(value = THIS_NAME + "登出", type = CommonConstant.SYSLOG_LOGIN)
    @ApiOperation(value = "注销", notes = "注销" + THIS_NAME, produces = MediaType.APPLICATION_JSON_VALUE)
    public JsonResult<String> logout(ServletResponse response) {
        CookieUtils.delCookieByName(response, "token");
        CookieUtils.delCookieByName(response, "username");
        CookieUtils.delCookieByName(response, "userId");
        return JsonResult.ok();
    }

    @GetMapping("/captcha.jpg")
    public void getCaptcha(String uuid, HttpServletResponse response) throws IOException {
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");
        BufferedImage image = loginService.getCaptcha(uuid);
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);
        IOUtils.closeQuietly(out);
    }

}
