package com.example.travelagency.receiver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class QueueReceiveController {

    private static final String QUEUE_NAME = "created-user";

    private final Logger logger = LoggerFactory.getLogger(QueueReceiveController.class);

    @JmsListener(destination = QUEUE_NAME, containerFactory = "jmsListenerContainerFactory")
    public void receiveMessage(String message) {
        logger.info("Received message: {}", message);
    }
}
