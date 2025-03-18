package com.worskhop.WorkshopAddressBook.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String USER_QUEUE = "userQueue";
    public static final String CONTACT_QUEUE = "contactQueue";
    public static final String EXCHANGE = "appExchange";

    @Bean
    public Queue userQueue() {
        return new Queue(USER_QUEUE, false);
    }

    @Bean
    public Queue contactQueue() {
        return new Queue(CONTACT_QUEUE, false);
    }

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(EXCHANGE);
    }

    @Bean
    public Binding userBinding(Queue userQueue, DirectExchange exchange) {
        return BindingBuilder.bind(userQueue).to(exchange).with("userRoutingKey");
    }

    @Bean
    public Binding contactBinding(Queue contactQueue, DirectExchange exchange) {
        return BindingBuilder.bind(contactQueue).to(exchange).with("contactRoutingKey");
    }
}
