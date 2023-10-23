package com.kaltsit.controller.sys;

import com.google.code.kaptcha.Producer;
import org.savage.utils.DateUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Date;

@Controller
public class CaptchaController {

    /**
     * 超时时间 单位 s
     */
    private static final int EXPIRE_TIME = 5 * 60;

    @Resource
    private Producer captchaProducer;

    @GetMapping("/captcha.jpg")
    public void getCaptcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 设置内容类型
        response.setContentType("image/jpeg");
        // 创建验证码文本
        String capText = captchaProducer.createText();
        // 将验证码文本设置到session
        //request.getSession().setAttribute("captcha", capText);
        DateUtils.addSeconds(new Date(), EXPIRE_TIME);
        // 创建验证码图片
        BufferedImage bi = captchaProducer.createImage(capText);
        // 获取响应输出流
        ServletOutputStream out = response.getOutputStream();
        // 将图片验证码数据写到响应输出流
        ImageIO.write(bi, "jpg", out);
        // 推送并关闭响应输出流
        try {
            out.flush();
        } finally {
            out.close();
        }
    }

}
