package com.self.mq.producer.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FanoutRabbitMqConfig {

    //queue
    public final static String javaQueue = "fanoutJavaQueue";
    //topicExchange
    public final static String exchange = "fanoutExchange";

    @Bean
    public Queue fanoutJavaQueue() {
        return new Queue(javaQueue);
    }

    @Bean
    FanoutExchange fanoutExchange(){
        return new FanoutExchange(exchange);
    }

    @Bean
    Binding bindingFanoutJavaQueueu(){
        return BindingBuilder.bind(fanoutJavaQueue()).to(fanoutExchange());
    }
}
