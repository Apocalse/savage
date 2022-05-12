package com.apocalse.mybatisplus.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {
    //主数据源配置 db1数据源
    @Primary
    @Bean(name = "db1DataSourceProperties")
    @ConfigurationProperties(prefix = "spring.datasource.db1")
    public DataSourceProperties db1DataSourceProperties() {
        return new DataSourceProperties();
    }

    //主数据源 db1数据源
    @Primary
    @Bean(name = "db1DataSource")
    public DataSource db1DataSource(@Qualifier("db1DataSourceProperties") DataSourceProperties dataSourceProperties) {
        return dataSourceProperties.initializeDataSourceBuilder().build();
    }

    //第二个db2数据源配置
    @Bean(name = "db2DataSourceProperties")
    @ConfigurationProperties(prefix = "spring.datasource.db2")
    public DataSourceProperties db2DataSourceProperties() {
        return new DataSourceProperties();
    }

    //第二个db2数据源
    @Bean("db2DataSource")
    public DataSource db2DataSource(@Qualifier("db2DataSourceProperties") DataSourceProperties dataSourceProperties) {
        return dataSourceProperties.initializeDataSourceBuilder().build();
    }
}
