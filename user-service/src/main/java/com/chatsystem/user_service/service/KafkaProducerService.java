package com.chatsystem.user_service.service;

import com.chatsystem.user_service.entity.User;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {
    private final KafkaTemplate<String, User> kafkaTemplate;

    public KafkaProducerService(KafkaTemplate<String, User> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendUserEvent(String topic, User user) {
        kafkaTemplate.send(topic, user);
        System.out.println("Sent user: " + user + " to topic: " + topic);
    }
}
