package com.kaltsit;

import com.kaltsit.filter.JWTFilter;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 启动器
 *
 * @author nieqiurong 2018-08-10 22:54:15.
 */
@SpringBootApplication
@EnableSwagger2
public class MybatisPlusApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisPlusApplication.class, args);
    }

    /**
     * filter过滤
     */
    @Bean
    public ShiroFilterFactoryBean factory(@Qualifier("securityManager") DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        // 添加自己的过滤器并且取名为jwt
        LinkedHashMap<String, Filter> filters = new LinkedHashMap<>();
        factoryBean.setSecurityManager(securityManager);
        filters.put("jwt", new JWTFilter());
        factoryBean.setFilters(filters);
        //自定义url规则 http://shiro.apache.org/web.html#urls-
        Map<String, String> filterRuleMap = new HashMap<>();
        // 所有请求通过我们自己的JWT Filter
        filterRuleMap.put("/**", "jwt");
        // 访问401和404页面不通过我们的Filter
        filterRuleMap.put("/401", "anon");
        filterRuleMap.put("/user/login", "anon");
        filterRuleMap.put("/user/register", "anon");
        filterRuleMap.put("/user/isExit", "anon");
        //swagger配置放行
        filterRuleMap.put("/swagger-ui.html", "anon");
        filterRuleMap.put("/swagger-resources/**", "anon");
        filterRuleMap.put("/v2/**", "anon");
        filterRuleMap.put("/webjars/**", "anon");
        filterRuleMap.put("/","anon");
        filterRuleMap.put("/csrf","anon");
        factoryBean.setFilterChainDefinitionMap(filterRuleMap);
        return factoryBean;
    }
}
