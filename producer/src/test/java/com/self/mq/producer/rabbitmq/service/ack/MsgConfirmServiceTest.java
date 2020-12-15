package com.self.mq.producer.rabbitmq.service.ack;


import com.self.mq.producer.ProducerApplication;
import com.self.mq.producer.rabbitmq.config.DirectRabbitMqConfig;
import com.self.mq.producer.rabbitmq.config.FanoutRabbitMqConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ProducerApplication.class)
public class MsgConfirmServiceTest {

    @Autowired
    private MsgConfirmService msgConfirmService;

    /**
     * 验证生产者
     *      未找到交换机，ConfirmCallback回调生效
     */
    @Test
    public void sendMessageExchangeError() {
        for (int i = 0; i< 1; i++){
            msgConfirmService.sendMessage("exhcange name", "java", "java message num " + i);
        }
    }
    /**
     * 验证生产者
     *      未找到路由键对应得queue，ReturnsCallback回调生效
     */
    @Test
    public void sendMessageQueueError() {
        for (int i = 0; i< 1; i++){
            msgConfirmService.sendMessage(DirectRabbitMqConfig.exchange, "java1", "java message num " + i);
        }
    }

    /**
     * 验证消费者
     *      手动确认消息
     */
    @Test
    public void sendAckMessage() {
        for (int i = 0; i< 1; i++){
            msgConfirmService.sendMessage(FanoutRabbitMqConfig.exchange, "python", "python message num " + i);
        }
    }
}