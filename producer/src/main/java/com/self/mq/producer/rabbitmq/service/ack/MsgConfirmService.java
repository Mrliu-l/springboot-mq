package com.self.mq.producer.rabbitmq.service.ack;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate.ConfirmCallback;
import org.springframework.amqp.rabbit.core.RabbitTemplate.ReturnsCallback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MsgConfirmService implements ConfirmCallback, ReturnsCallback {

    private RabbitTemplate rabbitTemplate;

    @Autowired
    public void setRabbitTemplate(RabbitTemplate rabbitTemplate){
        this.rabbitTemplate = rabbitTemplate;
        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.setReturnsCallback(this);
    }

    public void sendMessage(String exchange, String routingKey, Object msg){
        rabbitTemplate.convertAndSend(exchange, routingKey, msg);
    }

    /**
     *  ConfirmCallback只确认消息是否到达exchange，已实现方法confirm中ack属性为标准，true到达
     *  config : 需要开启rabbitmq得ack    publisher-confirm-type
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if(ack){
            log.info("发送消息到exchange成功");
        }else{
            log.error("发送消息到exchange失败" + cause);
        }
    }

    /**
     *  ReturnCallback消息没有正确到达队列时触发回调，如果正确到达队列不执行
     *  config : 需要开启rabbitmq发送失败回退    publisher-returns    或rabbitTemplate.setMandatory(true);设置为true
     */
    @Override
    public void returnedMessage(ReturnedMessage returned) {
        log.error("匹配queue失败:{}", returned.toString());
    }
}
