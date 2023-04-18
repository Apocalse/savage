package com.kaltsit.testController;

import com.alibaba.fastjson2.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/study")
public class StudyController {
    @GetMapping("/test")
    public JSONObject test(){
        String s = "{\n" +
                "  \"retCode\": \"0\",\n" +
                "  \"retMsg\": \"success\",\n" +
                "  \"pageInfo\": {\n" +
                "    \"total\": 300\n" +
                "  },\n" +
                "  \"tasks\": [\n" +
                "    { \"name\": \"张三\",\n" +
                "      \"code\": \"zhangSan\",\n" +
                "      \"status\": 1,\n" +
                "      \"icon\": true,\n" +
                "      \"phone\":  \"17801010101\",\n" +
                "      \"selectable\": 1\n" +
                "    },\n" +
                "    { \"name\": \"李四\",\n" +
                "      \"code\": \"liSi\",\n" +
                "      \"status\": 0,\n" +
                "      \"icon\": false,\n" +
                "      \"phone\": \"17802020202\",\n" +
                "      \"selectable\": 2\n" +
                "    },\n" +
                "    { \"name\": \"王五\",\n" +
                "      \"code\": \"wangWu\",\n" +
                "      \"status\": 2,\n" +
                "      \"icon\": true,\n" +
                "      \"phone\": \"17803030303\",\n" +
                "      \"selectable\": 0\n" +
                "    },\n" +
                "    { \"name\": \"马六\",\n" +
                "      \"code\": \"maLiu\",\n" +
                "      \"status\": 1,\n" +
                "      \"icon\": false,\n" +
                "      \"phone\": \"17804040404\",\n" +
                "      \"selectable\": 2\n" +
                "    }\n" +
                "  ]\n" +
                "}";
        return JSONObject.parseObject(s);
    }
}
