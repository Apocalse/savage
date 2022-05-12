package com.apocalse.mybatisplus;

import com.apocalse.mybatisplus.service.db1.UserServiceImpl;
import com.apocalse.mybatisplus.service.db2.PersonServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MybatisPlusApplicationTests {
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private PersonServiceImpl personService;

    @Test
    void selectDb1(){
        System.out.println(userService.getBaseMapper().getList());
        System.out.println(userService.list());
    }

    @Test
    void selectDb2(){
        System.out.println(personService.getBaseMapper().getList());
        System.out.println(personService.list());
    }

}
