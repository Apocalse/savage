package com.kaltsit.utils;

import com.alibaba.fastjson2.JSONObject;
import com.kaltsit.config.ESConfig;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.ParseException;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.*;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Arrays;

@Component
public class ESUtils {
    private static final RequestOptions COMMON_OPTIONS;
    @Resource
    private ESConfig esConfig;

    static {
        RequestOptions.Builder builder = RequestOptions.DEFAULT.toBuilder();
        builder.addHeader("Authorization", "kyle " + "TOKEN");
        builder.setHttpAsyncResponseConsumerFactory(
                new HttpAsyncResponseConsumerFactory
                        .HeapBufferedResponseConsumerFactory(200*1024*1024));
        COMMON_OPTIONS = builder.build();
    }

    public RestClient buildRestClient(){
        RestClientBuilder builder;
        HttpHost[] httpHosts = Arrays.stream(esConfig.getHosts().split(",")).map(x -> {
            String[] hostInfo = x.split(":");
            return new HttpHost(hostInfo[0], Integer.parseInt(hostInfo[1]), esConfig.getScheme());
        }).toArray(HttpHost[]::new);
        builder = RestClient.builder(httpHosts);
        //在创建restClient的同时，设置每个请求需要发送的默认头文件，以避免在每个请求中指定它们
        Header[] defaultHeaders = new Header[]{new BasicHeader("header", "value")};
        builder.setDefaultHeaders(defaultHeaders);
        //设置一个监听器，用来在节点发生故障的时候，采取相应的操作。
        builder.setFailureListener(new RestClient.FailureListener(){
            @Override
            public void onFailure(Node node) {
                super.onFailure(node);
                //doSomeThing();
            }
        });
        //将节点选择器设置为用于过滤客户机将发送请求到的节点之间的节点，这些节点被设置为客户机本身。
        //这对于防止在启用嗅探时将请求发送到专用的主节点非常有用。默认情况下，客户机向每个配置的节点发送请求
        builder.setNodeSelector(NodeSelector.SKIP_DEDICATED_MASTERS);
        //设置一个回调函数，允许修改默认的请求配置
        //创建restClient，中间的那些配置也可以不设置
        return builder.build();
    }

    /**
     * 发送es请求
     */
    public String requestGet(JSONObject queryStr, String endpoint) throws ParseException, IOException {
        RestClient restClient = buildRestClient();
        //创建一个请求组
        Request request = new Request("GET", endpoint);
        //为请求添加一些参数
        request.addParameter("pretty", "true");
        //请求的主体设置为任何HttpEntity,
        //为HttpEntity指定的ContentType非常重要，因为它将用于设置content - type头部，以便Elasticsearch能够正确解析内容。
        //request.setEntity(new NStringEntity("{\"json\":\"text\"}",ContentType.APPLICATION_JSON));
        //还可以将其设置为一个字符串，该字符串将默认为application/json的ContentType。
        request.setJsonEntity(queryStr.toJSONString());
        //非必须
        request.setOptions(COMMON_OPTIONS);
        //发送一个同步的请求，线程会阻塞
        Response response = restClient.performRequest(request);
        //发送异步请求，然后使用监听器来对返回结果进行处理
        /*restClient.performRequestAsync(request, new ResponseListener() {
            @Override
            public void onSuccess(Response resp) {
                System.out.println("成功");
            }

            @Override
            public void onFailure(Exception arg0) {
                System.out.println("失败");
            }
        });*/
        //响应状态行，您可以从中检索状态代码
        String responseBody = EntityUtils.toString(response.getEntity());
        restClient.close();
        return  responseBody;
    }

    /**
     * 发送es请求--更新
     */
    public String requestPost(JSONObject queryStr,String endpoint) throws ParseException, IOException {
        RestClient restClient = buildRestClient();
        //创建一个请求组
        Request request = new Request("POST", endpoint);
        //防止更新并发时版本不匹配造成更新失败--5 代表重新更新次数
        //request.addParameter("retry_on_conflict", "8");
        //还可以将其设置为一个字符串，该字符串将默认为application/json的ContentType。
        request.setJsonEntity(queryStr.toJSONString());
        //非必须
        request.setOptions(COMMON_OPTIONS);
        //发送一个同步的请求，线程会阻塞
        Response response = restClient.performRequest(request);
        String responseBody = EntityUtils.toString(response.getEntity());
        restClient.close();
        return  responseBody;
    }
}
