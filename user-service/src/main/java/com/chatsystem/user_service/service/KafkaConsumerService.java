package com.chatsystem.user_service.service;

import com.chatsystem.user_service.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {
  @Autowired UserService userService;

  @KafkaListener(topics = "user-topic", groupId = "user-service-group")
  public void consumeUserEvent(User user) {
    System.out.println("Received message: " + user);
    // Process user creation logic here
    userService.saveUser(user);
  }
}
