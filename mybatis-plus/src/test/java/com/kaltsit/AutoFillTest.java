package com.kaltsit;

import com.kaltsit.service.UserService;
import com.kaltsit.entity.UserEntity;
import com.kaltsit.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.Date;

/**
 * 自动填充测试
 *
 * @author nieqiurong 2018-08-10 23:47:02.
 */
@Slf4j
@SpringBootTest
@ContextConfiguration
public class AutoFillTest {

    @Autowired
    private UserService userService;

    @Test
    public void insert() {
        UserEntity user = new UserEntity();
        user.setAge(18);

        user.setEmail("aaa@123.com");
        userService.save(user);
    }

    @Test
    public void update() {
        UserEntity user = userService.getById("1525024320316833794");
        user.setAge(12);
        user.setEmail("ddd@123.com");
        userService.updateById(user);
    }

    @Test
    public void select() {
//        System.out.println(userService.getBaseMapper().getList());
        System.out.println(userService.list());
    }

    @Test
    public void delete(){
        userService.getBaseMapper().deleteById("1525024320316833794");
    }

    @Test void getNow(){
        System.out.println(DateUtils.formatDateTime(new Date()));
    }


}
