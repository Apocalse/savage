package com.kaltsit;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.GetResponse;
import co.elastic.clients.elasticsearch.indices.GetIndexResponse;
import com.alibaba.fastjson2.JSONObject;
import com.kaltsit.config.ESBeanConfig;
import com.kaltsit.es.ESTestUser;
import com.kaltsit.service.impl.SysLogServiceImpl;
import com.kaltsit.service.impl.SysMenuServiceImpl;
import com.kaltsit.utils.ESUtils;
import com.kaltsit.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private RedisUtil redisUtil;

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
    public void MenuTest01() {
        String fileName = "C:\\Users\\20201236\\Desktop\\新建文件夹\\test.xlsx";
        //EasyExcel.write(fileName, SysLogEntity.class).sheet("模板").doWrite(sysLogService.list());
    }

    @Test
    void redisTest() {
        Map<String, Object> dict = new HashMap<>();
        dict.put("0", "未知");
        dict.put("1", "新增");
        dict.put("2", "删除");
        dict.put("3", "修改");
        dict.put("4", "查询");
        dict.put("5", "登录/登出");
        redisUtil.add("SYS:LOG_TYPE", dict);
        System.out.println(redisUtil.getHashEntries("SYS:LOG_TYPE"));
    }


//    private static List converDict(Class objectClass, List data){
//        HashMap<String, Field> map = new HashMap();
//        Field[] fields =objectClass.getDeclaredFields();
//        Arrays.stream(fields).forEach(e->{
//            ExcelVaild annotation = e.getAnnotation(ExcelVaild.class);
//            if (null!=annotation){
//                String s = annotation.dictType();
//                if (StringUtils.isNotEmpty(s)){
//                    map.put(s, e);
//                }
//            }
//        });
//        data.stream().forEach(e->{
//            try {
//                for (String key : map.keySet()) {
//                    Field field = map.get(key);
//                    field.setAccessible(true);
//                    String value = field.get(e).toString();
//                    if (StringUtils.isNotEmpty(value)){
//                        String labelByCache = DictUtil.getLabelByCache(key, value);
//                        map.get(key).set(e,labelByCache);
//                    }
//                }
//            }catch (Exception E){
//                E.getMessage();
//            }
//
//        });
//
//        return data;
//    }


}
