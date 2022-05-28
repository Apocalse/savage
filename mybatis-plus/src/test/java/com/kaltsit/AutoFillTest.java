package com.kaltsit;

import com.alibaba.fastjson.JSONObject;
import com.kaltsit.commons.MenuLevel;
import com.kaltsit.commons.VisibleStatus;
import com.kaltsit.entity.MenuEntity;
import com.kaltsit.entity.UserEntity;
import com.kaltsit.service.impl.MenuServiceImpl;
import com.kaltsit.service.impl.UserServiceImpl;
import com.kaltsit.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.Date;
import java.util.List;

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
    private UserServiceImpl userService;
    @Autowired
    private MenuServiceImpl menuService;

    @Test
    public void insert() {
//        MenuEntity menu = new MenuEntity();
//        System.out.println(menu.toString());

//        List<MenuEntity> list = menuService.list();

//       System.out.println(JSONObject.toJSONString(list));

//        System.out.println(JSONObject.toJSONString(menuService.getMenuTree("1529139209180860417")));
    }

    @Test
    public void test(){
//        String s = "{\"Apikey\":\"65b4fa8dd7de4c61803cebccdf609826\",\"Connection\":\"Keep-Alive\",\"User-Agent\":\"Apache-HttpClient/4.5.2 (Java/1.8.0_191)\",\"fromip\":\"10.210.14.46\",\"Host\":\"10.210.0.20: 80\",\"nginx_ip\":\"10.210.0.22\",\"Content-Type\":\"application/json; charset=UTF-8\"}";
    }


}
