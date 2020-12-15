package com.self.mq.consumer.rabbitmq;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Slf4j
@Component
public class DirectRabbitMqConsumer {

    @RabbitListener(queues = {"directJavaQueue", "directPythonQueue", "directRubyQueue"})
    public void receiveDirectJavaQueue(Message message){
        String msg = new String(message.getBody(), StandardCharsets.UTF_8);
        log.info("direct received message:{}; properties:{}", msg, JSON.toJSONString(message.getMessageProperties()));
    }

    /**
     * 同一个队列，多个消费端，消息会轮询消费，不能重复消费
     */
    @RabbitListener(queues = "directPythonQueue")
    public void receiveDirectPythonQueue(Message message){
        String msg = new String(message.getBody(), StandardCharsets.UTF_8);
        log.info("received directPythonQueue message : " + msg);
    }
}
