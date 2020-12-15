package com.self.mq.rabbitmqproducer.service.ack;

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

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMessage(String exchange, String routingKey, Object msg){
//        rabbitTemplate.setMandatory(true);
//        rabbitTemplate.setConfirmCallback(this);
//        rabbitTemplate.setReturnsCallback(this);
        rabbitTemplate.convertAndSend(exchange, routingKey, msg);
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        log.info("---- confirm ----ack="+ack+"  cause="+String.valueOf(cause));
        log.info("correlationData -->"+correlationData.toString());
        if(ack){
            log.info("---- confirm ----ack==true  cause="+cause);
        }else{
            log.info("---- confirm ----ack==false  cause="+cause);
        }
    }

    @Override
    public void returnedMessage(ReturnedMessage returned) {
        log.info("returned : {}", returned.toString());
    }
}
