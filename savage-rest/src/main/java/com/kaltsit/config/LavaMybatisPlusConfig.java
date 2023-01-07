package com.kaltsit.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.kaltsit.base.LavaSqlInjector;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.kaltsit.mapper")
public class LavaMybatisPlusConfig {

    /**
     * 自定义的sql注入
     * @return LavaSqlInjector
     */
    @Bean
    public LavaSqlInjector sqlInjector() {
        return new LavaSqlInjector();
    }

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL)); //注意使用哪种数据库
        return interceptor;
    }

}
