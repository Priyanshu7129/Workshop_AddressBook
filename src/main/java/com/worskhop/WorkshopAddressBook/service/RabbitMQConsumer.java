package com.worskhop.WorkshopAddressBook.service;

import com.worskhop.WorkshopAddressBook.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQConsumer {

    @RabbitListener(queues = RabbitMQConfig.USER_QUEUE)
    public void handleUserRegistration(String message) {
        System.out.println("Received User Registration Event: " + message);
    }

    @RabbitListener(queues = RabbitMQConfig.CONTACT_QUEUE)
    public void handleContactAdded(String message) {
        System.out.println("Received Contact Added Event: " + message);
    }
}
