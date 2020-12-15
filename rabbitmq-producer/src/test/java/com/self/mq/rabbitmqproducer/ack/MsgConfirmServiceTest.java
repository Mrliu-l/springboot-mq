package com.self.mq.rabbitmqproducer.ack;

import com.self.mq.rabbitmqproducer.RabbitmqProducerApplication;
import com.self.mq.rabbitmqproducer.config.DirectRabbitMqConfig;
import com.self.mq.rabbitmqproducer.service.ack.MsgConfirmService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RabbitmqProducerApplication.class)
public class MsgConfirmServiceTest {

    @Autowired
    private MsgConfirmService msgConfirmService;

    @Test
    public void sendMessage() {
        for (int i = 0; i < 10; i++){
            msgConfirmService.sendMessage(DirectRabbitMqConfig.exchange, "java", "ack test : " + i);
        }
    }
}