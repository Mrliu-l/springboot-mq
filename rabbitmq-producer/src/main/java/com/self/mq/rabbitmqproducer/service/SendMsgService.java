package com.self.mq.rabbitmqproducer.service;

import com.self.mq.rabbitmqproducer.config.DirectRabbitMqConfig;
import com.self.mq.rabbitmqproducer.config.FanoutRabbitMqConfig;
import com.self.mq.rabbitmqproducer.config.TopicRabbitMqConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SendMsgService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public String sendTopicMsg(String key, String msg){
        rabbitTemplate.convertAndSend(TopicRabbitMqConfig.exchange, key, msg);
        return "msg send success";
    }

    public String sendDirectMsg(String key, String msg){
        rabbitTemplate.convertAndSend(DirectRabbitMqConfig.exchange, key, msg );
        log.info("[send msg success][key:{}][msg:{}]", key, msg);
        return "msg send success";
    }

    public String sendFanoutMsg(String msg){
        rabbitTemplate.convertAndSend(FanoutRabbitMqConfig.exchange, null, msg);
        log.info("[send msg success][msg:{}]", msg);
        return "msg send success";
    }
}
