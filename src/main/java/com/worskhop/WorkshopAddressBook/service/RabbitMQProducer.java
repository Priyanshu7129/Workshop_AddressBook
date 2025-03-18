package com.worskhop.WorkshopAddressBook.service;

import com.worskhop.WorkshopAddressBook.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendUserRegistrationMessage(String message) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE, "userRoutingKey", message);
        System.out.println("Sent User Registration Message: " + message);
    }

    public void sendContactAddedMessage(String message) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE, "contactRoutingKey", message);
        System.out.println("Sent Contact Added Message: " + message);
    }
}
