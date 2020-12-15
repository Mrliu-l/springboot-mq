package com.self.mq.producer.rabbitmq.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SendMsgService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public String sendMsg(String exchange, String key, String msg){
        rabbitTemplate.convertAndSend(exchange, key, msg);
        log.info("[send msg success][exchange:{}][key:{}][msg:{}]", exchange, key, msg);
        return "msg send success";
    }
}
