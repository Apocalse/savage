package com.kaltsit;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.GetResponse;
import co.elastic.clients.elasticsearch.indices.GetIndexResponse;
import com.alibaba.fastjson2.JSONObject;
import com.kaltsit.config.ESBeanConfig;
import com.kaltsit.es.ESTestUser;
import com.kaltsit.sys.entity.SysMenuEntity;
import com.kaltsit.sys.impl.SysMenuServiceImpl;
import com.kaltsit.sys.impl.SysLogServiceImpl;
import com.kaltsit.utils.ESUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.io.IOException;

/**
 * 自动填充测试
 *
 * @author nieqiurong 2018-08-10 23:47:02.
 */
@Slf4j
@SpringBootTest
@ContextConfiguration
public class SimpleTest {

    @Autowired
    private ESBeanConfig elasticsearchUtils;
    @Autowired
    private ESUtils esUtils;
    @Autowired
    private SysMenuServiceImpl menuService;
    @Autowired
    private SysLogServiceImpl sysLogService;

    @Test
    public void ESTest1() throws IOException {
        GetIndexResponse test = elasticsearchUtils.elasticsearchClient().indices().get(getIndex -> getIndex.index("test_1"));
        System.out.println(test.result().get("test_1").mappings());
    }

    @Test
    public void ESTest2() throws IOException {
        System.out.println(esUtils.requestGet(new JSONObject(), "/test_1" + "/_search"));
    }

    @Test
    public void ESTest3() throws IOException {
        ElasticsearchClient client = elasticsearchUtils.elasticsearchClient();
        System.out.println("连接成功--------------------------");
        GetResponse<ESTestUser> response = client.get(g -> g
                        .index("test_1")
                        .id("1"),
                ESTestUser.class
        );
        System.out.println(response.source());
    }

    @Test
    public void MenuTest01(){
//        Integer currentPage = 1; //当前页数：显示第一页数据
//        Integer pageSize = 2;    //每页显示多少：每页显示2条数据
//        Page<SysLogEntity> page = new Page<>(1, 2);
//        IPage<SysLogEntity> findList = sysLogService.page(page);
//        System.out.println(findList);
        menuService.save(new SysMenuEntity());
    }




}
