package com.kaltsit.service.sys.impl;

import com.google.code.kaptcha.Producer;
import com.kaltsit.constant.RedisConstant;
import com.kaltsit.exception.SavageException;
import com.kaltsit.utils.RedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.awt.image.BufferedImage;

@Service
public class LoginServiceImpl {

    private static final int EXPIRE_TIME = 5 * 60;
    @Resource
    private Producer producer;

    public BufferedImage getCaptcha(String uuid) {
        if (StringUtils.isBlank(uuid)) {
            throw new SavageException("uuid不能为空");
        }
        String code = producer.createText();
        RedisUtil.set(RedisConstant.CAPTCHA + uuid, code, EXPIRE_TIME);
        return producer.createImage(code);
    }

    public boolean validate(String uuid, String code) {
        String s = (String) RedisUtil.get("captcha:" + uuid);
        if (s == null) {
            throw new SavageException("验证码已过期");
        }
        RedisUtil.delete("captcha:" + uuid);
        return s.equalsIgnoreCase(code);
    }

}
