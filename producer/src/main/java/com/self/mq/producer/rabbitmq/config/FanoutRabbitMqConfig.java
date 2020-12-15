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
    public final static String pythonQueue = "fanoutPythonQueue";
    public final static String rubyQueue = "fanoutRubyQueue";
    //topicExchange
    public final static String exchange = "fanoutExchange";

    @Bean
    public Queue fanoutJavaQueue() {
        return new Queue(javaQueue);
    }

    @Bean
    public Queue fanoutPythonQueue() {
        return new Queue(pythonQueue);
    }

    @Bean
    public Queue fanoutRubyQueue() {
        return new Queue(rubyQueue);
    }

    @Bean
    FanoutExchange fanoutExchange(){
        return new FanoutExchange(exchange);
    }

    @Bean
    Binding bindingFanoutJavaQueueu(){
        return BindingBuilder.bind(fanoutJavaQueue()).to(fanoutExchange());
    }
    @Bean
    Binding bindingFanoutPythonQueueu(){
        return BindingBuilder.bind(fanoutPythonQueue()).to(fanoutExchange());
    }
    @Bean
    Binding bindingFanoutRubyQueueu(){
        return BindingBuilder.bind(fanoutRubyQueue()).to(fanoutExchange());
    }
}
