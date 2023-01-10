package com.kaltsit.controller;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.GetResponse;
import co.elastic.clients.elasticsearch.indices.GetIndexResponse;
import com.alibaba.fastjson2.JSONObject;
import com.kaltsit.annotation.SysLog;
import com.kaltsit.config.ESBeanConfig;
import com.kaltsit.entity.ESTestUser;
import com.kaltsit.utils.ESUtils;
import com.kaltsit.utils.JsonResult;
import com.kaltsit.utils.MapUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/es")
public class ESController {
    @Resource
    private ESUtils esUtils;
    @Resource
    private ESBeanConfig esBeanConfig;

    private static final String THIS_NAME = "ES功能";

    @GetMapping("/demo1")
    @SysLog(THIS_NAME + "查询全部-自定义方法")
    public JsonResult<String> demo1(){
        try {
            String s = esUtils.requestGet(new JSONObject(), "/test_1" + "/_search");
            return JsonResult.ok(JSONObject.parse(s).toString());
        } catch (IOException e) {
            e.printStackTrace();
            return JsonResult.error();
        }
    }

    @GetMapping("/demo2")
    @SysLog(THIS_NAME + "查询全部-封装方法")
    public JsonResult<GetIndexResponse> demo2() {
        try {
            GetIndexResponse test = esBeanConfig.elasticsearchClient().indices().get(getIndex -> getIndex.index("test_1"));
            return JsonResult.ok(test);
        } catch (IOException e) {
            e.printStackTrace();
            return JsonResult.error();
        }
    }

    @GetMapping("/getById")
    @SysLog(THIS_NAME + "根据id获取数据")
    public JsonResult<ESTestUser> getById(@RequestParam Map<String, Object> params) throws IOException {
        MapUtils map = MapUtils.getInstance(params);
        String id = map.getString("id");
        ElasticsearchClient client = esBeanConfig.elasticsearchClient();
        GetResponse<ESTestUser> response = client.get(g -> g
                        .index("test_1")
                        .id(id),
                ESTestUser.class
        );
        return JsonResult.ok(response.source());
    }
}
