package com.kaltsit.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.kaltsit.utils.JWTUtil;
import com.kaltsit.utils.UserUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * mybatis-plus填充器
 */
@Slf4j
@Component
public class LavaMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
//        log.info("start insert fill ....");
        this.strictInsertFill(metaObject, "createDate", Date.class, new Date());
        this.strictInsertFill(metaObject, "updateDate", Date.class, new Date());
        this.strictInsertFill(metaObject, "createUserId", String.class, UserUtils.getCurrentUserId());
        this.strictInsertFill(metaObject, "updateUserId", String.class, UserUtils.getCurrentUserId());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
//        log.info("start update fill ....");
        this.setFieldValByName("updateDate", new Date(), metaObject);
        this.strictInsertFill(metaObject, "updateUserId", String.class, UserUtils.getCurrentUserId());
    }
}
