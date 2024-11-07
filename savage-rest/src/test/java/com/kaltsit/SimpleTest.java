package com.kaltsit;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.GetResponse;
import co.elastic.clients.elasticsearch.indices.GetIndexResponse;
import com.alibaba.fastjson2.JSONObject;
import com.kaltsit.config.ESBeanConfig;
import com.kaltsit.entity.es.ESTestUser;
import com.kaltsit.service.sys.impl.SysLogServiceImpl;
import com.kaltsit.service.sys.impl.SysMenuServiceImpl;
import com.kaltsit.utils.ESUtils;
import com.kaltsit.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.TopicPartition;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.io.IOException;
import java.time.Duration;
import java.util.*;

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
    public void MenuTest01() {
        String fileName = "C:\\Users\\20201236\\Desktop\\新建文件夹\\test.xlsx";
        //EasyExcel.write(fileName, SysLogEntity.class).sheet("模板").doWrite(sysLogService.list());
    }

    @Test
    void redisTest() {
        Map<String, Object> dict = new HashMap<>();
        dict.put("0", "未知2");
        //dict.put("1", "新增1");
        //dict.put("2", "删除1");
        //dict.put("3", "修改1");
        //dict.put("4", "查询1");
        //dict.put("5", "登录1/登出1");
        RedisUtil.add("SYS:LOG_TYPE", dict);
        System.out.println(RedisUtil.getHashEntries("SYS:LOG_TYPE"));
    }

//    @Test
//    void queryTest(){
//        String userId = "1";
//        String uri = "";
//        QueryWrapper<SysLogEntity> qw = new QueryWrapper<>();
//        QueryWrapperUtils.initQueryWrapper()
//
//    }

    @Test
    void kafkaProducerTest() throws InterruptedException {
        Properties prop = new Properties();
        prop.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        prop.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        prop.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        prop.put(ProducerConfig.ACKS_CONFIG, "all");
        prop.put(ProducerConfig.RETRIES_CONFIG, 0);
        prop.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);
        prop.put(ProducerConfig.LINGER_MS_CONFIG, 1);
        prop.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432);
        String topic = "test";
        KafkaProducer<String, String> producer = new KafkaProducer<>(prop);
        for (int i = 0; i < 100; i++) {
            producer.send(new ProducerRecord<>(topic, Integer.toString(2), "hello kafka" + i));
            System.out.println("生产消息：" + i);
            Thread.sleep(1000);
        }
        producer.close();
    }

    @Test
    void kafkaConsumerTest() {
        Properties prop = new Properties();
        prop.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"127.0.0.1:9092");
        prop.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        prop.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        prop.put(ConsumerConfig.GROUP_ID_CONFIG, "con-1");    // 消费者组
        prop.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");
        prop.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);    //自动提交偏移量
        prop.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, 1000);     //自动提交时间
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(prop);
        ArrayList<String> topics = new ArrayList<>();
        //可以订阅多个消息
        topics.add("test");
        consumer.subscribe(topics);
        try {
            while(true) {
                ConsumerRecords<String, String> poll = consumer.poll(Duration.ofSeconds(10));
                for (TopicPartition topicPartition : poll.partitions()) {
                    //	通过TopicPartition获取指定的消息集合，获取到的就是当前topicPartition下面所有的消息
                    List<ConsumerRecord<String, String>> partitionRecords = poll.records(topicPartition);
                    //	获取TopicPartition对应的主题名称
                    String topic = topicPartition.topic();
                    //	获取TopicPartition对应的分区位置
                    int partition = topicPartition.partition();
                    //	获取当前TopicPartition下的消息条数
                    int size = partitionRecords.size();
                    System.out.printf("--- 获取topic: %s, 分区位置：%s, 消息总数： %s%n", topic, partition, size);
                    for(int i = 0; i < size; i++) {
                        ConsumerRecord<String, String> consumerRecord = partitionRecords.get(i);
                        //	实际的数据内容
                        String key = consumerRecord.key();
                        //	实际的数据内容
                        String value = consumerRecord.value();
                        //	当前获取的消息偏移量
                        long offset = consumerRecord.offset();
                        //	表示下一次从什么位置(offset)拉取消息
                        long commitOffser = offset + 1;
                        System.out.printf("消费消息 key：%s, value：%s, 消息offset: %s, 提交offset: %s%n",
                                key, value, offset, commitOffser);
                        Thread.sleep(1500);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            consumer.close();
        }

    }

}
