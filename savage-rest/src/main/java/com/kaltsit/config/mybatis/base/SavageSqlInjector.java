package com.kaltsit.config.mybatis.base;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.kaltsit.config.mybatis.base.mothods.DeleteWithDelToken;

import java.util.List;

public class SavageSqlInjector extends DefaultSqlInjector {

    @Override
    public List<AbstractMethod> getMethodList(Class<?> mapperClass, TableInfo tableInfo) {
        List<AbstractMethod> methodList = super.getMethodList(mapperClass, tableInfo);
        //增加自定义方法
        methodList.add(new DeleteWithDelToken());
        return methodList;
    }
}
