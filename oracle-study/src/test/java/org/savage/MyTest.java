package org.savage;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.savage.mapper.HyjfJbxxMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@Slf4j
@SpringBootTest
@ContextConfiguration
public class MyTest {
    @Autowired
    private HyjfJbxxMapper hyjfJbxxMapper;

    @Test
    void myTest1(){
        hyjfJbxxMapper.selectById("1714518493372596226");
    }

}
