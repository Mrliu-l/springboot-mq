package com.self.mq.consumer.rabbitmq;

import com.alibaba.fastjson.JSON;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * 同一个queue中得消息，只会被消费一遍
 */
@Slf4j
@Component
public class RabbitMqConsumer {

    /**
     * 直连交换机，同一个队列中得消息，只会被消费一遍
     */
    @RabbitListener(queues = {"directJavaQueue", "directPythonQueue", "directRubyQueue"})
    public void receiveDirectJavaQueue(Message message){
        String msg = new String(message.getBody(), StandardCharsets.UTF_8);
        log.info("direct received message:{}; properties:{}", msg, JSON.toJSONString(message.getMessageProperties()));
    }

    /**
     * 主题交换机，同一个队列中得消息，只会被消费一遍
     */
    @RabbitListener(queues = "topicJavaQueue")
    public void receiveTopicJavaQueue(Message message){
        String msg = new String(message.getBody(), StandardCharsets.UTF_8);
        log.info("topic1 received message:{}; properties:{}", msg, JSON.toJSONString(message.getMessageProperties()));
    }
    @RabbitListener(queues = "topicJavaQueue")
    public void receiveTopicJavaQueue1(Message message){
        String msg = new String(message.getBody(), StandardCharsets.UTF_8);
        log.info("topic2 received message:{}; properties:{}", msg, JSON.toJSONString(message.getMessageProperties()));
    }

    /**
     * 广播消息，消费端发送后，所有绑定交换机得queu都会收到该消息
     * @param message
     */
    @RabbitListener(queues = "fanoutJavaQueue")
    public void receiveFanoutJavaQueue(Message message){
        String msg = new String(message.getBody(), StandardCharsets.UTF_8);
        log.info("fanoutJavaQueue received message:{}; properties:{}", msg, JSON.toJSONString(message.getMessageProperties()));
    }
    @RabbitListener(queues = "fanoutRubyQueue")
    public void receiveFanoutRubyQueue(Message message){
        String msg = new String(message.getBody(), StandardCharsets.UTF_8);
        log.info("fanoutRubyQueue received message:{}; properties:{}", msg, JSON.toJSONString(message.getMessageProperties()));
    }

    /**
     *  手动ack
     *  配置config中配置项：
     *  listener:
     *       simple:
     *         acknowledge-mode: manual #消费者手动确认消息
     */
    @RabbitListener(queues = "fanoutPythonQueue")
    public void receiveFanoutPythonQueue(Message message, Channel channel) throws IOException {
        String msg = new String(message.getBody(), StandardCharsets.UTF_8);
        log.info("fanoutPythonQueue received message:{}; properties:{}", msg, JSON.toJSONString(message.getMessageProperties()));
        //采用手动ack，multiple = false 确认本次消息，否则确认本次tag之前所有得消息
        //配置中开启ack确认后，消费者未确认时消息阻塞，不会收到后续消息，断开连接后，未确认消息回到正常消息队列，被重复消费
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        /*
         * 还可以拒绝消息
         * 第三个参数是否重回队列
         */
//        channel.basicNack(message.getMessageProperties().getDeliveryTag(),false,true);
    }
}
