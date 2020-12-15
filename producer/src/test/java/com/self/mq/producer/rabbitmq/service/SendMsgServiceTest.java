package com.self.mq.producer.rabbitmq.service;

import com.self.mq.producer.ProducerApplication;
import com.self.mq.producer.rabbitmq.config.DirectRabbitMqConfig;
import com.self.mq.producer.rabbitmq.config.TopicRabbitMqConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ProducerApplication.class)
public class SendMsgServiceTest {

    @Autowired
    private SendMsgService sendMsgService;

    @Test
    public void sendMsg() {
        for (int i = 0; i< 10; i++){
//            sendMsgService.sendDirectMsg(DirectRabbitMqConfig.exchange, "java", "java message num " + i);
            sendMsgService.sendDirectMsg(DirectRabbitMqConfig.exchange, "python", "python message num " + i);
//            sendMsgService.sendDirectMsg(DirectRabbitMqConfig.exchange, "ruby", "ruby message num " + i);
        }
    }
}