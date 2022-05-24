package com.kaltsit;

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
        MenuEntity menu = new MenuEntity();
        menu.setName("菜单管理");
        menu.setUrl("/menu");
        menu.setParentId("1529138714366267394");
        menu.setOrderNum(10);
        menu.setType(MenuLevel.SECOND);
        menu.setStatus(VisibleStatus.SHOW);
        menu.setIcon("el-icon-menu");
        menuService.save(menu);
    }


}
