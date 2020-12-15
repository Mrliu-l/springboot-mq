package com.self.mq.rabbitmqproducer.config;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class DirectRabbitMqConfig {

    //绑定键
    public final static String javaQueue = "directJavaQueue";
    public final static String pythonQueue = "directPythonQueue";
    public final static String rubyQueue = "directRubyQueue";
    //topicExchange
    public final static String exchange = "directExchange";
    /**
     * 配置属性不一致，消息发送失败
     *      例如：是否持久化，默认配置是true,如果交换机是false，则会发送消息失败
     * @return
     */
    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange(exchange, false, false);
    }
}