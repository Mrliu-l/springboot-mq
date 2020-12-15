package com.self.mq.producer.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TopicRabbitMqConfig {
    public final static String javaQueue = "topicJavaQueue";
    public final static String exchange = "topicExchange";

    @Bean
    public Queue topicJavaQueue() {
        return new Queue(javaQueue);
    }

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(exchange);
    }

    /**
     * 绑定exchange和queue
     * routingkey为java : 完全匹配
     * routingkey为java.# : 以java.开头，即匹配成功
     * routingkey为java.* : java.test1可以匹配成功，java.test1.end匹配失败
     */
    @Bean
    Binding bindingPythonExchangeMessage() {
        return BindingBuilder.bind(topicJavaQueue()).to(topicExchange()).with("java.#");
    }
}
