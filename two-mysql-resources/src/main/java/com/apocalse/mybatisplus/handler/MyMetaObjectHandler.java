package com.apocalse.mybatisplus.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

@Slf4j
@Component

//TODO 多源数据自动填充失败
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert fill ....");
        //this.strictInsertFill(metaObject, "createDate", LocalDateTime.class, LocalDateTime.now()); // 起始版本 3.3.0(推荐使用)
        //this.strictInsertFill(metaObject, "create_date", LocalDateTime.class, LocalDateTime.now());
        this.setFieldValByName("createDate", new Date(), metaObject);// 起始版本 3.3.0(推荐使用)
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update fill ....");
        this.setFieldValByName("createDate", new Date(), metaObject); // 起始版本 3.3.0(推荐)
    }
}
