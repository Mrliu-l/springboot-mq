package com.self.mq.producer.rabbitmq.service;

import com.self.mq.producer.ProducerApplication;
import com.self.mq.producer.rabbitmq.config.DirectRabbitMqConfig;
import com.self.mq.producer.rabbitmq.config.FanoutRabbitMqConfig;
import com.self.mq.producer.rabbitmq.config.TopicRabbitMqConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ProducerApplication.class)
public class SendMsgServiceTest {

    @Autowired
    private SendMsgService sendMsgService;

    @Test
    public void sendDirectMsg() {
        for (int i = 0; i< 10; i++){
            sendMsgService.sendMsg(DirectRabbitMqConfig.exchange, "java", "java message num " + i);
        }
    }
    @Test
    public void sendTopicMsg() {
        for (int i = 0; i< 10; i++){
            sendMsgService.sendMsg(TopicRabbitMqConfig.exchange, "java." + i, "topic message num " + i);
        }
    }
    @Test
    public void sendFanoutMsg() {
        for (int i = 0; i< 1; i++){
            sendMsgService.sendMsg(FanoutRabbitMqConfig.exchange, null, "fanout message num " + i);
        }
    }
}