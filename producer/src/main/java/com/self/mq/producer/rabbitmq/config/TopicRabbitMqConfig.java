package com.self.mq.producer.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TopicRabbitMqConfig {
    //queue
    public final static String javaQueue = "topicJavaQueue";
    public final static String pythonQueue = "topicPythonQueue";
    public final static String rubyQueue = "topicRubyQueue";
    //topicExchange
    public final static String exchange = "topicExchange";

    @Bean
    public Queue topicJavaQueue() {
        return new Queue(javaQueue);
    }

    @Bean
    public Queue topicPythonQueue() {
        return new Queue(pythonQueue);
    }

    @Bean
    public Queue topicRubyQueue() {
        return new Queue(rubyQueue);
    }

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(exchange);
    }

    //绑定exchange和queue,routingkey规则为java
    //这样只要是消息携带的路由键是topic.java,才会分发到该队列
    @Bean
    Binding bindingJavaExchangeMessage() {
        return BindingBuilder.bind(topicJavaQueue()).to(topicExchange()).with("java");
    }

    //绑定exchange和queue,routingkey规则为python.#
    // 这样只要是消息携带的路由键是以python.开头,都会分发到该队列
    @Bean
    Binding bindingPythonExchangeMessage() {
        return BindingBuilder.bind(topicPythonQueue()).to(topicExchange()).with("python.#");
    }

    //将secondQueue和topicExchange绑定,而且绑定的键值为用上通配路由键规则ruby.*
    // ruby.test1.queue能够路由到该队列，ruby.test.1.queue不行
    @Bean
    Binding bindingRubyExchangeMessage() {
        return BindingBuilder.bind(topicRubyQueue()).to(topicExchange()).with("ruby.*.queue");
    }

}
