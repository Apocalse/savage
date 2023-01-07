package com.apocalse.mybatisplus;

import com.apocalse.mybatisplus.entity.db1.UserEntity;
import com.apocalse.mybatisplus.entity.db2.PersonEntity;
import com.apocalse.mybatisplus.mapper.db1.UserMapper;
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
    void insertDb1(){
        UserEntity user = new UserEntity();
        user.setName("AAA-2");
        user.setAge(18);
        userService.save(user);
    }

    @Test
    void updateDb1(){
        UserEntity user = new UserEntity();
        user.setName("AAA-AAA");
        user.setId("1524722145007067138");

        userService.getBaseMapper().updateById(user);
    }

    @Test
    void selectDb1(){
       // System.out.println(userService.getBaseMapper().getList());
        System.out.println(userService.list());
    }

    @Test
    void insertDb2(){
        PersonEntity person = new PersonEntity();
        person.setName("AAA");
        person.setAge(18);
        personService.getBaseMapper().insert(person);
    }

    @Test
    void selectDb2(){
        System.out.println(personService.getBaseMapper().getList());
        System.out.println(personService.list());
    }

}
